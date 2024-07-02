package org.example.entity;

import java.util.List;

public class Album {
    private String nome;
    private Integer pagina;
    private String capa;
    private List<String> figurinhas;
    private String descricao;

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

    public List<String> getFigurinhas() {
        return figurinhas;
    }

    public void setFigurinhas(List<String> figurinhas) {
        this.figurinhas = figurinhas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Album{" +
                "nome='" + nome + '\'' +
                ", pagina=" + pagina +
                ", capa='" + capa + '\'' +
                ", figurinhas=" + figurinhas +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}