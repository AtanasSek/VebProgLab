package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
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

    @GetMapping("/add")
    public String saveCourse(){
        return "a";
    }
}
