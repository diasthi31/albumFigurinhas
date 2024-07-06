package org.example.controller;

import org.example.entity.Figurinha;
import org.example.repository.FigurinhaRepository;

import java.util.List;

public class FigurinhaController {

    private final FigurinhaRepository figurinhaRepository;

    public FigurinhaController() {
        this.figurinhaRepository = new FigurinhaRepository();
    }

    public void cadastrarFigurinha(Figurinha figurinha) {
        figurinhaRepository.cadastrarFigurinha(figurinha);
    }

    public Figurinha buscarFigurinhaPorTag(String tag) {
        return figurinhaRepository.buscarFigurinhaPorTagSecundario(tag);
    }

    public List<Figurinha> buscarTodas() {
        return figurinhaRepository.buscarTodas();
    }

    public Boolean removerFigurinha(int id) {
        return figurinhaRepository.removerFigurinha(id);
    }

    public Boolean atualizarFigurinha(Figurinha figurinha) {
        return figurinhaRepository.atualizarFigurinha(figurinha);
    }
}
