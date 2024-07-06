package org.example.controller;

import org.example.entity.Album;
import org.example.entity.Figurinha;
import org.example.repository.AlbumRepository;

import java.util.List;

public class AlbumController {
    private AlbumRepository albumRepository;

    public AlbumController() {
        this.albumRepository = new AlbumRepository();
    }

    public Album obterAlbum() {
        return albumRepository.obterAlbum();
    }

    public List<Figurinha> buscarFigurinhas() {
        return albumRepository.buscarTodasFigurinhas();
    }

    public Boolean editarAlbum(Album album) {
        return albumRepository.editarAlbum(album);
    }

    public void removerTodasFigurinhas() {
        albumRepository.removerFigurinhas();
    }

    public List<Figurinha> filtrarFigurinhaPorTag(String tag) {
        return albumRepository.buscarFigurinhaPorTag(tag);
    }

    public void adicionarFigurinha(Figurinha figurinha) {
    }

    public void removerFigurinha(int id) {

    }

    public void atualizarFigurinha(Figurinha figurinha) {
    }

    public Figurinha buscarFigurinhaPorId(int i) {
        return new Figurinha();
    }
}
