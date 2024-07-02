package org.example.controller;

import org.example.entity.Album;
import org.example.repository.AlbumRepository;

public class AlbumController {
    private AlbumRepository albumRepository;

    public AlbumController() {
        this.albumRepository = new AlbumRepository();
    }

    public Album obterAlbum() {
        return albumRepository.obterAlbum();
    }

    public void atualizarAlbum(Album album) {
        albumRepository.atualizarAlbum(album);
    }

    public void removerTodasFigurinhas() {
        albumRepository.removerFigurinhas();
    }
}
