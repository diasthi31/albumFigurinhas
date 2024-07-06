package org.example.view;

import org.example.controller.UsuarioController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmLogin extends JFrame {
    private final UsuarioController usuarioController = new UsuarioController();

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnOk;
    private JButton btnSair;

    private Boolean autenticado = false;

    public FrmLogin() {
        configuraJanela();
        iniciaComponentes();
        configuraLayout();
        configuraBotoes();
    }

    private void configuraJanela() {
        setTitle("Album de Figurinhas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);
    }

    private void iniciaComponentes() {
        txtLogin = new JTextField(30);
        txtSenha = new JPasswordField(30);
        txtLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
        txtSenha.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnOk = new JButton("Ok");
        btnSair = new JButton("Sair");
    }

    private void configuraLayout() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        painel.add(new JLabel("Login:"), constraints);

        constraints.gridx = 1;
        painel.add(txtLogin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        painel.add(new JLabel("Senha:"), constraints);

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

        add(painel);
    }

    private void configuraBotoes() {
        btnOk.addActionListener(e -> login());
        btnSair.addActionListener(e -> sair());
    }

    public String getLogin() {
        return txtLogin.getText();
    }

    public String getSenha() {
        return new String(txtSenha.getPassword());
    }

    public void msgErro() {
        JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
    }

    private void recuperaLogin() {
        String login = getLogin();
        String senha = getSenha();
    }

    public Boolean login() {
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());

        boolean existe = usuarioController.verificaUsuario(login, senha);
        if (existe) {
            autenticado = true;
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!");
            return false;
        }
    }

    private void sair() {
        System.exit(0);
    }

    public Boolean autenticado() {
        return autenticado;
    }
}