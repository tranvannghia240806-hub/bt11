package com.bookstore.enums;

public enum OrderStatus_24110287 {
    NEW(0, "Đơn hàng mới"),
    CONFIRMED(1, "Đã xác nhận"),
    PREPARING(2, "Chuẩn bị hàng"),
    SHIPPING(3, "Vận chuyển"),
    DELIVERING(4, "Giao hàng"),
    DELIVERED(5, "Đã giao"),
    CANCELLED(6, "Đơn hàng hủy"),
    RETURNED(7, "Đơn hàng hoàn");

    private final int code;
    private final String label;

    OrderStatus_24110287(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() { return code; }
    public String getLabel() { return label; }

    public static OrderStatus_24110287 fromCode(Integer code) {
        if (code != null) {
            for (OrderStatus_24110287 s : values()) {
                if (s.code == code) return s;
            }
        }
        return NEW;
    }
}
