package org.example.controller;

import org.example.entity.Figurinha;
import org.example.repository.FigurinhaRepository;

public class FigurinhaController {

    private FigurinhaRepository figurinhaRepository;

    public FigurinhaController() {
        this.figurinhaRepository = new FigurinhaRepository();
    }

    public void cadastrarFigurinha(Figurinha figurinha) {
        figurinhaRepository.cadastrarFigurinha(figurinha);
    }

    public Figurinha buscarFigurinhaPorTagSecundario(String tag) {
        return figurinhaRepository.buscarFigurinhaPorTagSecundario(tag);
    }

    // Método para verificar se o usuário é colecionador
    public boolean isColecionador() {
        // Implemente a lógica para verificar se o usuário é colecionador
        return false; // Exemplo simples
    }

    // Método para permitir ou não a edição da figurinha baseada no perfil
    public boolean permiteEdicao(Figurinha figurinha) {
        if (isColecionador()) {
            // Se for colecionador, não permite edição
            return false;
        } else {
            // Outros perfis podem editar
            return true;
        }
    }
}
