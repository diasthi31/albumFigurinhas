package org.example;

import org.example.controller.UsuarioController;
import org.example.repository.Repository;
import org.example.view.FrmLogin;
import org.example.view.FrmSplash;
import org.example.view.FrmUsuarios;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Repository repository = new Repository();
        UsuarioController usuarioController = new UsuarioController();

        FrmSplash splash = new FrmSplash(5000);
        splash.mostrarSplash();

        FrmLogin loginView = new FrmLogin();
        loginView.setVisible(true);

        while (!loginView.autenticado()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }

        loginView.dispose();

        SwingUtilities.invokeLater(() -> {
            FrmUsuarios frmUsuarios = new FrmUsuarios();
            frmUsuarios.setVisible(true);
        });

//        do {
//
//            if (loginView.login()) {
//                loginView.setVisible(false);
//
//            } else {
//                loginView.setVisible(true);
//            }
//        } while (!loginView.login());



//
//        FrmUsuario usuario = new FrmUsuario();
//        usuario.setVisible(true);
//
//        FrmAutoria album = new FrmAutoria();
//        album.setVisible(true);
//
//        FrmFigurinha figurinha = new FrmFigurinha();
//        figurinha.setVisible(true);
//
//        FrmAlbumCapa albumCapa = new FrmAlbumCapa();
//        albumCapa.setVisible(true);
//
//        FrmAlbum album2 = new FrmAlbum();
//        album2.setVisible(true);
//
//        FrmNovaFigurinha novaFigurinha = new FrmNovaFigurinha();
//        novaFigurinha.setVisible(true);
//
//        FrmSobre sobre = new FrmSobre();
//        sobre.setVisible(true);
    }
}