package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//@WebServlet(name = "ListStudentServlet",urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentUser = request.getParameter("studentUser");
        HttpSession session = request.getSession();


        session.setAttribute("username",studentUser);
        Student student = studentService.findByUsername(studentUser);
        String courseId = session.getAttribute("courseId").toString();

        courseService.addStudentInCourse(student.getUsername(),Long.parseLong(courseId));
        response.sendRedirect("/StudentEnrollmentSummary");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context = new WebContext(request,response,request.getServletContext());
        response.setContentType("text/html;charset=UTF-8");
        context.setVariable("students",studentService.listAll());
        String courseId = request.getSession().getAttribute("courseId").toString();
        context.setVariable("courseId",courseId);
        this.springTemplateEngine.process("listStudents.html",context,response.getWriter());

    }
}
