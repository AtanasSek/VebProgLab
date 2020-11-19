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
        model.addAttribute("courses",courseService.listAll());
        return "listCourses.html";
    }

    @PostMapping
    public String RdrctToAddStudent(@RequestParam(required = true) Long courseId, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("courseId",courseId);
        return "redirect:/AddStudent";
    }



    //TODO: vrakja 405 method GET not supported. HTML ne podrzuva delete metoda, mora so ajax povik ili nesto slicno
    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @GetMapping("/add")
    public String addCourse(Model model){
        model.addAttribute("teacherList",teacherService.findAll());
        return "addCourse.html";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name , @RequestParam String description , @RequestParam Long id){
        //Go smestuvam vo objekt namesto direktno da go pratam vo funkcijata poradi citlivost
        Teacher teacher = teacherService.findById(id);
        courseService.addCourse(new Course(name,description,teacher));
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
    public String editCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long id, @PathVariable Long courseId, Model model){
        Course course = new Course(courseId,name,description);
        course.setTeacher(teacherService.findById(id));
        courseService.editCourse(course);
        return "redirect:/courses";
    }
}
