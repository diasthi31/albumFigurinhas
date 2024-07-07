package org.example.repository;

import org.example.entity.Figurinha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Figurinha_AlbumRepository extends Repository {

    public Boolean insereFigurinhaAlbum(Integer id) {
        String sql = "INSERT INTO figurinha_album (idFigurinha, idAlbum) VALUES (?, 1)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = connect();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    public List<Figurinha> buscaFigurinhas() {
        List<Figurinha> figurinhas = new ArrayList<>();
        String sql = "SELECT f.id, f.nome, f.pagina, f.capa, f.tag, f.descricao FROM figurinha f, album a, figurinha_album fa WHERE f.id = fa.idFigurinha AND a.id = fa.idAlbum";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = connect();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Figurinha figurinha = new Figurinha();
                figurinha.setId(rs.getInt("id"));
                figurinha.setNome(rs.getString("nome"));
                figurinha.setPagina(rs.getInt("pagina"));
                figurinha.setCapa(rs.getString("capa"));
                figurinha.setTag(rs.getString("tag"));
                figurinha.setDescricao(rs.getString("descricao"));
                figurinhas.add(figurinha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return figurinhas;
    }

    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
