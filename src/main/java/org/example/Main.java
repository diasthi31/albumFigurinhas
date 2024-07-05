package org.example;

import org.example.repository.Repository;
import org.example.view.FrmLogin;
import org.example.view.FrmNovaFigurinha;
import org.example.view.FrmSobre;
import org.example.view.FrmSplash;
import org.example.view.FrmUsuarios;
import org.example.view.FrmUsuario;
import org.example.view.FrmAlbum;
import org.example.view.FrmAlbumCapa;
import org.example.view.FrmAutoria;
import org.example.view.FrmFigurinha;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Repository repository = new Repository();

        FrmSplash splash = new FrmSplash(5000);
        splash.mostrarSplash();

        FrmLogin loginView = new FrmLogin();
        loginView.setVisible(true);

        FrmUsuarios usuarios = new FrmUsuarios();
        usuarios.setVisible(true);

        FrmUsuario usuario = new FrmUsuario();
        usuario.setVisible(true);

        FrmAutoria album = new FrmAutoria();
        album.setVisible(true);

        FrmFigurinha figurinha = new FrmFigurinha();
        figurinha.setVisible(true);

        FrmAlbumCapa albumCapa = new FrmAlbumCapa();
        albumCapa.setVisible(true);

        FrmAlbum album2 = new FrmAlbum();
        album2.setVisible(true);

        FrmNovaFigurinha novaFigurinha = new FrmNovaFigurinha();
        novaFigurinha.setVisible(true);

        FrmSobre sobre = new FrmSobre();
        sobre.setVisible(true);
    }
}