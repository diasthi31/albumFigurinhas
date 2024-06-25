package org.example.entity;

public enum Perfil {
    ADMINISTRADOR(1),
    AUTOR(2),
    COLECIONADOR(3);

    private final int valor;

    private Perfil(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static Perfil paraValor(int valor) {
        for (Perfil perfil : Perfil.values()) {
            if (perfil.getValor() == valor) {
                return perfil;
            }
        }

        throw new IllegalArgumentException("Perfil com valor: " + valor + " não encontrado.");
    }
}