package com.bookstore.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil_24110287 {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("BookStorePU");

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }
}
