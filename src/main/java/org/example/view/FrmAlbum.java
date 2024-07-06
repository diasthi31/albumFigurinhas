package org.example.view;

import org.example.controller.AlbumController;
import org.example.controller.Figurinha_AlbumController;
import org.example.entity.Figurinha;
import org.example.repository.FigurinhaRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FrmAlbum extends JFrame {
    private AlbumController albumController;
    private JLabel lblVisualizacaoCapa;
    private JPanel panel;
    private JScrollPane scrollPane;
    private List<Figurinha> figurinhas;

    public FrmAlbum() {
        this.albumController = new AlbumController();

        setTitle("Álbum de figurinhas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        inicializarComponentes();
        pack();
    }

    private void inicializarComponentes() {
        JPanel topPanel = new JPanel(new FlowLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");

        topPanel.add(btn1);
        topPanel.add(btn2);
        topPanel.add(btn3);

        panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblVisualizacaoCapa = new JLabel();
        lblVisualizacaoCapa.setPreferredSize(new Dimension(150, 150));
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(lblVisualizacaoCapa, constraints);

        figurinhas = buscarFigurinhas();
        carregarFigurinhas();

        scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton btnFirst = new JButton("|<");
        JButton btnPrev = new JButton("<");
        JTextField txtPage = new JTextField("002", 3);
        JButton btnNext = new JButton(">");
        JButton btnLast = new JButton(">|");
        bottomPanel.add(btnFirst);
        bottomPanel.add(btnPrev);
        bottomPanel.add(txtPage);
        bottomPanel.add(btnNext);
        bottomPanel.add(btnLast);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        btn1.addActionListener(e -> {
            dispose();
            FrmUsuario frmUsuario = new FrmUsuario();
            frmUsuario.setVisible(true);
        });

        btn2.addActionListener(e -> {
            dispose();
            FrmNovaFigurinha novaFigurinha = new FrmNovaFigurinha();
            novaFigurinha.setVisible(true);
        });

        btn3.addActionListener(e -> {
            dispose();
            FrmSobre sobre = new FrmSobre();
            sobre.setVisible(true);
        });

        btnFirst.addActionListener(e -> {
            // lógica para ir para a primeira página
        });

        btnPrev.addActionListener(e -> {
            // lógica para ir para a página anterior
        });

        btnNext.addActionListener(e -> {
            // lógica para ir para a próxima página
        });

        btnLast.addActionListener(e -> {
            // lógica para ir para a última página
        });
    }

    private void carregarFigurinhas() {
        panel.removeAll();
        FigurinhaRepository figurinhaRepository = new FigurinhaRepository();

        if (figurinhas != null && !figurinhas.isEmpty()) {
            for (Figurinha figurinha : figurinhas) {
                ImageIcon imagemIcon = figurinhaRepository.visualizarImagemBanco(figurinha.getId());

                if (imagemIcon != null) {
                    JLabel lblFigurinha = new JLabel(imagemIcon);
                    lblFigurinha.setToolTipText("Clique duplo para detalhes");

                    lblFigurinha.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getClickCount() == 2) {
                                abrirFrmFigurinha();
                            }
                        }
                    });

                    panel.add(lblFigurinha);
                } else {
                    System.out.println("Erro ao carregar a imagem da figurinha: " + figurinha.getNome());
                }
            }
        } else {
            JLabel lblMensagem = new JLabel("Nenhuma figurinha disponível");
            lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(lblMensagem);
        }

        panel.revalidate();
        panel.repaint();
    }

    private List<Figurinha> buscarFigurinhas() {
        Figurinha_AlbumController controller = new Figurinha_AlbumController();
        return controller.buscaFigurinhas();
    }

    private void abrirFrmFigurinha() {
        new FrmFigurinha().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmAlbum frmAlbum = new FrmAlbum();
            frmAlbum.setVisible(true);
        });
    }
}
