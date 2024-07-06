package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmSobre extends JFrame {

    public FrmSobre() {
        setTitle("Sobre");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iniciarComponentes();

        pack();
        setSize(500, 260);
        setLocationRelativeTo(null);
    }

    private void iniciarComponentes() {
        JPanel panel = criaPainel();
        JLabel lblTitulo = criaTitulo();
        JTextArea txtAreaDescricao = criaDescricao();
        JButton btnFechar = criaBotaoFechar();

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtAreaDescricao), BorderLayout.CENTER);
        panel.add(btnFechar, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private JPanel criaPainel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return panel;
    }

    private JLabel criaTitulo() {
        JLabel lblTitulo = new JLabel("Sobre o Aplicativo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        return lblTitulo;
    }

    private JTextArea criaDescricao() {
        JTextArea txtAreaDescricao = new JTextArea();
        txtAreaDescricao.setEditable(false);
        txtAreaDescricao.setLineWrap(true);
        txtAreaDescricao.setWrapStyleWord(true);
        txtAreaDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAreaDescricao.setBorder(new EmptyBorder(10, 10, 10, 10));
        txtAreaDescricao.setText("""
                Aplicativo para visualizar e gerenciar um álbum de figurinhas.\


                Desenvolvido por: Gabriel Cantú, Luiz Felipe e Thiago Dias
                Versão: 1.0

                © 2024. Todos os direitos reservados.""");

        return txtAreaDescricao;
    }

    private JButton criaBotaoFechar() {
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> {
            dispose();

            FrmAlbum frmAlbum = new FrmAlbum();
            frmAlbum.setVisible(true);
        });
        return btnFechar;
    }
}