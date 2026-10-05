package com.bookstore.controller;

import com.bookstore.dto.Cart_24110287;
import com.bookstore.entity.Orders_24110287;
import com.bookstore.entity.Users_24110287;
import com.bookstore.service.CartService_24110287;
import com.bookstore.service.OrderService_24110287;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/user/checkout")
public class CheckoutController_24110287 extends HttpServlet {
    private final CartService_24110287 cartService = new CartService_24110287();
    private final OrderService_24110287 orderService = new OrderService_24110287();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart_24110287 cart = cartService.getCart(req.getSession());
        if (cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/user/cart");
            return;
        }
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/WEB-INF/views/user/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Cart_24110287 cart = cartService.getCart(session);
        Users_24110287 user = (Users_24110287) session.getAttribute("user");
        try {
            Orders_24110287 order = orderService.checkoutCOD(user.getId(), cart,
                    req.getParameter("receiverName"), req.getParameter("phone"),
                    req.getParameter("address"), req.getParameter("note"));
            cartService.clear(cart);
            resp.sendRedirect(req.getContextPath() + "/user/orders?success=" + order.getOrderId());
        } catch (IllegalArgumentException | IllegalStateException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("cart", cart);
            req.getRequestDispatcher("/WEB-INF/views/user/checkout.jsp").forward(req, resp);
        }
    }
}
