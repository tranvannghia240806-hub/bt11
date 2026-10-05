package com.bookstore.dao;

import com.bookstore.entity.Books_24110287;
import com.bookstore.util.JPAUtil_24110287;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BookDAO_24110287 {

    public Books_24110287 findById(int id) {
        EntityManager em = JPAUtil_24110287.getEntityManager();
        try {
            return em.find(Books_24110287.class, id);
        } finally {
            em.close();
        }
    }

    public List<Books_24110287> findPage(int page, int size) {
        EntityManager em = JPAUtil_24110287.getEntityManager();
        try {
            return em.createQuery("select b from Books_24110287 b order by b.bookid", Books_24110287.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public long count() {
        EntityManager em = JPAUtil_24110287.getEntityManager();
        try {
            return em.createQuery("select count(b) from Books_24110287 b", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }
}
