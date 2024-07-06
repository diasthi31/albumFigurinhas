package org.example.entity;

public class Usuario {
    private String login;
    private String senha;
    private Perfil perfil;

    public Usuario() {}

    public Usuario(String login, String senha, Perfil perfil) {
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}