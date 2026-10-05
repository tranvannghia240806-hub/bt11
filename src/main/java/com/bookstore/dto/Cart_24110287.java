package com.bookstore.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart_24110287 implements Serializable {
    private final Map<Integer, CartItem_24110287> items = new LinkedHashMap<>();

    public Collection<CartItem_24110287> getItems() { return items.values(); }
    public CartItem_24110287 get(int bookId) { return items.get(bookId); }
    public void put(CartItem_24110287 item) { items.put(item.getBookId(), item); }
    public void remove(int bookId) { items.remove(bookId); }
    public void clear() { items.clear(); }
    public boolean isEmpty() { return items.isEmpty(); }

    public int getTotalQuantity() {
        int n = 0;
        for (CartItem_24110287 i : items.values()) n += i.getQuantity();
        return n;
    }

    public BigDecimal getTotal() {
        BigDecimal t = BigDecimal.ZERO;
        for (CartItem_24110287 i : items.values()) t = t.add(i.getSubtotal());
        return t;
    }
}
