package org.example.controller;

import org.example.entity.Figurinha;
import org.example.repository.Figurinha_AlbumRepository;

import java.util.List;

public class Figurinha_AlbumController {
    Figurinha_AlbumRepository figurinha_AlbumRepository = new Figurinha_AlbumRepository();

    public Boolean insereFigurinhaAlbum(Integer id) {
        return figurinha_AlbumRepository.insereFigurinhaAlbum(id);
    }

    public List<Figurinha> buscaFigurinhas() {
        return figurinha_AlbumRepository.buscaFigurinhas();
    }
}