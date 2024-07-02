package org.example.controller;

import org.example.entity.Usuario;
import org.example.repository.UsuarioRepository;

import java.util.List;

public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public void inserirUsuario(Usuario usuario) {
        usuarioRepository.inserirUsuario(usuario);
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

