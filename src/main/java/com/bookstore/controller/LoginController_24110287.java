package com.bookstore.controller;

import com.bookstore.entity.Users_24110287;
import com.bookstore.service.UserService_24110287;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController_24110287 extends HttpServlet {
    private final UserService_24110287 userService = new UserService_24110287();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Users_24110287 user = userService.login(req.getParameter("email"), req.getParameter("password"));

        // Đề bài: đăng nhập đúng vai trò user -> trang chủ, ngược lại quay lại trang đăng nhập
        if (user != null && !Boolean.TRUE.equals(user.getAdmin())) {
            req.getSession().invalidate();                 // chống session fixation
            req.getSession(true).setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("error", user == null
                    ? "Email hoặc mật khẩu không đúng."
                    : "Tài khoản này không phải vai trò User.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
