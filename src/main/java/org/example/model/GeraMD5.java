package org.example.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeraMD5 {
    public static String gerar(String capa) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] inputBytes = capa.getBytes();
            byte[] hashBytes = md.digest(inputBytes);

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao calcular o hash MD5", e);
        }
    }
}
