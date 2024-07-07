package org.example.view;

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
        if (usuario == null) {
            usuario = new Usuario();
        }

        if (txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
        } else {
            usuario.setLogin(txtLogin.getText());
            usuario.setSenha(txtSenha.getText());
            usuario.setPerfil((Perfil) cbPerfil.getSelectedItem());

            UsuarioRepository usuarioRepository = new UsuarioRepository();

            if (!(usuarioRepository.existeUsuario(usuario.getLogin()))) {
                try {
                    usuarioRepository.inserirUsuario(usuario);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                dispose();

                FrmUsuarios frmUsuarios = new FrmUsuarios();
                frmUsuarios.setVisible(true);
            } else {
                usuarioRepository.editarUsuario(usuario);

                if (usuario.getPerfil().getValor() == 3) {
                    dispose();

                    FrmAlbum frmAlbum = new FrmAlbum();
                    frmAlbum.setVisible(true);
                } else {
                    dispose();

                    FrmUsuarios frmUsuarios = new FrmUsuarios();
                    frmUsuarios.setVisible(true);
                }
            }
        }
    }

    public void cancela() {
        dispose();

        if (usuario != null) {
            if (usuario.getPerfil() != null) {
                if (usuario.getPerfil().getValor() != 1) {
                    FrmAlbum frmAlbum = new FrmAlbum();
                    frmAlbum.setVisible(true);
                } else {
                    FrmUsuarios frmUsuarios = new FrmUsuarios();
                    frmUsuarios.setVisible(true);
                }
            } else {
                FrmUsuarios frmUsuarios = new FrmUsuarios();
                frmUsuarios.setVisible(true);
            }
        } else {
            FrmUsuarios frmUsuarios = new FrmUsuarios();
            frmUsuarios.setVisible(true);
        }
    }

    public void bloqueiaCampo() {
        lblPerfil.setVisible(false);
        cbPerfil.setVisible(false);
    }
}