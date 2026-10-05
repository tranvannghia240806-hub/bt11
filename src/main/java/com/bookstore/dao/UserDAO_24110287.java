package com.bookstore.dao;

import com.bookstore.entity.Users_24110287;
import com.bookstore.util.JPAUtil_24110287;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UserDAO_24110287 {

    public Users_24110287 findByEmailAndPasswd(String email, String passwdHash) {
        EntityManager em = JPAUtil_24110287.getEntityManager();
        try {
            List<Users_24110287> list = em.createQuery(
                    "select u from Users_24110287 u where u.email = :e and u.passwd = :p", Users_24110287.class)
                    .setParameter("e", email)
                    .setParameter("p", passwdHash)
                    .setMaxResults(1)
                    .getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }
}
