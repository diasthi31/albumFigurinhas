package org.example.view;

import org.example.controller.AlbumController;
import org.example.entity.Album;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAutoria extends JFrame {
    private AlbumController albumController;
    private JTextField txtNomeAlbum;
    private JTextField txtPagina;
    private JTextField txtCapa;
    private JTextArea txtDescricao;
    private JButton btnSalvar;
    private JButton btnRemoverTodas;

    public FrmAutoria() {
        albumController = new AlbumController();

        setTitle("Editar Álbum");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel pnlForm = new JPanel(new GridLayout(5, 2));

        pnlForm.add(new JLabel("Nome do Álbum:"));
        txtNomeAlbum = new JTextField();
        pnlForm.add(txtNomeAlbum);

        pnlForm.add(new JLabel("Página:"));
        txtPagina = new JTextField();
        pnlForm.add(txtPagina);

        pnlForm.add(new JLabel("Capa:"));
        txtCapa = new JTextField();
        pnlForm.add(txtCapa);

        pnlForm.add(new JLabel("Descrição:"));
        txtDescricao = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(txtDescricao);
        pnlForm.add(scrollPane);

        btnSalvar = new JButton("Salvar");
        btnRemoverTodas = new JButton("Remover Todas as Figurinhas");

        JPanel pnlButtons = new JPanel();
        pnlButtons.add(btnSalvar);
        pnlButtons.add(btnRemoverTodas);

        add(pnlForm, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        carregarAlbum();

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Album album = albumController.obterAlbum();
                album.setNome(txtNomeAlbum.getText());
                album.setPagina(Integer.parseInt(txtPagina.getText()));
                album.setCapa(txtCapa.getText());
                albumController.atualizarAlbum(album);
                JOptionPane.showMessageDialog(null, "Álbum atualizado com sucesso.");
            }
        });

        btnRemoverTodas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover todas as figurinhas?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    albumController.removerTodasFigurinhas();
                    JOptionPane.showMessageDialog(null, "Todas as figurinhas foram removidas.");
                }
            }
        });

        setVisible(true);
    }

    private void carregarAlbum() {
        Album album = albumController.obterAlbum();
//        if (album != null) {
//            txtNomeAlbum.setText(album.getNome());
//            txtPagina.setText(album.getPagina().toString());
//            txtCapa.setText(album.getCapa());
//        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmAutoria());
    }
}
