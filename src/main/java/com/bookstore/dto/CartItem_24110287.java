package com.bookstore.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem_24110287 implements Serializable {
    private int bookId;
    private String title;
    private String coverImage;
    private BigDecimal price;
    private int quantity;
    private int stock;

    public CartItem_24110287(int bookId, String title, String coverImage, BigDecimal price) {
        this.bookId = bookId;
        this.title = title;
        this.coverImage = coverImage;
        this.price = price == null ? BigDecimal.ZERO : price;
    }

    public BigDecimal getSubtotal() { return price.multiply(BigDecimal.valueOf(quantity)); }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getCoverImage() { return coverImage; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
