package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmSplash extends JWindow implements ActionListener {
    private final int duracao;
    Timer timer;
    JProgressBar barraProgresso;

    public FrmSplash(Integer duracao) throws InterruptedException {
        this.duracao = duracao;
    }

    public void mostrarSplash() {
        JPanel conteudo = (JPanel) getContentPane();
        conteudo.setBackground(Color.BLACK);

        //DEFINE TAMANHO DA JANELA
        int largura = 720;
        int altura = 720;
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (tela.width - largura) / 2;
        int y = (tela.height - altura) / 2;
        setBounds(x, y, largura, altura);
        setLocationRelativeTo(null);

        //DEFINE A TELA DE SPLASH
        JLabel rotulo = new JLabel(new ImageIcon("src/main/java/org/example/img/CR7Vasco.jpg"));

        barraProgresso = new JProgressBar();
        barraProgresso.setValue(0);
        barraProgresso.setMinimum(0);
        barraProgresso.setMaximum(100);
        barraProgresso.setPreferredSize(new Dimension(100,15));
//      getContentPane().add(barraProgresso, BorderLayout.SOUTH);

        timer = new Timer(50, this);
        timer.setRepeats(true);
        timer.start();

        conteudo.add(rotulo, BorderLayout.CENTER);
        conteudo.add(barraProgresso, BorderLayout.SOUTH);
        Color oraRed = new Color(126, 20, 20, 255);
        conteudo.setBorder(BorderFactory.createLineBorder(oraRed));

        //DEIXA A TELA VISÍVEL
        setVisible(true);

        //AGUARDA A DURAÇÃO PASSADA NA MAIN
        try {
            Thread.sleep(duracao);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //FECHA A TELA DE SPLASH
        setVisible(false);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int v = barraProgresso.getValue();

        if (v < barraProgresso.getMaximum())
            barraProgresso.setValue(v+1);
        else {
            timer.stop();
            dispose();
        }
    }
}