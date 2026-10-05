package com.bookstore.controller;

import com.bookstore.dao.BookDAO_24110287;
import com.bookstore.entity.Books_24110287;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/book-detail")
public class BookDetailController_24110287 extends HttpServlet {
    private final BookDAO_24110287 bookDAO = new BookDAO_24110287();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Books_24110287 book = null;
        try { book = bookDAO.findById(Integer.parseInt(req.getParameter("id"))); }
        catch (NumberFormatException ignored) { }
        if (book == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        req.setAttribute("book", book);
        req.getRequestDispatcher("/WEB-INF/views/book-detail.jsp").forward(req, resp);
    }
}
