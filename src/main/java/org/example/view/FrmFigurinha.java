package org.example.view;

import org.example.controller.FigurinhaController;
import org.example.entity.Figurinha;
import org.example.model.FigurinhaModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FrmFigurinha extends JFrame {
    private JTextField txtNome;
    private JTextField txtPagina;
    private JTextField txtCapa;
    private JLabel lblVisualizacaoCapa;
    private JTextField txtTag;
    private JTextField txtDescricao;

    private final FigurinhaController figurinhaController;

    public FrmFigurinha() {
        this.figurinhaController = new FigurinhaController();

        setTitle("Cadastro de Figurinha");
        setDefaultCloseOperation(voltaTela());
        setSize(900, 500);
        setLocationRelativeTo(null);
        inicializarComponentes();

        setVisible(true);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridBagLayout());
        getContentPane().add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel lblNome = new JLabel("Nome:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lblNome, constraints);

        txtNome = new JTextField(40);
        txtNome.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(txtNome, constraints);

        JLabel lblPagina = new JLabel("Página:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lblPagina, constraints);

        txtPagina = new JTextField(5);
        txtPagina.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(txtPagina, constraints);

        JLabel lblCapa = new JLabel("Capa:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(lblCapa, constraints);

        txtCapa = new JTextField(40);
        txtCapa.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(txtCapa, constraints);

        JButton btnSelecionarCapa = new JButton("...");
        btnSelecionarCapa.addActionListener(e -> selecionarImagemCapa());
        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(btnSelecionarCapa, constraints);

        lblVisualizacaoCapa = new JLabel();
        lblVisualizacaoCapa.setPreferredSize(new Dimension(150, 150));
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(lblVisualizacaoCapa, constraints);

        JLabel lblTag = new JLabel("Tag:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(lblTag, constraints);

        txtTag = new JTextField(40);
        txtTag.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(txtTag, constraints);

        JLabel lblDescricao = new JLabel("Descrição:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(lblDescricao, constraints);

        txtDescricao = new JTextField(40);
        txtDescricao.setBorder(new EmptyBorder(5, 5, 5, 5));
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(txtDescricao, constraints);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarFigurinha());
        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(btnSalvar, constraints);
    }

    private void salvarFigurinha() {
        Figurinha figurinha = new Figurinha();
        figurinha.setNome(txtNome.getText());
        figurinha.setPagina(Integer.parseInt(txtPagina.getText()));
        figurinha.setCapa(txtCapa.getText());
        figurinha.setTag(txtTag.getText());
        figurinha.setDescricao(txtDescricao.getText());

        figurinhaController.cadastrarFigurinha(figurinha);

        JOptionPane.showMessageDialog(this, "Figurinha cadastrada com sucesso!");

        limparCampos();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtPagina.setText("");
        txtCapa.setText("");
        lblVisualizacaoCapa.setIcon(null);
        txtTag.setText("");
        txtDescricao.setText("");
    }

    private void selecionarImagemCapa() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "png", "gif", "bmp"));

        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            txtCapa.setText(filePath);
            txtTag.setText(FigurinhaModel.gerarHash(txtCapa.getText()));

            exibirImagemCapa(filePath);
        }
    }

    private void exibirImagemCapa(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(file));
                Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblVisualizacaoCapa.setIcon(new ImageIcon(image));
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public int voltaTela() {
        dispose();

        FrmAutoria autoria = new FrmAutoria();
        autoria.setVisible(true);

        return 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FrmFigurinha::new);
    }
}