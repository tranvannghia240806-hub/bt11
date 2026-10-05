package com.bookstore.dao;

import com.bookstore.entity.Books_24110287;
import com.bookstore.entity.OrderItems_24110287;
import com.bookstore.entity.Orders_24110287;
import com.bookstore.util.JPAUtil_24110287;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAO_24110287 {

    /**
     * Lưu đơn + trừ tồn kho trong CÙNG 1 transaction.
     * Khóa dòng sách (PESSIMISTIC_WRITE) để 2 người mua cùng lúc không làm tồn kho âm.
     */
    public Orders_24110287 placeOrder(Orders_24110287 order) {
        EntityManager em = JPAUtil_24110287.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            BigDecimal total = BigDecimal.ZERO;
            for (OrderItems_24110287 it : order.getItems()) {
                Books_24110287 book = em.find(Books_24110287.class, it.getBookId(),
                        LockModeType.PESSIMISTIC_WRITE);
                if (book == null) {
                    throw new IllegalStateException("Sách (mã " + it.getBookId() + ") không còn tồn tại.");
                }
                int stock = book.getQuantity() == null ? 0 : book.getQuantity();
                if (stock < it.getQuantity()) {
                    throw new IllegalStateException("Sách \"" + book.getTitle()
                            + "\" chỉ còn " + stock + " cuốn trong kho.");
                }
                book.setQuantity(stock - it.getQuantity());
                it.setPrice(book.getPrice());          // lấy giá hiện tại trong DB
                it.setBookTitle(book.getTitle());
                total = total.add(it.getSubtotal());
            }
            order.setTotalAmount(total);
            em.persist(order);
            tx.commit();
            return order;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /** status = null -> tất cả. Fetch luôn items để JSP đọc được sau khi đóng EntityManager. */
    public List<Orders_24110287> findByUser(int userId, Integer status) {
        EntityManager em = JPAUtil_24110287.getEntityManager();
        try {
            String jpql = "select distinct o from Orders_24110287 o left join fetch o.items "
                    + "where o.userId = :uid" + (status != null ? " and o.status = :st" : "")
                    + " order by o.orderDate desc";
            TypedQuery<Orders_24110287> q = em.createQuery(jpql, Orders_24110287.class);
            q.setParameter("uid", userId);
            if (status != null) q.setParameter("st", status);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Map<Integer, Long> countByStatus(int userId) {
        EntityManager em = JPAUtil_24110287.getEntityManager();
        try {
            List<Object[]> rows = em.createQuery(
                    "select o.status, count(o) from Orders_24110287 o "
                            + "where o.userId = :uid group by o.status", Object[].class)
                    .setParameter("uid", userId).getResultList();
            Map<Integer, Long> map = new HashMap<>();
            for (Object[] r : rows) map.put(((Number) r[0]).intValue(), (Long) r[1]);
            return map;
        } finally {
            em.close();
        }
    }
}
