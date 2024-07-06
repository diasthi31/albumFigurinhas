package org.example.repository;

import org.example.entity.Figurinha;
import org.example.model.FigurinhaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Figurinha_AlbumRepository extends Repository {

    public Boolean insereFigurinhaAlbum(Integer id) {
        String sql = "INSERT INTO figurinha_album (idFigurinha, idAlbum) VALUES (?, 1)";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);


            stmt.setInt(1, id);

            if (stmt.executeUpdate() > 0)
                return true;

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return false;
    }

    public List<Figurinha> buscaFigurinhas() {
        List<Figurinha> figurinhas = new ArrayList<>();

        String sql = "SELECT f.id, f.nome, f.pagina, f.capa, f.tag, f.descricao FROM figurinha f WHERE f.id IN (SELECT f1.idFigurinha FROM figurinha_album f1 WHERE f1.idAlbum = 1)";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Figurinha figurinha = new Figurinha();

                figurinha.setId(rs.getInt("id"));
                figurinha.setNome(rs.getString("nome"));
                figurinha.setPagina(rs.getInt("pagina"));
                figurinha.setCapa(rs.getString("capa"));
                figurinha.setTag(rs.getString("tag"));
                figurinha.setDescricao(rs.getString("descricao"));

                figurinhas.add(figurinha);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return figurinhas;
    }
}