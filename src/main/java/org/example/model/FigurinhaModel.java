package org.example.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FigurinhaModel {
    public static String gerarHash(String capa) {
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

    public static byte[] lerArquivoImagem(String caminhoImagem) throws IOException {
        File arquivo = new File(caminhoImagem);
        FileInputStream fis = new FileInputStream(arquivo);
        byte[] imagemBytes = new byte[(int) arquivo.length()];
        fis.read(imagemBytes);
        fis.close();

        return imagemBytes;
    }

    public static byte[] converterImagem(String imagem) throws IOException {
        return lerArquivoImagem(imagem);
    }
}