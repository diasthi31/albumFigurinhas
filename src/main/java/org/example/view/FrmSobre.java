package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmSobre extends JFrame {

    public FrmSobre() {
        setTitle("Sobre");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        layoutComponents();

        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Sobre o Aplicativo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lblTitulo, BorderLayout.NORTH);

        JTextArea txtAreaDescricao = new JTextArea();
        txtAreaDescricao.setEditable(false);
        txtAreaDescricao.setLineWrap(true);
        txtAreaDescricao.setWrapStyleWord(true);
        txtAreaDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAreaDescricao.setText("Este é um aplicativo de álbum de figurinhas.\n\nDesenvolvido por: [Seu Nome]\nVersão: 1.0\n\n© 2024. Todos os direitos reservados.");
        panel.add(new JScrollPane(txtAreaDescricao), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        panel.add(btnFechar, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private void layoutComponents() {
        // Define o layout da interface, se necessário
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmSobre());
    }
}
