package org.example.view;

import org.example.controller.AlbumController;
import org.example.controller.FigurinhaController;
import org.example.entity.Album;
import org.example.entity.Figurinha;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

public class FrmAutoria extends JFrame {
    private final AlbumController albumController;
    private final JTextField txtNomeAlbum;
    private final JTextField txtPaginas;
    private final JTextField txtCapa;
    private final DefaultTableModel tableModel;

    public FrmAutoria() {
        albumController = new AlbumController();

        setTitle("FrmAutoria");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel pnlForm = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        pnlForm.add(new JLabel("Nome:"), constraints);

        txtNomeAlbum = new JTextField(30);
        txtNomeAlbum.setMargin(new Insets(5, 5, 5, 5));
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        pnlForm.add(txtNomeAlbum, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        pnlForm.add(new JLabel("Páginas:"), constraints);

        txtPaginas = new JTextField(10);
        txtPaginas.setMargin(new Insets(5, 5, 5, 5));
        constraints.gridx = 1;
        constraints.gridy = 1;
        pnlForm.add(txtPaginas, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        pnlForm.add(new JLabel("Capa:"), constraints);

        txtCapa = new JTextField(30);
        txtCapa.setMargin(new Insets(5, 5, 5, 5));
        constraints.gridx = 1;
        constraints.gridy = 2;
        pnlForm.add(txtCapa, constraints);

        JButton btnEscolherCapa = new JButton("...");
        constraints.gridx = 2;
        constraints.gridy = 2;
        pnlForm.add(btnEscolherCapa, constraints);

        JButton btnSalvarAlteracao = new JButton("Salvar Alterações");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        pnlForm.add(btnSalvarAlteracao, constraints);

        add(pnlForm, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"#", "Nome", "Página"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel pnlFigurinhas = new JPanel(new BorderLayout());
        pnlFigurinhas.add(scrollPane, BorderLayout.CENTER);

        JPanel pnlFigurinhaButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdicionar = new JButton("+");
        JButton btnEditar = new JButton("E");
        JButton btnRemover = new JButton("-");
        JButton btnFiltrar = new JButton("Filtrar");

        pnlFigurinhaButtons.add(btnAdicionar);
        pnlFigurinhaButtons.add(btnEditar);
        pnlFigurinhaButtons.add(btnRemover);
        pnlFigurinhaButtons.add(btnFiltrar);

        pnlFigurinhas.add(pnlFigurinhaButtons, BorderLayout.NORTH);

        add(pnlFigurinhas, BorderLayout.CENTER);

        carregarAlbum();
        carregarFigurinhas();

        btnEscolherCapa.addActionListener(e -> escolherCapa());
        btnSalvarAlteracao.addActionListener(e -> salvarAlteracao(new Album()));
        btnAdicionar.addActionListener(e -> adicionarFigurinha());
        btnEditar.addActionListener(e -> editarFigurinha(table));
        btnRemover.addActionListener(e -> removerFigurinha(table));
        btnFiltrar.addActionListener(e -> abrirJanelaFiltro());

        setVisible(true);
    }

    private void salvarAlteracao(Album album) {
        album.setNome(txtNomeAlbum.getText());
        album.setPagina(txtPaginas.getColumns());
        album.setCapa(txtCapa.getText());

        if (albumController.editarAlbum(album))
            JOptionPane.showMessageDialog(this, "Álbum alterado com sucesso!");
        else
            JOptionPane.showMessageDialog(this, "Erro ao alterar o album!");
    }

    private void carregarAlbum() {
        Album album = albumController.obterAlbum();
        if (album != null) {
            txtNomeAlbum.setText(album.getNome());
            txtPaginas.setText(String.valueOf(album.getPagina()));
            txtCapa.setText(album.getCapa());
        }
    }

    private void carregarFigurinhas() {
        FigurinhaController figurinhaController = new FigurinhaController();
        List<Figurinha> figurinhas = figurinhaController.buscarTodas();
        if (figurinhas != null) {
            for (Figurinha figurinha : figurinhas) {
                tableModel.addRow(new Object[]{figurinha.getId(), figurinha.getNome(), figurinha.getPagina()});
            }
        }
    }

    private void escolherCapa() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            txtCapa.setText(selectedFile.getAbsolutePath());
        }
    }

    private void adicionarFigurinha() {
        dispose();

        FrmFigurinha frmFigurinha = new FrmFigurinha();
        frmFigurinha.setVisible(true);
    }

    private void editarFigurinha(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = tableModel.getValueAt(selectedRow, 0).toString();
            String nome = tableModel.getValueAt(selectedRow, 1).toString();
            String paginaStr = tableModel.getValueAt(selectedRow, 2).toString();

            String novoNome = JOptionPane.showInputDialog(this, "Nome da Figurinha:", nome);
            String novaPaginaStr = JOptionPane.showInputDialog(this, "Página da Figurinha:", paginaStr);

            if (novoNome != null && novaPaginaStr != null) {
                int novaPagina = Integer.parseInt(novaPaginaStr);
                Figurinha figurinha = albumController.buscarFigurinhaPorId(Integer.parseInt(id));
                figurinha.setNome(novoNome);
                figurinha.setPagina(novaPagina);
                FigurinhaController figurinhaController = new FigurinhaController();

                if (figurinhaController.atualizarFigurinha(figurinha))
                    JOptionPane.showMessageDialog(this, "Figurinha alterada com sucesso!");
                else
                    JOptionPane.showMessageDialog(this, "Erro ao alterar figurinha!");

                tableModel.setValueAt(novoNome, selectedRow, 1);
                tableModel.setValueAt(novaPagina, selectedRow, 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma figurinha para editar.");
        }
    }

    private void removerFigurinha(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover a figurinha selecionada?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                FigurinhaController figurinhaController = new FigurinhaController();
                if (figurinhaController.removerFigurinha(id))
                    JOptionPane.showMessageDialog(this, "Figurinha excluída com sucesso!");
                else
                    JOptionPane.showMessageDialog(this, "Erro ao alterar a figurinha!");

                tableModel.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma figurinha para remover.");
        }
    }

    private void abrirJanelaFiltro() {
        String filtro = JOptionPane.showInputDialog(this, "Digite a tag para filtrar as figurinhas:");
        if (filtro != null && !filtro.isEmpty()) {
            List<Figurinha> figurinhasFiltradas = albumController.filtrarFigurinhaPorTag(filtro);
            tableModel.setRowCount(0);
            for (Figurinha figurinha : figurinhasFiltradas) {
                tableModel.addRow(new Object[]{figurinha.getId(), figurinha.getNome(), figurinha.getPagina()});
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FrmAutoria::new);
    }
}
