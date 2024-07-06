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
            usuarioRepository.inserirUsuario(usuario);

            return true;
        }

        return false;
    }

    public void editarUsuario(Usuario usuario) {
        usuarioRepository.editarUsuario(usuario);
    }

    public void excluirUsuario(Usuario usuario) {
        usuarioRepository.excluirUsuario(usuario);
    }

    public List<Usuario> todosUsuarios() {
        return usuarioRepository.todosUsuarios();
    }

    public Boolean verificaUsuario(String login, String senha) {
        return usuarioRepository.verificaUsuario(login, senha);
    }
}

