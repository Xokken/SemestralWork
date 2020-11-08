package ru.itis.xokken.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FilterUpload", urlPatterns = "/gates/upload")
public class FilterUpload implements Filter {
    FilterConfig config;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        if (((boolean) session.getAttribute("auth"))) {
            ((HttpServletResponse) resp).sendRedirect(config.getServletContext().getContextPath() + "/gates/profile");
        }
        else{
            chain.doFilter(req, resp);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

}
