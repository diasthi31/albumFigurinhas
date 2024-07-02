package org.example;

import org.example.repository.Repository;
import org.example.view.FrmLogin;
import org.example.view.FrmSplash;
import org.example.view.FrmUsuarios;
import org.example.view.FrmUsuario;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Repository repository = new Repository();

        FrmSplash splash = new FrmSplash(5000);
        splash.mostrarSplash();

        SwingUtilities.invokeLater(() -> {
            FrmLogin loginView = new FrmLogin();
            loginView.setVisible(true);
        });

        FrmUsuarios usuarios = new FrmUsuarios();
        usuarios.setVisible(true);

        FrmUsuario usuario = new FrmUsuario();
        usuario.setVisible(true);
    }
}