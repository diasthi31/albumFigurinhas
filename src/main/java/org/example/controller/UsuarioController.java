package org.example.controller;

import org.example.entity.Usuario;
import org.example.repository.UsuarioRepository;

import java.util.List;

public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioController() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public Boolean inserirUsuario(Usuario usuario) {
        Boolean existe = usuarioRepository.verificaUsuario(usuario.getLogin(), usuario.getSenha());

        if (!existe) {
            try {
                usuarioRepository.inserirUsuario(usuario);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public void editarUsuario(Usuario usuario) {
        usuarioRepository.editarUsuario(usuario);
    }

    public void excluirUsuario(Usuario usuario) {
        usuarioRepository.excluirUsuario(usuario.getLogin());
    }

    public List<Usuario> todosUsuarios() {
        return usuarioRepository.todosUsuarios();
    }

    public Boolean verificaUsuario(String login, String senha) {
        return usuarioRepository.verificaUsuario(login, senha);
    }

    public Integer verificaTipo(String login) {
        return usuarioRepository.verificaTipoUsuario(login);
    }
}

