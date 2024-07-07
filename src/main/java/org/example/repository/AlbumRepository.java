package org.example.repository;

import org.example.entity.Album;
import org.example.entity.Figurinha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository extends Repository {

    public Boolean existeAlbum() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM album";
            conn = connect();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return false;
    }

    public Boolean editarAlbum(Album album) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE album SET nome = ?, paginas = ?, capa = ? WHERE nome = ?";
            conn = connect();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, album.getNome());
            stmt.setInt(2, album.getPagina());
            stmt.setString(3, album.getCapa());
            stmt.setString(4, album.getNome());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    public Boolean criarAlbum(Album album) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            if (!existeAlbum()) {
                String sql = "INSERT INTO album (nome, paginas, capa) VALUES (?, ?, ?)";
                conn = connect();
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, album.getNome());
                stmt.setInt(2, album.getPagina());
                stmt.setString(3, album.getCapa());
                stmt.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    public Album obterAlbum() {
        Album album = new Album();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT nome, paginas, capa FROM album WHERE id = 1";
            conn = connect();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                album.setNome(rs.getString(1));
                album.setPagina(rs.getInt(2));
                album.setCapa(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return album;
    }

    public Boolean adicionarFigurnha(Figurinha figurinha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            if (existeAlbum()) {
                String sql = "INSERT INTO album (figurinhas) VALUES (?)";
                conn = connect();
                stmt = conn.prepareStatement(sql);
                FigurinhaRepository repository = new FigurinhaRepository();
                if (repository.buscarFigurinhaPorTag(figurinha.getTag())) {
                    stmt.setString(1, figurinha.getTag());
                    stmt.executeUpdate();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    public Boolean editarFigurnha(Figurinha figurinha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            if (existeAlbum()) {
                String sql = "UPDATE album SET figurinhas = ? WHERE tag = ?";
                conn = connect();
                stmt = conn.prepareStatement(sql);
                FigurinhaRepository repository = new FigurinhaRepository();
                if (repository.buscarFigurinhaPorTag(figurinha.getTag())) {
                    stmt.setString(1, figurinha.getTag());
                    stmt.setString(2, figurinha.getTag());
                    stmt.executeUpdate();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    public List<Figurinha> buscarTodasFigurinhas() {
        List<Figurinha> figurinhas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT f.id, f.nome, f.pagina, f.capa, f.tag, f.descricao FROM album a, figurinha f WHERE a.id IN (SELECT fa.idAlbum FROM figurinha_album fa, album a, figurinha f WHERE fa.idFigurinha = f.id AND fa.idAlbum = a.id)";
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

    public List<Figurinha> buscarFigurinhaPorNome(String nome) {
        List<Figurinha> figurinhas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT f.nome, f.pagina, f.capa, f.tag, f.descricao FROM album a, figurinha f WHERE a.tag = f.tag AND nome LIKE %?%";
            conn = connect();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Figurinha figurinha = new Figurinha();
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

    public void removerFigurinha(Figurinha figurinha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            if (existeAlbum()) {
                String sql = "DELETE FROM album WHERE tag = ?";
                conn = connect();
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, figurinha.getTag());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public void removerFigurinhas() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            if (existeAlbum()) {
                String sql = "DELETE FROM album";
                conn = connect();
                stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    public List<Figurinha> buscarFigurinhaPorTag(String tag) {
        return new ArrayList<>();
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
