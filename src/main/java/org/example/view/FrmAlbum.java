package org.example.view;

import org.example.controller.AlbumController;
import org.example.entity.Album;
import org.example.entity.Figurinha;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmAlbum extends JFrame {

    private AlbumController albumController;

    public FrmAlbum() {
        this.albumController = new AlbumController();

        setTitle("Álbum de Figurinhas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        layoutComponents();

        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }

    private void initComponents() {
        Album album = albumController.obterAlbum();

        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Simulação de figurinhas do álbum (substitua pela lógica real de seu sistema)
        List<Figurinha> figurinhas = buscarFigurinhasDoAlbum(album);

        for (Figurinha figurinha : figurinhas) {
            JLabel lblFigurinha = new JLabel(new ImageIcon(figurinha.getCapa()));
            lblFigurinha.setToolTipText("Clique duplo para detalhes");

            lblFigurinha.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        abrirFrmFigurinha(figurinha);
                    }
                }
            });

            panel.add(lblFigurinha);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void layoutComponents() {
        // Defina o layout da sua interface aqui, se necessário
    }

    private List<Figurinha> buscarFigurinhasDoAlbum(Album album) {
        // Implemente a lógica para buscar as figurinhas associadas ao álbum
        // Exemplo simulado:
        // return albumController.buscarFigurinhasDoAlbum(album);
        return null; // Simulação
    }

    private void abrirFrmFigurinha(Figurinha figurinha) {
        // Implemente a lógica para abrir FrmFigurinha com os detalhes da figurinha clicada
        // Exemplo simulado:
        // new FrmFigurinha(figurinha);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmAlbum());
    }
}
