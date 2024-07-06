package org.example.view;

import org.example.repository.UsuarioRepository;
import org.example.entity.Perfil;
import org.example.entity.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FrmUsuarios extends JFrame {
    private final UsuarioRepository usuarioRepository;

    private final JTextField txtLogin;
    private final JTextField txtSenha;
    private final JComboBox<Perfil> cbPerfil;
    private final JList<Usuario> lstUsuarios;
    private final DefaultListModel<Usuario> listModel;

    public FrmUsuarios() {
        usuarioRepository = new UsuarioRepository(); //INSTANCIANDO O REPOSITORY

        setTitle("Gerenciamento de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel lblLogin = new JLabel("Login:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lblLogin, constraints);

        txtLogin = new JTextField(20);
        constraints.gridx = 1;
        panel.add(txtLogin, constraints);

        JLabel lblSenha = new JLabel("Senha:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lblSenha, constraints);

        txtSenha = new JTextField(20);
        constraints.gridx = 1;
        panel.add(txtSenha, constraints);

        JLabel lblPerfil = new JLabel("Perfil:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lblPerfil, constraints);

        cbPerfil = new JComboBox<>(Perfil.values());
        constraints.gridx = 1;
        panel.add(cbPerfil, constraints);

        JButton btnInserir = new JButton("+ Inserir");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(btnInserir, constraints);

        JButton btnExcluir = new JButton("- Excluir");
        constraints.gridx = 1;
        panel.add(btnExcluir, constraints);

        JButton btnEditar = new JButton("Editar");
        constraints.gridx = 2;
        panel.add(btnEditar, constraints);

        JLabel lblFiltro = new JLabel("Filtrar por nome:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(lblFiltro, constraints);

        JTextField txtFiltro = new JTextField(20);
        constraints.gridx = 1;
        panel.add(txtFiltro, constraints);

        JButton btnFiltrar = new JButton("Filtrar");
        constraints.gridx = 2;
        panel.add(btnFiltrar, constraints);

        listModel = new DefaultListModel<>();
        lstUsuarios = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(lstUsuarios);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, constraints);

        //CARREGAR A LISTA DE USUÁRIOS
        carregarUsuarios();

        //EVENTO DO BOTÃO DE INSERIR
        btnInserir.addActionListener(e -> {
            String login = txtLogin.getText();
            String senha = txtSenha.getText();
            Perfil perfil = (Perfil) cbPerfil.getSelectedItem();

            Usuario usuario = new Usuario();
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setPerfil(perfil);

            usuarioRepository.inserirUsuario(usuario);

            carregarUsuarios(); //ATUALIZA A LISTA APÓS INSERIR
        });

        //EVENTO DO BOTÃO DE EXCLUIR
        btnExcluir.addActionListener(e -> {
            Usuario usuarioSelecionado = lstUsuarios.getSelectedValue();
            if (usuarioSelecionado != null) {
                usuarioRepository.excluirUsuario(usuarioSelecionado);

                carregarUsuarios(); //ATUALIZA A LISTA APÓS EXCLUSÃO
            } else {
                JOptionPane.showMessageDialog(FrmUsuarios.this, "Selecione um usuário para excluir.");
            }
        });

        //EVENTO DO BOTÃO DE EDITAR
        btnEditar.addActionListener(e -> {
            Usuario usuarioSelecionado = lstUsuarios.getSelectedValue();
            if (usuarioSelecionado != null) {
                String novoLogin = txtLogin.getText();
                String novaSenha = txtSenha.getText();
                Perfil novoPerfil = (Perfil) cbPerfil.getSelectedItem();

                usuarioSelecionado.setLogin(novoLogin);
                usuarioSelecionado.setSenha(novaSenha);
                usuarioSelecionado.setPerfil(novoPerfil);

                usuarioRepository.editarUsuario(usuarioSelecionado);

                carregarUsuarios(); //ATUALIZA A LISTA APÓS EDIÇÃO
            } else {
                JOptionPane.showMessageDialog(FrmUsuarios.this, "Selecione um usuário para editar.");
            }
        });

        //EVENTO DO BOTÃO DE FILTRAR
        btnFiltrar.addActionListener(e -> {
            String filtro = txtFiltro.getText();
            List<Usuario> usuariosFiltrados = usuarioRepository.usuarioPorNome(filtro);

            listModel.clear();

            for (Usuario usuario : usuariosFiltrados) {
                listModel.addElement(usuario);
            }
        });
    }

    //MÉTODO PARA CARREGAR A LISTA DE USUÁRIOS
    private void carregarUsuarios() {
        listModel.clear();
        List<Usuario> usuarios = usuarioRepository.todosUsuarios();

        for (Usuario usuario : usuarios) {
            listModel.addElement(usuario);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmUsuarios frmUsuarios = new FrmUsuarios();
            frmUsuarios.setVisible(true);
        });
    }
}