package org.example.view;

import org.example.controller.UsuarioController;
import org.example.entity.Perfil;
import org.example.entity.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmUsuario extends JFrame {
    private UsuarioController usuarioController;

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JComboBox<Perfil> cmbPerfil;
    private JButton btnSalvar;
    private JButton btnExcluir;
    private JList<Usuario> lstUsuarios;

    public FrmUsuario() {
        usuarioController = new UsuarioController();

        setTitle("Gerenciamento de Usuários");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pnlForm = new JPanel(new GridLayout(4, 2));

        pnlForm.add(new JLabel("Login:"));
        txtLogin = new JTextField();
        pnlForm.add(txtLogin);

        pnlForm.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        pnlForm.add(txtSenha);

        pnlForm.add(new JLabel("Perfil:"));
        cmbPerfil = new JComboBox<>(Perfil.values());
        pnlForm.add(cmbPerfil);

        btnSalvar = new JButton("Salvar");
        btnExcluir = new JButton("Excluir");

        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnSalvar);
        pnlButtons.add(btnExcluir);

        lstUsuarios = new JList<>();
        atualizarListaUsuarios();

        add(pnlForm, BorderLayout.NORTH);
        add(new JScrollPane(lstUsuarios), BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario();
                usuario.setLogin(txtLogin.getText());
                usuario.setSenha(new String(txtSenha.getPassword()));
                usuario.setPerfil((Perfil) cmbPerfil.getSelectedItem());

                if (!txtLogin.getText().isEmpty() && !txtSenha.getText().isEmpty()) {
                    usuarioController.inserirUsuario(usuario);
                    atualizarListaUsuarios();
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = lstUsuarios.getSelectedValue();
                if (usuario != null) {
                    usuarioController.excluirUsuario(usuario);
                    atualizarListaUsuarios();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para excluir.");
                }
            }
        });

        lstUsuarios.addListSelectionListener(e -> {
            Usuario usuario = lstUsuarios.getSelectedValue();
            if (usuario != null) {
                txtLogin.setText(usuario.getLogin());
                txtSenha.setText(usuario.getSenha());
                cmbPerfil.setSelectedItem(usuario.getPerfil());
            }
        });

        setVisible(true);
    }

    private void atualizarListaUsuarios() {
        List<Usuario> usuarios = usuarioController.todosUsuarios();
        lstUsuarios.setListData(usuarios.toArray(new Usuario[0]));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmUsuario());
    }
}
