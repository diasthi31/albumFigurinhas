package org.example.repository;

import org.example.entity.Figurinha;
import org.example.model.GeraMD5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FigurinhaRepository extends Repository {
    public void cadastrarFigurinha(Figurinha figurinha) {
        try {
            String sql = "INSERT INTO figurinha (nome, pagina, capa, tag, descricao) VALUES (?, ?, ?, ?, ?)";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, figurinha.getNome());
            stmt.setInt(2, figurinha.getPagina());
            stmt.setString(3, figurinha.getCapa());
//            stmt.setString(4, GeraMD5.gerar(figurinha.getCapa()));
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

//    public static void main(String[] args) {
//        FigurinhaRepository repositorio = new FigurinhaRepository();
//        Figurinha figurinha = new Figurinha();
//
//        figurinha.setNome("Cristiano Ronaldo");
//        figurinha.setDescricao("Foto tirada em 2024");
//        figurinha.setPagina(1);
//        figurinha.setCapa("/home/thaigo/IdeaProjects/albumFigurinhas/src/main/java/org/example/img/CR7Vasco.jpg");
//        figurinha.setTag(figurinha.getCapa());
//
//        repositorio.cadastrarFigurinha(figurinha);
//        System.out.println(repositorio.buscarFigurinhaPorTag("3f4989655f6b31a69a376e19a7382f7b"));
//    }
}