package org.example.view;

import org.example.controller.FigurinhaController;
import org.example.entity.Figurinha;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmFigurinha extends JFrame {

    private JTextField txtNome;
    private JTextField txtPagina;
    private JTextField txtCapa;
    private JTextField txtTag;
    private JTextField txtDescricao;
    private JButton btnSalvar;

    private FigurinhaController figurinhaController;

    public FrmFigurinha() {
        this.figurinhaController = new FigurinhaController();

        // Inicialize seus componentes aqui

        // Implemente a lógica para preencher os campos com dados da figurinha

        // Implemente a lógica para bloquear campos se o usuário for colecionador

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica para salvar as alterações da figurinha
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Implemente métodos para preencher e bloquear campos com base no perfil do usuário
}
