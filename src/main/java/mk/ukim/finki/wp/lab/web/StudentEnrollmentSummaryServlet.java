package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentEnrollmentSummaryServlet",urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummaryServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context = new WebContext(request,response,request.getServletContext());
        response.setContentType("text/html;charset=UTF-8");

        //ID od sesija
        String courseId = request.getSession().getAttribute("courseId").toString();
        context.setVariable("courseId",courseId);

        //Course od findById
        Course course = courseService.findById(Long.parseLong(courseId));
        context.setVariable("course",course);

        this.springTemplateEngine.process("studentsInCourse.html",context,response.getWriter());
    }
}
