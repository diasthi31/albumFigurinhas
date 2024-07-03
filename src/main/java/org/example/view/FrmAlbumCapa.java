package org.example.view;

import org.example.controller.AlbumController;
import org.example.entity.Album;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAlbumCapa extends JFrame {

    private JButton btnTrocarSenha;
    private JButton btnAdquirirFigurinha;
    private JButton btnSobre;
    private JLabel lblInfo;

    private AlbumController albumController;

    public FrmAlbumCapa() {
        this.albumController = new AlbumController();

        setTitle("FrmAlbum - Página Capa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        layoutComponents();

        pack();
        setVisible(true);
    }

    private void initComponents() {
        btnTrocarSenha = new JButton("Trocar Senha");
        btnAdquirirFigurinha = new JButton("Adquirir Nova Figurinha");
        btnSobre = new JButton("Sobre");
        lblInfo = new JLabel();

        btnTrocarSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novaSenha = JOptionPane.showInputDialog(FrmAlbumCapa.this, "Digite a nova senha:");
                // Chamar método do controller para trocar senha
                // Exemplo: albumController.trocarSenha(novaSenha);
                lblInfo.setText("Senha trocada com sucesso para: " + novaSenha);
            }
        });

        btnAdquirirFigurinha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chamar método do controller para adquirir nova figurinha
                // Exemplo: albumController.adquirirNovaFigurinha();
                lblInfo.setText("Nova figurinha adquirida!");
            }
        });

        btnSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chamar método do controller para exibir informações sobre o álbum
                // Exemplo: String sobre = albumController.getSobre();
                lblInfo.setText("Informações sobre o álbum");
            }
        });
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(btnTrocarSenha);
        panel.add(btnAdquirirFigurinha);
        panel.add(btnSobre);
        panel.add(lblInfo);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmAlbumCapa();
            }
        });
    }
}
