package com.imadelfetouh.adminservice.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    private static final PasswordHash passwordHash = new PasswordHash();
    private final String alg = "SHA-256";

    private PasswordHash() {

    }

    public static PasswordHash getInstance() {
        return passwordHash;
    }

    public String hash(String password) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(alg);
            byte[] bytes = messageDigest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();

            for(byte b : bytes) {
                int value = 0xFF & b;
                stringBuilder.append(Integer.toHexString(value));
            }
            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
