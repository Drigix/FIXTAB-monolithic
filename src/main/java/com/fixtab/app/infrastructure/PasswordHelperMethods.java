package com.fixtab.app.infrastructure;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class PasswordHelperMethods {
    public static String passwordToHash(String password, String userName, String salt)
    {
        try
        {
            password = String.format("%s%s%s", userName, salt, password);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            return new String(Base64.encodeBase64(messageDigest.digest(), false));
        }
        catch (Exception ex)
        {
            return new String("");
        }
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }

    public static String generateBase64Salt() {
        return new String(Base64.encodeBase64(generateSalt(), false));
    }

    public static String generateRandomPassword() {
        return new String(Base64.encodeBase64(generateSalt(), false));
    }

}
