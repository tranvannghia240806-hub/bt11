package com.bookstore.service;

import com.bookstore.dao.BookDAO_24110287;
import com.bookstore.dto.Cart_24110287;
import com.bookstore.dto.CartItem_24110287;
import com.bookstore.entity.Books_24110287;
import jakarta.servlet.http.HttpSession;

/** Mỗi method trả về thông báo (null nếu mọi thứ ổn). */
public class CartService_24110287 {
    public static final String SESSION_KEY = "cart";
    private final BookDAO_24110287 bookDAO = new BookDAO_24110287();

    public Cart_24110287 getCart(HttpSession session) {
        Cart_24110287 cart = (Cart_24110287) session.getAttribute(SESSION_KEY);
        if (cart == null) {
            cart = new Cart_24110287();
            session.setAttribute(SESSION_KEY, cart);
        }
        return cart;
    }

    private int stockOf(Books_24110287 b) {
        return b.getQuantity() == null ? 0 : b.getQuantity();
    }

    public String add(Cart_24110287 cart, int bookId, int qty) {
        if (qty < 1) return "Số lượng phải lớn hơn 0.";
        Books_24110287 book = bookDAO.findById(bookId);
        if (book == null) return "Sách không tồn tại.";
        int stock = stockOf(book);
        if (stock <= 0) return "Sách \"" + book.getTitle() + "\" đã hết hàng.";

        CartItem_24110287 item = cart.get(bookId);
        int newQty = (item == null ? 0 : item.getQuantity()) + qty;
        String msg = null;
        if (newQty > stock) {
            newQty = stock;
            msg = "Chỉ còn " + stock + " cuốn \"" + book.getTitle() + "\" trong kho, đã điều chỉnh số lượng.";
        }
        if (item == null) {
            item = new CartItem_24110287(bookId, book.getTitle(), book.getCoverImage(), book.getPrice());
            cart.put(item);
        }
        item.setQuantity(newQty);
        item.setStock(stock);
        return msg;
    }

    public String update(Cart_24110287 cart, int bookId, int qty) {
        CartItem_24110287 item = cart.get(bookId);
        if (item == null) return "Sản phẩm không có trong giỏ hàng.";
        if (qty <= 0) {                       // nhập 0 hoặc âm = xóa khỏi giỏ
            cart.remove(bookId);
            return null;
        }
        Books_24110287 book = bookDAO.findById(bookId);
        if (book == null || stockOf(book) <= 0) {
            cart.remove(bookId);
            return "Sách không còn hàng nên đã được gỡ khỏi giỏ.";
        }
        int stock = stockOf(book);
        String msg = null;
        if (qty > stock) {
            qty = stock;
            msg = "Chỉ còn " + stock + " cuốn \"" + book.getTitle() + "\" trong kho, đã điều chỉnh số lượng.";
        }
        item.setQuantity(qty);
        item.setStock(stock);
        return msg;
    }

    public void remove(Cart_24110287 cart, int bookId) { cart.remove(bookId); }
    public void clear(Cart_24110287 cart) { cart.clear(); }
}
