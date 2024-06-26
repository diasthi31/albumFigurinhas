package org.example.view;

import javax.swing.*;
import java.awt.*;

public class FrmLogin extends JFrame {
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnCancelar;

    public FrmLogin() {
        //CRIAÇÃO DA JANELA
        setTitle("Album de Figurinhas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        //CRIAÇÃO DOS CAMPOS
        JLabel lblLogin = new JLabel("Login:");
        JLabel lblSenha = new JLabel("Senha:");
        txtLogin = new JTextField(50);
        txtSenha = new JPasswordField(20);
        btnLogin = new JButton("Login");
        btnCancelar = new JButton("Cancelar");

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

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        painel.add(btnLogin, constraints);

        constraints.gridy = 3;
        painel.add(btnCancelar, constraints);

        // Adiciona o painel à janela
        add(painel);

        // Configuração dos botões
//        btnLogin.addActionListener(e -> login());
//        btnCancelar.addActionListener(e -> cancel());
    }

    // Métodos de acesso aos campos
    public String getUsername() {
        return txtLogin.getText();
    }

//    public String getPassword() {
//        return new String(txtLogin.getPassword());
//    }

    // Métodos para botões de ação
//    private void login() {
//        String username = getUsername();
//        String password = getPassword();
//        // Aqui você pode adicionar a lógica de autenticação
//        JOptionPane.showMessageDialog(this, "Login: " + username + "\nPassword: " + password);
//    }

    private void cancel() {
        txtLogin.setText("");
        txtSenha.setText("");
    }
}