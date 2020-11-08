package ru.itis.xokken.Filters;

import ru.itis.xokken.Entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "BrowserFilter", urlPatterns = "/gates/*")
public class FilterRegistration implements Filter {
    FilterConfig config;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        if (session.getAttribute("auth") == null){
            session.setAttribute("auth", false);
        }
        if (!((boolean) session.getAttribute("auth"))) {
            ((HttpServletResponse) resp).sendRedirect(config.getServletContext().getContextPath() + "/registration");
        }
        else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;

    }

}
