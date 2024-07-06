package org.example.view;

import org.example.controller.UsuarioController;
import org.example.entity.Perfil;
import org.example.entity.Usuario;
import org.example.repository.UsuarioRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmUsuario extends JFrame {
    private JTextField txtLogin;
    private JTextField txtSenha;
    private JLabel lblPerfil;
    private JComboBox<Perfil> cbPerfil;
    private Usuario usuario;

    public FrmUsuario() {
        this(null);
    }

    public FrmUsuario(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Cadastro/Edição de Usuários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        getContentPane().add(painel, BorderLayout.CENTER);
        painel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        criaCamposFormulario(painel, constraints);
        criaBotoes(painel, constraints);

        if (usuario != null) {
            preencheCampos(usuario);
        }
    }

    private void criaCamposFormulario(JPanel painel, GridBagConstraints constraints) {
        JLabel lblLogin = new JLabel("Login:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painel.add(lblLogin, constraints);

        txtLogin = new JTextField(40);
        txtLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        painel.add(txtLogin, constraints);

        JLabel lblSenha = new JLabel("Senha:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painel.add(lblSenha, constraints);

        txtSenha = new JTextField(40);
        txtSenha.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        painel.add(txtSenha, constraints);

        lblPerfil = new JLabel("Perfil:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painel.add(lblPerfil, constraints);

        cbPerfil = new JComboBox<>(Perfil.values());
        constraints.gridx = 1;
        painel.add(cbPerfil, constraints);
    }

    private void criaBotoes(JPanel painel, GridBagConstraints constraints) {
        JButton btnOk = new JButton("Ok");
        JButton btncancela = new JButton("Cancelar");

        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnOk);
        pnlButtons.add(btncancela);

        JPanel btnPainel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPainel.add(btnOk);
        btnPainel.add(btncancela);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 5, 5, 5);
        painel.add(btnPainel, constraints);

        btnOk.addActionListener(e -> salvaUsuario());
        btncancela.addActionListener(e -> cancela());
    }

    private void preencheCampos(Usuario usuario) {
        txtLogin.setText(usuario.getLogin());
        txtSenha.setText(usuario.getSenha());
        cbPerfil.setSelectedItem(usuario.getPerfil());
    }

    private void salvaUsuario() {
        String login = txtLogin.getText();
        String senha = txtSenha.getText();
        Perfil perfil = (Perfil) cbPerfil.getSelectedItem();

        if (login == null || login.isEmpty() || senha == null || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Login e senha são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (usuario == null) {
            usuario = new Usuario(login, senha, perfil);
            UsuarioController usuarioController = new UsuarioController();
            boolean usuarioInserido = usuarioController.inserirUsuario(usuario);
            if (!usuarioInserido) {
                JOptionPane.showMessageDialog(this, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setPerfil(perfil);
            UsuarioController usuarioController = new UsuarioController();
            usuarioController.editarUsuario(usuario);
        }

        JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
        dispose();

        if (usuario.getPerfil().getValor() != 1) {
            FrmAlbum frmAlbum = new FrmAlbum();
            frmAlbum.setVisible(true);
        } else {
            FrmUsuarios frmUsuarios = new FrmUsuarios();
            frmUsuarios.setVisible(true);
        }
    }


    public void cancela() {
        dispose();

        if (usuario.getPerfil().getValor() != 1) {
            FrmAlbum frmAlbum = new FrmAlbum();
            frmAlbum.setVisible(true);
        } else {
            FrmUsuarios frmUsuarios = new FrmUsuarios();
            frmUsuarios.setVisible(true);
        }
    }

    public static void main(String[] args) {
        FrmUsuario frmUsuario = new FrmUsuario();
        frmUsuario.setVisible(true);
    }

    public void bloqueiaCampo() {
        lblPerfil.setVisible(false);
        cbPerfil.setVisible(false);
    }
}