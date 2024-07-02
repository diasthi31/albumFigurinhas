package org.example.repository;

import org.example.entity.Album;
import org.example.entity.Figurinha;
import org.example.model.GeraMD5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository extends Repository{
    public Boolean existeAlbum() {
        try {
            String sql = "SELECT COUNT(*) FROM album";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
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

    public Boolean criarAlbum(Album album) {
        try {
            if (!existeAlbum()) {
                String sql = "INSERT INTO album (nome, pagina, capa, descricao) VALUES (?, ?, ?, ?)";

                Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, album.getNome());
                stmt.setInt(2, album.getPagina());
                stmt.setString(3, album.getCapa());
                stmt.setString(4, album.getDescricao());

                stmt.executeUpdate();

                stmt.close();
                conn.close();

                return true;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return false;
    }

    public Boolean adicionarFigurnha(Figurinha figurinha) {
        try {
            if (existeAlbum()) {
                String sql = "INSERT INTO album (figurinha) VALUES (?)";

                Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement(sql);

                FigurinhaRepository repository = new FigurinhaRepository();

                if (repository.buscarFigurinhaPorTag(figurinha.getTag())) {
                    stmt.setString(1, figurinha.getTag());

                    stmt.executeUpdate();

                    return true;
                }

                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return false;
    }

    public Boolean editarFigurnha(Figurinha figurinha) {
        try {
            if (existeAlbum()) {
                String sql = "UPDATE album SET figurinha = ? WHERE tag = ?";

                Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement(sql);

                FigurinhaRepository repository = new FigurinhaRepository();

                if (repository.buscarFigurinhaPorTag(figurinha.getTag())) {
                    stmt.setString(1, figurinha.getTag());
                    stmt.setString(2, figurinha.getTag());

                    stmt.executeUpdate();

                    return true;
                }

                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return false;
    }

    public List<Figurinha> buscarFigurinhaPorNome(String nome) {
        List<Figurinha> figurinhas = new ArrayList<>();

        try {
            String sql = "SELECT f.nome, f.pagina, f.capa, f.tag, f.descricao FROM album a, figurinha f WHERE a.tag = f.tag AND nome LIKE %?%";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Figurinha figurinha = new Figurinha();

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

    public void removerFigurinha(Figurinha figurinha) {
        try {
            if (existeAlbum()) {
                String sql = "DELETE FROM album WHERE tag = ?";

                Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, figurinha.getTag());
                stmt.executeUpdate();

                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void removerFigurinhas() {
        try {
            if (existeAlbum()) {
                String sql = "DELETE FROM album";

                Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.executeUpdate();

                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
//        AlbumRepository repositorio = new AlbumRepository();
//        Album album = new Album();
//        Figurinha figurinha = new Figurinha();
//        GeraMD5 hash = new GeraMD5();
//
//        figurinha.setNome("Cristiano Ronaldo");
//        figurinha.setDescricao("Foto tirada em 2024");
//        figurinha.setPagina(1);
//        figurinha.setCapa("/home/thaigo/IdeaProjects/albumFigurinhas/src/main/java/org/example/img/CR7Vasco.jpg");
//        figurinha.setTag(figurinha.getCapa());
//
//        album.setNome("Álbum da Copa 2024");
//        album.setCapa("");
//        album.setDescricao("Álbum da Copa América USA 2024");
//        album.setPagina(12);
//        album.setFigurinhas(figurinha.getTag());
//
//        repositorio.criarAlbum(album);
//        repositorio.adicionarFigurnha(figurinha);
//        repositorio.editarFigurnha(figurinha);
//        repositorio.buscarFigurinhaPorNome("Ronaldo");
//        repositorio.removerFigurinha(figurinha);
//        repositorio.removerFigurinhas();
    }
}