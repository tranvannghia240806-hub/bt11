package com.bookstore.controller;

import com.bookstore.dao.BookDAO_24110287;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/home")
public class HomeController_24110287 extends HttpServlet {
    private static final int PAGE_SIZE = 6;
    private final BookDAO_24110287 bookDAO = new BookDAO_24110287();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long total = bookDAO.count();
        int totalPages = (int) Math.max(1, Math.ceil(total / (double) PAGE_SIZE));
        int page = 1;
        try { page = Integer.parseInt(req.getParameter("page")); } catch (NumberFormatException ignored) { }
        page = Math.min(Math.max(page, 1), totalPages);

        req.setAttribute("books", bookDAO.findPage(page, PAGE_SIZE));
        req.setAttribute("page", page);
        req.setAttribute("totalPages", totalPages);
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}
