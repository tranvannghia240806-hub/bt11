package com.bookstore.service;

import com.bookstore.dao.OrderDAO_24110287;
import com.bookstore.dto.Cart_24110287;
import com.bookstore.dto.CartItem_24110287;
import com.bookstore.entity.OrderItems_24110287;
import com.bookstore.entity.Orders_24110287;
import com.bookstore.enums.OrderStatus_24110287;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderService_24110287 {
    private final OrderDAO_24110287 orderDAO = new OrderDAO_24110287();

    /** Ném IllegalArgumentException (dữ liệu sai) hoặc IllegalStateException (hết hàng). */
    public Orders_24110287 checkoutCOD(int userId, Cart_24110287 cart,
                                       String name, String phone, String address, String note) {
        if (cart == null || cart.isEmpty()) throw new IllegalArgumentException("Giỏ hàng đang trống.");
        name = name == null ? "" : name.trim();
        phone = phone == null ? "" : phone.trim();
        address = address == null ? "" : address.trim();
        note = note == null ? null : note.trim();

        if (name.isEmpty()) throw new IllegalArgumentException("Vui lòng nhập họ tên người nhận.");
        if (!phone.matches("^0\\d{9}$")) throw new IllegalArgumentException("Số điện thoại phải gồm 10 chữ số, bắt đầu bằng 0.");
        if (address.isEmpty()) throw new IllegalArgumentException("Vui lòng nhập địa chỉ giao hàng.");

        Orders_24110287 order = new Orders_24110287();
        order.setUserId(userId);
        order.setReceiverName(name);
        order.setPhone(phone);
        order.setAddress(address);
        order.setNote(note);
        order.setPaymentMethod("COD");
        order.setStatus(OrderStatus_24110287.NEW.getCode());
        order.setOrderDate(LocalDateTime.now());

        for (CartItem_24110287 ci : cart.getItems()) {
            OrderItems_24110287 oi = new OrderItems_24110287();
            oi.setOrder(order);
            oi.setBookId(ci.getBookId());
            oi.setQuantity(ci.getQuantity());
            order.getItems().add(oi);
        }
        return orderDAO.placeOrder(order);
    }

    public List<Orders_24110287> history(int userId, Integer status) {
        return orderDAO.findByUser(userId, status);
    }

    public Map<Integer, Long> countByStatus(int userId) {
        return orderDAO.countByStatus(userId);
    }
}
