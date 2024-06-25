package org.example;

import org.example.repository.Repository;
import org.example.view.FrmSplash;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Repository repository = new Repository();

        FrmSplash splash = new FrmSplash(5000);
        splash.mostrarSplash();

        //INICIA A APLICAÇÃO PRINCIPAL
        JFrame frame = new JFrame("Albúm de Figurinhas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}