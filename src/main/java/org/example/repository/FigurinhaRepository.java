package org.example.repository;

import org.example.entity.Figurinha;
import org.example.model.FigurinhaModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
            String sql = "SELECT f.id, f.nome, f.pagina, f.capa, f.tag, f.descricao FROM figurinha f WHERE tag = ?";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, tag);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                figurinha.setId(rs.getInt("id"));
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

    public ImageIcon visualizarImagemBanco(Integer id) {
        String sql = "SELECT capa FROM figurinha WHERE id = ? ";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                byte[] imgBytes = rs.getBytes("capa");

                ImageIcon imagemIcon = new ImageIcon(imgBytes);

                Image imagem = imagemIcon.getImage();
                Image novaImagem = imagem.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

                return new ImageIcon(novaImagem);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Figurinha> buscarTodas() {
        List<Figurinha> figurinhas = new ArrayList<>();

        try {
            String sql = "SELECT f.id, f.nome, f.pagina, f.capa, f.tag, f.descricao FROM figurinha f ";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Figurinha figurinha = new Figurinha();

                figurinha.setId(rs.getInt("id"));
                figurinha.setCapa(rs.getString("capa"));
                figurinha.setNome(rs.getString("nome"));
                figurinha.setDescricao(rs.getString("descricao"));
                figurinha.setTag(rs.getString("tag"));
                figurinha.setPagina(rs.getInt("pagina"));

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

    public Boolean removerFigurinha(int id) {
        String sql = "DELETE FROM figurinha WHERE id = ? ";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            return true;
        } catch (Exception e) {
            e.getStackTrace();
        }

        return false;
    }

    public Boolean atualizarFigurinha(Figurinha figurinha) {
        try {
            String sql = "UPDATE figurinha SET nome = ?, pagina = ? WHERE nome = ?";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, figurinha.getNome());
            stmt.setInt(2, figurinha.getPagina());
            stmt.setString(3, figurinha.getNome());

            stmt.executeUpdate();

            stmt.close();
            disconnect();

            return true;
        } catch (Exception e) {
            e.getStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        FigurinhaRepository figurinhaRepository = new FigurinhaRepository();

        Figurinha figurinha = new Figurinha();
        figurinha.setNome("Mbappé");
        figurinha.setPagina(2);
        figurinha.setCapa("/home/thaigo/Downloads/mbappe.jpeg");
        figurinha.setDescricao("Copa");
        figurinhaRepository.cadastrarFigurinha(figurinha);
    }
}