package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentProfileServlet",urlPatterns = "/StudentProfile")
public class StudentProfileServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final StudentService studentService;

    public StudentProfileServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //ne e gotovo
        WebContext context = new WebContext(request,response,request.getServletContext());
        response.setContentType("text/html;charset=UTF-8");

        //ID od sesija
        String courseId = (String)request.getSession().getAttribute("courseId");
        context.setVariable("courseId",courseId);

        //Course od findById
        Course course = courseService.findById(Long.parseLong(courseId));
        context.setVariable("course",course);

        this.springTemplateEngine.process("studentProfile.html",context,response.getWriter());
    }
}
