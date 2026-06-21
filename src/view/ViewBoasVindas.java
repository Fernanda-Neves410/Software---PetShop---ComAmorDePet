package view;

import java.awt.*;
import javax.swing.*;

public class ViewBoasVindas extends JPanel {

    private JButton btnEntrar;
    private JButton btnLogin;

    public ViewBoasVindas() {

        setLayout(new BorderLayout());

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        // ===== TÍTULO =====
        JLabel titulo = new JLabel(" Com Amor de Pet ");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Sistema de Gestão para Pet Shop");
        subtitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel modulos = new JLabel("Clientes • Produtos • Serviços • Vendas");
        modulos.setFont(new Font("Tahoma", Font.PLAIN, 13));
        modulos.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel versao = new JLabel("Versão 2.0");
        versao.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ===== BOTÕES =====
        btnEntrar = new JButton("Entrar no Sistema");
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setPreferredSize(new Dimension(220, 40));


        // ===== RODAPÉ =====
        JLabel rodape = new JLabel("APSOO - UFMS | Grupo B");
        rodape.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ===== ESPAÇAMENTO =====
        centro.add(Box.createVerticalStrut(40));
        centro.add(titulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(subtitulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(modulos);
        centro.add(Box.createVerticalStrut(15));
        centro.add(versao);
        centro.add(Box.createVerticalStrut(30));

        centro.add(btnEntrar);
        centro.add(Box.createVerticalStrut(10));

        centro.add(rodape);
        centro.add(Box.createVerticalStrut(20));

        add(centro, BorderLayout.CENTER);

        // ação provisória
        btnEntrar.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento.")
        );
    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

}