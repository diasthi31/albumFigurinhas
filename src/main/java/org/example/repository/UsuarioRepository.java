package org.example.repository;

import org.example.entity.Perfil;
import org.example.entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository extends Repository {

    public boolean inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (login, senha, perfil) VALUES (?,?,?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setString(1, usuario.getLogin());
             stmt.setString(2, usuario.getSenha());
             stmt.setInt(3, usuario.getPerfil().getValor());

             stmt.executeUpdate();
             return true;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return false;
    }

    public void editarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET login = ?, senha = ?, perfil = ? WHERE login = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setInt(3, usuario.getPerfil().getValor());
            stmt.setString(4, usuario.getLogin());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirUsuario(String usuario) {
        String sql = "DELETE FROM usuario WHERE login = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> todosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.login, u.senha, u.perfil FROM usuario u ORDER BY u.perfil, u.login";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));

                switch (rs.getInt("perfil")) {
                    case 1 -> u.setPerfil(Perfil.ADMINISTRADOR);
                    case 2 -> u.setPerfil(Perfil.AUTOR);
                    case 3 -> u.setPerfil(Perfil.COLECIONADOR);
                }

                usuarios.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public List<Usuario> usuarioPorNome(String nome) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.login, u.perfil FROM usuario u WHERE u.login LIKE ? ORDER BY u.perfil, u.login";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setLogin(rs.getString("login"));

                    switch (rs.getInt("perfil")) {
                        case 1 -> u.setPerfil(Perfil.ADMINISTRADOR);
                        case 2 -> u.setPerfil(Perfil.AUTOR);
                        case 3 -> u.setPerfil(Perfil.COLECIONADOR);
                    }

                    usuarios.add(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Boolean verificaUsuario(String login, String senha) {
        String sql = "SELECT u.login, u.senha FROM usuario u WHERE u.login = ? AND u.senha = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Integer verificaTipoUsuario(String login) {
        String sql = "SELECT u.perfil FROM usuario u WHERE u.login = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("perfil");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Boolean existeUsuario(String login) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE login = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}