package mk.ukim.finki.wp.lab.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "HomeFilter")
public class HomeFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Long courseId = (Long)request.getSession().getAttribute("courseId");
        System.out.println("Filter Debug ,COURSEID:" + courseId);

        if(courseId == null
                && !request.getServletPath().equals("/courses")
                && !request.getServletPath().equals("/courses/add")
                && !request.getServletPath().contains("/courses/delete")
                && !request.getServletPath().contains("/courses/edit")
                && !request.getServletPath().contains("/teachers")) {

            response.sendRedirect("/courses");
        }
        else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
