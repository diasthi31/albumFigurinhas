package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmSplash extends JWindow implements ActionListener {
    private final int duracao;
    private Timer timer;
    private JProgressBar barraProgresso;

    public FrmSplash(Integer duracao) {
        this.duracao = duracao;
    }

    public void mostrarSplash() {
        JPanel conteudo = configuraConteudoTela();
        configuraJanela(conteudo);
        configuraTelaSplash(conteudo);

        setVisible(true);

        try {
            Thread.sleep(duracao);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }

        setVisible(false);
    }

    private JPanel configuraConteudoTela() {
        JPanel conteudo = (JPanel) getContentPane();
        conteudo.setBackground(Color.BLACK);

        return conteudo;
    }

    private void configuraJanela(JPanel conteudo) {
        setSize(720, 720);
        setLocationRelativeTo(null);
    }

    private void configuraTelaSplash(JPanel conteudo) {
        JLabel rotulo = new JLabel(new ImageIcon("src/main/java/org/example/img/CR7Vasco.jpg"));
        barraProgresso = new JProgressBar(0, 100);
        barraProgresso.setValue(0);
        barraProgresso.setPreferredSize(new Dimension(100, 15));

        timer = new Timer(50, this);
        timer.setRepeats(true);
        timer.start();

        conteudo.add(rotulo, BorderLayout.CENTER);
        conteudo.add(barraProgresso, BorderLayout.SOUTH);
        conteudo.setBorder(BorderFactory.createLineBorder(new Color(126, 20, 20, 255)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int valor = barraProgresso.getValue();

        if (valor < barraProgresso.getMaximum()) {
            barraProgresso.setValue(valor + 1);
        } else {
            timer.stop();
            dispose();
        }
    }
}