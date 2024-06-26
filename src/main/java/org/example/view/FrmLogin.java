package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmLogin extends JFrame {
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnOk;
    private JButton btnSair;

    public FrmLogin() {
        //CRIAÇÃO DA JANELA
        setTitle("Album de Figurinhas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 250);
        setLocationRelativeTo(null);

        //CRIAÇÃO DOS CAMPOS
        JLabel lblLogin = new JLabel("Login:");
        JLabel lblSenha = new JLabel("Senha:");
        txtLogin = new JTextField(30);
        txtSenha = new JPasswordField(30);
        txtLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
        txtSenha.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnOk = new JButton("Ok");
        btnSair = new JButton("Sair");

        //CRIAÇÃO DO LAYOUT
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        painel.add(lblLogin, constraints);

        constraints.gridx = 1;
        painel.add(txtLogin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        painel.add(lblSenha, constraints);

        constraints.gridx = 1;
        painel.add(txtSenha, constraints);

        JPanel btnPainel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPainel.add(btnOk);
        btnPainel.add(btnSair);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 5, 5, 5);
        painel.add(btnPainel, constraints);

        //ADICIONA PAINEL À JANELA
        add(painel);

        //CONFIGURAR BOTÕES
        btnOk.addActionListener(e -> recuperaLogin());
        btnSair.addActionListener(e -> sair());
    }

    //RESGATAR DADOS DE ENTRADA
    public String getLogin() {
        return txtLogin.getText();
    }

    public String getSenha() {
        return new String(txtSenha.getPassword());
    }

    // Métodos para botões de ação
    private void recuperaLogin() {
        String login = getLogin();
        String senha = getSenha();

        JOptionPane.showMessageDialog(this, "Login: " + login + "\nSenha: " + senha);
    }

    private void sair() {
        System.exit(0);
    }
}