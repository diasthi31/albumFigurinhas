package org.example.view;

import org.example.entity.Perfil;
import org.example.entity.Usuario;
import org.example.repository.UsuarioRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmUsuario extends JFrame {
    private final JTextField txtLogin;
    private final JTextField txtSenha;
    private final JComboBox<Perfil> cbPerfil;
    private Usuario usuario;

    public FrmUsuario() {
        this(null); // Chama o construtor com usuário nulo
    }

    public FrmUsuario(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Cadastro/Edição de Usuários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblLogin = new JLabel("Login:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lblLogin, constraints);

        txtLogin = new JTextField(40);
        txtLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        panel.add(txtLogin, constraints);

        JLabel lblSenha = new JLabel("Senha:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lblSenha, constraints);

        txtSenha = new JTextField(40);
        txtSenha.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        panel.add(txtSenha, constraints);

        JLabel lblPerfil = new JLabel("Perfil:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lblPerfil, constraints);

        cbPerfil = new JComboBox<>(Perfil.values());
        constraints.gridx = 1;
        panel.add(cbPerfil, constraints);

        JButton btnOk = new JButton("Ok");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnOk);
        pnlButtons.add(btnCancelar);

        JPanel btnPainel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPainel.add(btnOk);
        btnPainel.add(btnCancelar);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 5, 5, 5);
        panel.add(btnPainel, constraints);

        if (usuario != null) {
            preencherCampos(usuario);
        }

        btnOk.addActionListener(e -> salvarUsuario());
        btnCancelar.addActionListener(e -> cancelar());
    }

    private void preencherCampos(Usuario usuario) {
        txtLogin.setText(usuario.getLogin());
        txtSenha.setText(usuario.getSenha());
        cbPerfil.setSelectedItem(usuario.getPerfil());
    }

    private void salvarUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }

        usuario.setLogin(txtLogin.getText());
        usuario.setSenha(txtSenha.getText());
        usuario.setPerfil((Perfil) cbPerfil.getSelectedItem());

        // Salvar no repositório
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        if (usuario.getLogin() == null) {
            usuarioRepository.inserirUsuario(usuario);
        } else {
            usuarioRepository.editarUsuario(usuario);
        }

        dispose();

        FrmUsuarios frmUsuarios = new FrmUsuarios();
        frmUsuarios.setVisible(true);
    }

    public void cancelar() {
        dispose();

        FrmUsuarios frmUsuarios = new FrmUsuarios();
        frmUsuarios.setVisible(true);
    }
}