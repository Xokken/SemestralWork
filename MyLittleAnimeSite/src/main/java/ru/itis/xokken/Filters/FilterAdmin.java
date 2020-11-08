package ru.itis.xokken.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FilterAdmin", urlPatterns = "/admin")
public class FilterAdmin implements Filter {
    FilterConfig config;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        if (session.getAttribute("admin") == null){
            session.setAttribute("admin", false);
        }
        if (!((boolean) session.getAttribute("admin"))) {
            ((HttpServletResponse) resp).sendRedirect(config.getServletContext().getContextPath() + "/gates/home");
        }
        else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
