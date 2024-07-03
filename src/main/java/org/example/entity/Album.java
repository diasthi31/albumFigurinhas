package org.example.entity;

import java.util.List;

public class Album {
    private String nome;
    private Integer pagina;
    private String capa;
    private String figurinhas;

    public Album() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getFigurinhas() {
        return figurinhas;
    }

    public void setFigurinhas(String figurinhas) {
        this.figurinhas = figurinhas;
    }

    @Override
    public String toString() {
        return "Album{" +
                "nome='" + nome + '\'' +
                ", pagina=" + pagina +
                ", capa='" + capa + '\'' +
                ", figurinhas=" + figurinhas +
                '}';
    }
}