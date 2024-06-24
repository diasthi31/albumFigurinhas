package org.example.entity;

public class Figurinha {
    private String nome;
    private Integer pagina;
    private String capa;
    private String tag;
    private String descricao;

    public Figurinha() {}

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Figurinha{" +
                "nome='" + nome + '\'' +
                ", pagina=" + pagina +
                ", capa='" + capa + '\'' +
                ", tag='" + tag + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}