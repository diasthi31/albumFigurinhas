package org.example.view;

import org.example.controller.FigurinhaController;
import org.example.controller.Figurinha_AlbumController;
import org.example.entity.Figurinha;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FrmNovaFigurinha extends JFrame {
    private final FigurinhaController figurinhaController;

    private JTextField txtTag;
    private JTextField txtNome;
    private JTextField txtPagina;
    private JTextField txtNumero;
    private JLabel lblVisualizacaoCapa;

    public FrmNovaFigurinha() {
        this.figurinhaController = new FigurinhaController();

        setTitle("Cadastro de Nova Figurinha");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTag = new JLabel("Tag:");
        txtTag = new JTextField(30);
        JButton btnBuscar = new JButton("...");

        btnBuscar.addActionListener(e -> buscarFigurinha());

        addComponent(panel, lblTag, gbc, 0, 0);
        addComponent(panel, txtTag, gbc, 1, 0);
        addComponent(panel, btnBuscar, gbc, 2, 0);

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(20);
        addComponent(panel, lblNome, gbc, 0, 1);
        addComponent(panel, txtNome, gbc, 1, 1);

        JLabel lblPagina = new JLabel("Página:");
        txtPagina = new JTextField(20);
        addComponent(panel, lblPagina, gbc, 0, 2);
        addComponent(panel, txtPagina, gbc, 1, 2);

        JLabel lblNumero = new JLabel("Número:");
        txtNumero = new JTextField(20);
        addComponent(panel, lblNumero, gbc, 0, 3);
        addComponent(panel, txtNumero, gbc, 1, 3);

        JLabel lblCapaTitle = new JLabel("Capa:");
        lblVisualizacaoCapa = new JLabel();
        lblVisualizacaoCapa.setPreferredSize(new Dimension(150, 150));
        addComponent(panel, lblCapaTitle, gbc, 0, 4);
        addComponent(panel, lblVisualizacaoCapa, gbc, 1, 4);

        JButton btnInserir = new JButton("Inserir");
        btnInserir.addActionListener(e -> inserirFigurinha());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarAoAlbum());

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        addComponent(panel, btnInserir, gbc, 0, 5);
        addComponent(panel, btnVoltar, gbc, 1, 5);

        add(panel, BorderLayout.CENTER);
    }

    private void addComponent(JPanel panel, Component component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    private void buscarFigurinha() {
        String tag = txtTag.getText().trim();
        if (!tag.isEmpty()) {
            Figurinha figurinha = figurinhaController.buscarFigurinhaPorTag(tag);
            if (figurinha != null) {
                txtNome.setText(figurinha.getNome());
                txtPagina.setText(String.valueOf(figurinha.getPagina()));
                txtNumero.setText(String.valueOf(figurinha.getId()));

                exibirImagemCapa(figurinha.getCapa());
            } else {
                JOptionPane.showMessageDialog(this,
                        "Figurinha não encontrada!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Digite a tag da figurinha!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selecionarImagemCapa() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "png", "gif", "bmp"));

        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();

            exibirImagemCapa(filePath);
        }
    }

    private void exibirImagemCapa(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(file));
                Image image = imageIcon.getImage().getScaledInstance(lblVisualizacaoCapa.getWidth(), lblVisualizacaoCapa.getHeight(), Image.SCALE_SMOOTH);
                lblVisualizacaoCapa.setIcon(new ImageIcon(image));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inserirFigurinha() {
        try {
            Figurinha figurinha = new Figurinha();
            figurinha.setId(Integer.parseInt(txtNumero.getText()));
            System.out.println(Integer.parseInt(txtNumero.getText()));

            Figurinha_AlbumController figurinha_albumController = new Figurinha_AlbumController();

            if (figurinha_albumController.insereFigurinhaAlbum(Integer.parseInt(txtNumero.getText())))
                JOptionPane.showMessageDialog(this,
                    "Figurinha inserida com sucesso!",
                    "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "Erro ao inserir figurinha!");

            limparCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Página e ID devem ser números inteiros!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltarAoAlbum() {
        dispose();

        FrmAlbum frmAlbum = new FrmAlbum();
        frmAlbum.setVisible(true);
    }

    private void limparCampos() {
        txtTag.setText("");
        txtNome.setText("");
        txtPagina.setText("");
        txtNumero.setText("");
        lblVisualizacaoCapa.setIcon(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmNovaFigurinha frm = new FrmNovaFigurinha();
            frm.setVisible(true);
        });
    }
}
