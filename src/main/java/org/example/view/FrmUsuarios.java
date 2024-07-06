package org.example.view;

import org.example.repository.UsuarioRepository;
import org.example.entity.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class FrmUsuarios extends JFrame {
    private final UsuarioRepository usuarioRepository;
    private JTable tblUsuarios;
    private UsuarioTableModel tableModel;

    public FrmUsuarios() {
        usuarioRepository = new UsuarioRepository();

        setTitle("Gerenciamento de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        initComponents(panel, constraints);
        carregarUsuarios();
    }

    private void initComponents(JPanel panel, GridBagConstraints constraints) {
        JButton btnInserir = new JButton("+ Inserir");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.0;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(btnInserir, constraints);

        JButton btnExcluir = new JButton("- Excluir");
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(btnExcluir, constraints);

        JButton btnEditar = new JButton(" Editar ");
        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(btnEditar, constraints);

        JLabel lblFiltro = new JLabel("Filtrar por nome:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.0;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(lblFiltro, constraints);

        JTextField txtFiltro = new JTextField(20);
        txtFiltro.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtFiltro, constraints);

        JButton btnFiltrar = new JButton(" Filtrar ");
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.weightx = 0.0;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(btnFiltrar, constraints);

        tableModel = new UsuarioTableModel();
        tblUsuarios = new JTable(tableModel);
        tblUsuarios.setRowHeight(30);
        tblUsuarios.setDefaultRenderer(Object.class, new CustomCellRenderer());
        JScrollPane scrollPane = new JScrollPane(tblUsuarios);
        scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 2.0;
        constraints.weighty = 2.0;
        panel.add(scrollPane, constraints);

        addEventListeners(btnInserir, btnExcluir, btnEditar, txtFiltro, btnFiltrar);
    }

    private void addEventListeners(JButton btnInserir, JButton btnExcluir, JButton btnEditar, JTextField txtFiltro, JButton btnFiltrar) {
        btnInserir.addActionListener(e -> {
            dispose();
            FrmUsuario frmUsuario = new FrmUsuario();
            frmUsuario.setVisible(true);
        });

        btnExcluir.addActionListener(e -> excluirUsuario());

        btnEditar.addActionListener(e -> editarUsuario());

        btnFiltrar.addActionListener(e -> filtrarUsuarios(txtFiltro.getText()));
    }

    private void excluirUsuario() {
        int selectedRow = tblUsuarios.getSelectedRow();
        if (selectedRow >= 0) {
            Usuario usuarioSelecionado = tableModel.getUsuarioAt(selectedRow);
            usuarioRepository.excluirUsuario(usuarioSelecionado);
            carregarUsuarios();
        } else {
            JOptionPane.showMessageDialog(FrmUsuarios.this, "Selecione um usuário para excluir.");
        }
    }

    private void editarUsuario() {
        int selectedRow = tblUsuarios.getSelectedRow();
        if (selectedRow >= 0) {
            Usuario usuarioSelecionado = tableModel.getUsuarioAt(selectedRow);
            dispose();
            FrmUsuario frmUsuario = new FrmUsuario(usuarioSelecionado);
            frmUsuario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(FrmUsuarios.this, "Selecione um usuário para editar.");
        }
    }

    private void filtrarUsuarios(String filtro) {
        List<Usuario> usuariosFiltrados = usuarioRepository.usuarioPorNome(filtro);
        tableModel.setUsuarios(usuariosFiltrados);

        System.out.println(filtro);
        System.out.println(usuarioRepository.usuarioPorNome("admin"));
        System.out.println(usuariosFiltrados);
    }

    private void carregarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.todosUsuarios();
        tableModel.setUsuarios(usuarios);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmUsuarios frmUsuarios = new FrmUsuarios();
            frmUsuarios.setVisible(true);
        });
    }

    private static class UsuarioTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Nome", "Perfil"};
        private List<Usuario> usuarios;

        public void setUsuarios(List<Usuario> usuarios) {
            this.usuarios = usuarios;
            fireTableDataChanged();
        }

        public Usuario getUsuarioAt(int row) {
            return usuarios.get(row);
        }

        @Override
        public int getRowCount() {
            return (usuarios == null) ? 0 : usuarios.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Usuario usuario = usuarios.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return usuario.getLogin();
                case 1:
                    return usuario.getPerfil().name();
                default:
                    return null;
            }
        }
    }

    private static class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            }

            return component;
        }
    }

}
