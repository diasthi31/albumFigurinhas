package org.example.view;

import org.example.controller.FigurinhaController;
import org.example.entity.Figurinha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmNovaFigurinha extends JFrame {
    private FigurinhaController figurinhaController;

    private JTextField txtTag;
    private JTextField txtNome;
    private JTextField txtPagina;
    private JTextField txtCapa;
    private JTextArea txtDescricao;

    public FrmNovaFigurinha() {
        this.figurinhaController = new FigurinhaController();

        setTitle("Cadastro de Nova Figurinha");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel lblTag = new JLabel("Tag:");
        txtTag = new JTextField();
        panel.add(lblTag);
        panel.add(txtTag);

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        panel.add(lblNome);
        panel.add(txtNome);

        JLabel lblPagina = new JLabel("Página:");
        txtPagina = new JTextField();
        panel.add(lblPagina);
        panel.add(txtPagina);

        JLabel lblCapa = new JLabel("Capa:");
        txtCapa = new JTextField();
        panel.add(lblCapa);
        panel.add(txtCapa);

        JLabel lblDescricao = new JLabel("Descrição:");
        txtDescricao = new JTextArea();
        txtDescricao.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtDescricao);
        panel.add(lblDescricao);
        panel.add(scrollPane);

        JButton btnBuscar = new JButton("...");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tag = txtTag.getText().trim();
                if (!tag.isEmpty()) {
                    Figurinha figurinha = figurinhaController.buscarFigurinhaPorTag(tag);
                    if (figurinha != null) {
                        txtNome.setText(figurinha.getNome());
                        txtPagina.setText(String.valueOf(figurinha.getPagina()));
                        txtCapa.setText(figurinha.getCapa());
                        txtDescricao.setText(figurinha.getDescricao());
                    } else {
                        JOptionPane.showMessageDialog(FrmNovaFigurinha.this,
                                "Figurinha não encontrada!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(FrmNovaFigurinha.this,
                            "Digite a tag da figurinha!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(new JLabel());
        panel.add(btnBuscar);

        JButton btnInserir = new JButton("Inserir");
        btnInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Figurinha figurinha = new Figurinha();
                figurinha.setTag(txtTag.getText().trim());
                figurinha.setNome(txtNome.getText().trim());
                figurinha.setPagina(Integer.parseInt(txtPagina.getText().trim()));
                figurinha.setCapa(txtCapa.getText().trim());
                figurinha.setDescricao(txtDescricao.getText().trim());

                figurinhaController.cadastrarFigurinha(figurinha);

                JOptionPane.showMessageDialog(FrmNovaFigurinha.this,
                        "Figurinha inserida com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                limparCampos();
            }
        });

        panel.add(new JLabel());
        panel.add(btnInserir);

        add(panel, BorderLayout.CENTER);
    }

    private void limparCampos() {
        txtTag.setText("");
        txtNome.setText("");
        txtPagina.setText("");
        txtCapa.setText("");
        txtDescricao.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmNovaFigurinha frm = new FrmNovaFigurinha();
            frm.setVisible(true);
        });
    }
}
