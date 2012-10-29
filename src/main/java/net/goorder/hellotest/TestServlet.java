package net.goorder.hellotest;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Witold Szczerba
 */
@WebServlet(urlPatterns="/hello")
public class TestServlet extends HttpServlet {

    @Inject
    private TestCdiBean cdiBean;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().append("Hello from Servlet\n");
        resp.getWriter().append(cdiBean.greet());
    }
    
}
