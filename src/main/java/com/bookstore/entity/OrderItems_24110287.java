package com.bookstore.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItems_24110287 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders_24110287 order;

    @Column(name = "bookid", nullable = false)
    private Integer bookId;

    @Column(name = "book_title", columnDefinition = "nvarchar(200)")
    private String bookTitle;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    public BigDecimal getSubtotal() {
        return price == null || quantity == null ? BigDecimal.ZERO
                : price.multiply(BigDecimal.valueOf(quantity));
    }

    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }
    public Orders_24110287 getOrder() { return order; }
    public void setOrder(Orders_24110287 order) { this.order = order; }
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
