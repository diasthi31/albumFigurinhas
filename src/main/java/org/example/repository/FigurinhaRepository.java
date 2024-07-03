package org.example.repository;

import org.example.entity.Figurinha;
import org.example.model.FigurinhaModel;
import org.example.model.GeraMD5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FigurinhaRepository extends Repository {
    public void cadastrarFigurinha(Figurinha figurinha) {
        String sql = "INSERT INTO figurinha (nome, pagina, capa, tag, descricao) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, figurinha.getNome());
            stmt.setInt(2, figurinha.getPagina());
            stmt.setBytes(3, FigurinhaModel.converterImagem(figurinha.getCapa()));
            stmt.setString(4, FigurinhaModel.gerarHash(figurinha.getCapa()));
            stmt.setString(5, figurinha.getDescricao());

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public Boolean buscarFigurinhaPorTag(String tag) {
        try {
            String sql = "SELECT f.nome, f.pagina, f.capa, f.tag, f.descricao FROM figurinha f WHERE tag = ?";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, tag);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return false;
    }

    public Figurinha buscarFigurinhaPorTagSecundario(String tag) {
        Figurinha figurinha = new Figurinha();
        try {
            String sql = "SELECT f.nome, f.pagina, f.capa, f.tag, f.descricao FROM figurinha f WHERE tag = ?";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, tag);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                figurinha.setCapa(rs.getString("capa"));
                figurinha.setNome(rs.getString("nome"));
                figurinha.setDescricao(rs.getString("descricao"));
                figurinha.setTag(rs.getString("tag"));
                figurinha.setPagina(rs.getInt("pagina"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return figurinha;
    }

    public void visualizarImagemBanco() {
        String sql = "SELECT capa FROM figurinha"; // Adicione uma cláusula WHERE para selecionar uma imagem específica

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                byte[] imgBytes = rs.getBytes("capa");
                String outputFilePath = "output-image.jpg";

                try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                    fos.write(imgBytes);
                    System.out.println("Image has been saved to " + outputFilePath);
                } catch (IOException e) {
                    System.out.println("Error writing image to file: " + e.getMessage());
                }
            } else {
                System.out.println("No image found with the given id.");
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FigurinhaRepository repositorio = new FigurinhaRepository();
        Figurinha figurinha = new Figurinha();

        figurinha.setNome("Cristiano Ronaldo");
        figurinha.setDescricao("Foto tirada em 2024");
        figurinha.setPagina(1);
        figurinha.setCapa("/home/thaigo/IdeaProjects/albumFigurinhas/src/main/java/org/example/img/CR7Vasco.jpg");
        figurinha.setTag(figurinha.getCapa());

        repositorio.cadastrarFigurinha(figurinha);
//        System.out.println(repositorio.buscarFigurinhaPorTag("3f4989655f6b31a69a376e19a7382f7b"));
//        repositorio.visualizarImagemBanco();
    }
}