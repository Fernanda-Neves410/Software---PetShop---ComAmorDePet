package view;

import view.ComAmorDePetApp;
import javax.swing.BorderFactory;
import java.awt.Font;

public class ViewRelatorio extends javax.swing.JPanel {

    public ViewRelatorio() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelRelTitulo = new javax.swing.JLabel();
        jButtonRelatorioGerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaRelatorio = new javax.swing.JTextArea();

        // ===== TÍTULO =====
        jLabelRelTitulo.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        jLabelRelTitulo.setText("Relatório do Sistema");

        // ===== BOTÃO =====
        jButtonRelatorioGerar.setText("Gerar Relatório");
        jButtonRelatorioGerar.addActionListener(e -> gerarRelatorio());

        // ===== TEXTO =====
        jTextAreaRelatorio.setColumns(20);
        jTextAreaRelatorio.setRows(12);
        jTextAreaRelatorio.setEditable(false);
        jTextAreaRelatorio.setFont(new Font("Monospaced", Font.PLAIN, 12));
        jTextAreaRelatorio.setText("Clique em 'Gerar Relatório' para visualizar os dados.");

        // padding interno do relatório (isso muda MUITO o visual)
        jTextAreaRelatorio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jScrollPane1.setViewportView(jTextAreaRelatorio);

        // margem externa do scroll (evita encostar na borda)
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== LAYOUT =====
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                    // TOPO LIMPO: título esquerda + botão direita
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelRelTitulo)
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jButtonRelatorioGerar)
                    )

                    // relatório ocupando área maior, mas com leitura confortável
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)

                )
                .addGap(20)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(15)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRelTitulo)
                    .addComponent(jButtonRelatorioGerar)
                )

                .addGap(12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)

                .addGap(15)
        );
    }

    private void gerarRelatorio() {
        jTextAreaRelatorio.setText(
            ComAmorDePetApp.controle.gerarRelatorioCompleto()
        );
    }

    // ===== COMPONENTES =====
    private javax.swing.JButton jButtonRelatorioGerar;
    private javax.swing.JLabel jLabelRelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaRelatorio;
}