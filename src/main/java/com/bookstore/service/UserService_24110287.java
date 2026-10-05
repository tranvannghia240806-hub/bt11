package com.bookstore.service;

import com.bookstore.dao.UserDAO_24110287;
import com.bookstore.entity.Users_24110287;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService_24110287 {
    private final UserDAO_24110287 userDAO = new UserDAO_24110287();

    /** Trả về user nếu đúng email + mật khẩu, ngược lại null. */
    public Users_24110287 login(String email, String password) {
        if (email == null || password == null || email.isBlank() || password.isEmpty()) return null;
        return userDAO.findByEmailAndPasswd(email.trim(), md5(password));
    }

    public static String md5(String s) {
        try {
            byte[] d = MessageDigest.getInstance("MD5").digest(s.getBytes(StandardCharsets.UTF_8));
            return String.format("%032x", new BigInteger(1, d));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
