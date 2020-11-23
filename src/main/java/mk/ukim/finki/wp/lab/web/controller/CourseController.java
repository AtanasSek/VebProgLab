package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        List<Course> courseList = courseService.listAll();
        courseList.sort(Comparator.comparing(Course::getName));
        model.addAttribute("courses",courseService.listAll());

        return "listCourses.html";
    }

    @PostMapping
    public String RdrctToAddStudent(@RequestParam(required = true) Long courseId, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("courseId",courseId);
        return "redirect:/AddStudent";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteForm(){
        return "deleteCourse.html";
    }

    @GetMapping("/add")
    public String addCourse(Model model){
        model.addAttribute("teacherList",teacherService.findAll());
        return "addCourse.html";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name , @RequestParam String description , @RequestParam Long id){
        //Go smestuvam vo objekt namesto direktno da go pratam vo funkcijata poradi citlivost
        if(courseService.checkIfCourseExists(name.trim()))
            return "redirect:/courses";
        Teacher teacher = teacherService.findById(id);
        Course course = new Course(name,description,teacher);
        teacherService.findById(id).getCourses().add(course);
        System.out.println(teacherService.findById(id).getCourses());
        courseService.addCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Course course = courseService.findById(id);
        model.addAttribute("courseName",course.getName());
        model.addAttribute("courseDescription",course.getDescription());
        model.addAttribute("teacherList",teacherService.findAll());
        return "addCourse.html";
    }

    @PostMapping("/edit/{courseId}")
    public String editCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long id, @PathVariable Long courseId){
        Course course = new Course(courseId,name,description);
        course.setTeacher(teacherService.findById(id));
        courseService.editCourse(course);
        return "redirect:/courses";
    }
}
