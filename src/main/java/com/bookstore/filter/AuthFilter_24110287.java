package com.bookstore.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter({"/user/cart", "/user/checkout", "/user/orders"})
public class AuthFilter_24110287 implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) req;
        HttpServletResponse p = (HttpServletResponse) resp;
        HttpSession s = r.getSession(false);
        if (s == null || s.getAttribute("user") == null) {   // tên attribute lúc đăng nhập
            p.sendRedirect(r.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, resp);
    }
}
