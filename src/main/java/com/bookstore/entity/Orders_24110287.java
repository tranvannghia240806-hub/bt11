package com.bookstore.entity;

import com.bookstore.enums.OrderStatus_24110287;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders_24110287 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "receiver_name", nullable = false, columnDefinition = "nvarchar(100)")
    private String receiverName;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "address", nullable = false, columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "note", columnDefinition = "nvarchar(255)")
    private String note;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "payment_method", nullable = false, length = 10)
    private String paymentMethod = "COD";

    @Column(name = "status", nullable = false)
    private Integer status = OrderStatus_24110287.NEW.getCode();

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems_24110287> items = new ArrayList<>();

    public String getStatusLabel() { return OrderStatus_24110287.fromCode(status).getLabel(); }

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public List<OrderItems_24110287> getItems() { return items; }
    public void setItems(List<OrderItems_24110287> items) { this.items = items; }
}
