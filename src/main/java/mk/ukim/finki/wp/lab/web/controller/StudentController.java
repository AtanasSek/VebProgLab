package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/courses")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/AddStudent")
    public String ShowAddStudentForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Long courseId = (Long)session.getAttribute("courseId");
        model.addAttribute("courseId",courseId);
        model.addAttribute("students",studentService.listAll());



        return "listStudents.html";
    }

    @PostMapping("/AddStudent")
    public String RedirectToEnrollmentSummary(HttpServletRequest request , Model model){
        HttpSession session = request.getSession();

        String studentUser = request.getParameter("studentUser");
        session.setAttribute("username",studentUser);

        Student student = studentService.findByUsername(studentUser);
        String courseId = session.getAttribute("courseId").toString();

        courseService.addStudentInCourse(student.getUsername(),Long.parseLong(courseId));

        return "redirect:/courses/StudentEnrollmentSummary";
    }

    @GetMapping("/CreateStudent")
    public String ShowCreateStudentForm(HttpServletRequest request){
        return "createStudent.html";
    }

    @PostMapping("/CreateStudent")
    public String SaveStudent(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        studentService.save(username,password,name,surname);

        return "redirect:/courses/AddStudent";
    }

}
