package com.bookstore.controller;

import com.bookstore.dto.Cart_24110287;
import com.bookstore.service.CartService_24110287;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/user/cart")
public class CartController_24110287 extends HttpServlet {
    private final CartService_24110287 cartService = new CartService_24110287();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("cart", cartService.getCart(session));
        req.setAttribute("cartMsg", session.getAttribute("cartMsg"));   // flash message
        session.removeAttribute("cartMsg");
        req.getRequestDispatcher("/WEB-INF/views/user/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Cart_24110287 cart = cartService.getCart(session);
        String action = req.getParameter("action");
        String msg = null;
        try {
            if ("add".equals(action)) {
                msg = cartService.add(cart, Integer.parseInt(req.getParameter("bookId")), parseQty(req, 1));
            } else if ("update".equals(action)) {
                msg = cartService.update(cart, Integer.parseInt(req.getParameter("bookId")), parseQty(req, 1));
            } else if ("remove".equals(action)) {
                cartService.remove(cart, Integer.parseInt(req.getParameter("bookId")));
            } else if ("clear".equals(action)) {
                cartService.clear(cart);
            }
        } catch (NumberFormatException e) {
            msg = "Dữ liệu không hợp lệ.";
        }
        if (msg != null) session.setAttribute("cartMsg", msg);
        resp.sendRedirect(req.getContextPath() + "/user/cart");   // Post-Redirect-Get
    }

    private int parseQty(HttpServletRequest req, int def) {
        try { return Integer.parseInt(req.getParameter("qty")); }
        catch (NumberFormatException e) { return def; }
    }
}
