package org.example.repository;

import org.example.entity.Perfil;
import org.example.entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository extends Repository {
    public void inserirUsuario(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuario (login, senha, perfil) VALUES (?,?,?)";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setInt(3, usuario.getPerfil().getValor());

            stmt.executeUpdate();

            stmt.close();
            disconnect();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void editarUsuario(Usuario usuario) {
        try {
            String sql = "UPDATE usuario SET login = ?, senha = ?, perfil = ? WHERE login = ?";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setInt(3, usuario.getPerfil().getValor());
            stmt.setString(4, usuario.getLogin());

            stmt.executeUpdate();

            stmt.close();
            disconnect();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void excluirUsuario(Usuario usuario) {
        try {
            String sql = "DELETE FROM usuario WHERE login = ?";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getLogin());

            stmt.executeUpdate();

            stmt.close();
            disconnect();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List<Usuario> todosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT u.login, u.perfil FROM usuario u ORDER BY u.perfil, u.login";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();

                u.setLogin(rs.getString("login"));

                if (rs.getInt("perfil") == 1) {
                    u.setPerfil(Perfil.ADMINISTRADOR);
                } else if (rs.getInt("perfil") == 2) {
                    u.setPerfil(Perfil.AUTOR);
                } else if (rs.getInt("perfil") == 3) {
                    u.setPerfil(Perfil.COLECIONADOR);
                }

                usuarios.add(u);
            }

            rs.close();
            stmt.close();
            disconnect();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return usuarios;
    }

    public List<Usuario> usuarioPorTipo(Integer perfil) {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT u.login, u.perfil FROM usuario u WHERE u.perfil = ? ORDER BY u.perfil, u.login";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, perfil);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();

                u.setLogin(rs.getString("login"));

                if (rs.getInt("perfil") == 1) {
                    u.setPerfil(Perfil.ADMINISTRADOR);
                } else if (rs.getInt("perfil") == 2) {
                    u.setPerfil(Perfil.AUTOR);
                } else if (rs.getInt("perfil") == 3) {
                    u.setPerfil(Perfil.COLECIONADOR);
                }

                usuarios.add(u);
            }

            rs.close();
            stmt.close();
            disconnect();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return usuarios;
    }

    public List<Usuario> usuarioPorNome(String nome) {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT u.login, u.perfil FROM usuario u WHERE u.nome LIKE %?% ORDER BY u.perfil, u.login";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();

                u.setLogin(rs.getString("login"));

                if (rs.getInt("perfil") == 1) {
                    u.setPerfil(Perfil.ADMINISTRADOR);
                } else if (rs.getInt("perfil") == 2) {
                    u.setPerfil(Perfil.AUTOR);
                } else if (rs.getInt("perfil") == 3) {
                    u.setPerfil(Perfil.COLECIONADOR);
                }

                usuarios.add(u);
            }

            rs.close();
            stmt.close();
            disconnect();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return usuarios;
    }

    public Boolean verificaUsuario(String login, String senha) {
        try {
            String sql = "SELECT u.login, u.senha FROM usuario u WHERE u.login = ? AND u.senha = ?";

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

            stmt.close();
            disconnect();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        UsuarioRepository repositorio = new UsuarioRepository();

//        repositorio.inserirUsuario();
//        repositorio.editarUsuario();
//        repositorio.todosUsuarios();
//        repositorio.excluirUsuario();
//        repositorio.verificaUsuario();
//        repositorio.usuarioPorNome();
//        repositorio.usuarioPorTipo();
    }
}