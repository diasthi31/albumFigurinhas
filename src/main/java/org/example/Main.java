package org.example;

import org.example.view.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FrmSplash splash = new FrmSplash(5000);
        splash.mostraSplash();

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

        if (loginView.tipo().equals(1)) {
            FrmUsuarios frmUsuarios = new FrmUsuarios();
            frmUsuarios.setVisible(true);
        } else if (loginView.tipo().equals(2)) {
            FrmAutoria frmAutoria = new FrmAutoria();
            frmAutoria.setVisible(true);
        } else if (loginView.tipo().equals(3)) {
            FrmAlbum frmAlbum = new FrmAlbum();
            frmAlbum.setVisible(true);
        }
    }
}