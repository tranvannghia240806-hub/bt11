package com.bookstore.controller;

import com.bookstore.entity.Users_24110287;
import com.bookstore.enums.OrderStatus_24110287;
import com.bookstore.service.OrderService_24110287;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/user/orders")
public class OrderHistoryController_24110287 extends HttpServlet {
    private final OrderService_24110287 orderService = new OrderService_24110287();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users_24110287 user = (Users_24110287) req.getSession().getAttribute("user");

        Integer status = null;                       // không có tham số = tất cả
        String s = req.getParameter("status");
        if (s != null && !s.isBlank()) {
            try {
                int code = Integer.parseInt(s);
                if (code >= 0 && code < OrderStatus_24110287.values().length) status = code;
            } catch (NumberFormatException ignored) { }
        }

        Map<Integer, Long> counts = orderService.countByStatus(user.getId());
        long all = counts.values().stream().mapToLong(Long::longValue).sum();

        req.setAttribute("orders", orderService.history(user.getId(), status));
        req.setAttribute("statuses", OrderStatus_24110287.values());
        req.setAttribute("counts", counts);
        req.setAttribute("allCount", all);
        req.setAttribute("currentStatus", status);
        req.setAttribute("success", req.getParameter("success"));
        req.getRequestDispatcher("/WEB-INF/views/user/orders.jsp").forward(req, resp);
    }
}
