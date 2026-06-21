package view;

import model.Produto;

public class ViewProduto extends javax.swing.JPanel {

    public ViewProduto() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel("Cadastro de Produtos");
        jLabelTitulo.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 23));
        

        jLabelFabricante = new javax.swing.JLabel("Fabricante:");
        jLabelCategoria = new javax.swing.JLabel("Categoria:");
        jLabelEstoque = new javax.swing.JLabel("Quantidade em estoque:");
        jLabelCodBarras = new javax.swing.JLabel("Código de Barras:");
        jLabelNome = new javax.swing.JLabel("Nome:");
        jLabelPreco = new javax.swing.JLabel("Preço:");

        jTextFieldFabricante = new javax.swing.JTextField();
        jTextFieldCategoria = new javax.swing.JTextField();
        jTextFieldTitulo = new javax.swing.JTextField();
        jTextFieldEstoque = new javax.swing.JTextField();
        jTextFieldPreco = new javax.swing.JTextField();
        jFTextFieldCodBarras = new javax.swing.JFormattedTextField();

        jButtonSalvar = new javax.swing.JButton("Salvar");
        jButtonLimpar = new javax.swing.JButton("Limpar");

        // ===== EVENTOS =====
        jButtonSalvar.addActionListener(evt -> jButtonSalvarActionPerformed(evt));
        jButtonLimpar.addActionListener(evt -> jButtonLimparActionPerformed(evt));

        // ===== LAYOUT =====
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                    .addComponent(jLabelTitulo)

                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNome)
                            .addComponent(jLabelCodBarras)
                            .addComponent(jLabelFabricante)
                            .addComponent(jLabelCategoria)
                            .addComponent(jLabelEstoque)
                            .addComponent(jLabelPreco)
                        )
                        .addGap(15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldTitulo, 300, 300, 300 )
                            .addComponent(jFTextFieldCodBarras, 150, 150, 150 )
                            .addComponent(jTextFieldFabricante,  250, 250, 250 )
                            .addComponent(jTextFieldCategoria,  200, 200, 200 )
                            .addComponent(jTextFieldEstoque,  120, 120, 120 )
                            .addComponent(jTextFieldPreco, 120, 120, 120 )
                        )
                    )

                    .addGroup(layout.createSequentialGroup()
                        .addGap(120)
                        .addComponent(jButtonLimpar)
                        .addGap(10)
                        .addComponent(jButtonSalvar)
                    )
                )
                .addGap(20)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(15)
                .addComponent(jLabelTitulo)
                .addGap(15)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldTitulo))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodBarras)
                    .addComponent(jFTextFieldCodBarras))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFabricante)
                    .addComponent(jTextFieldFabricante))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCategoria)
                    .addComponent(jTextFieldCategoria))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEstoque)
                    .addComponent(jTextFieldEstoque))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPreco)
                    .addComponent(jTextFieldPreco))

                .addGap(20)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpar)
                    .addComponent(jButtonSalvar))

                .addGap(20)
        );
    }

    // ===== SUA LÓGICA ORIGINAL (NÃO ALTERADA) =====

    static private double transDouble(String entrada) {
        double valor = 0.0;

        entrada = entrada.replace(",", ".");
        entrada = entrada.replace(" ", "");

        try {
            valor = Double.parseDouble(entrada);
            return valor;

        } catch (NumberFormatException e) {
            ComAmorDePetApp.mostraMensagem(
                "Valor inválido. Digite novamente.",
                "Cadastro de Produtos"
            );
            return -1;
        }
    }

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {

        String codigoBarras = jFTextFieldCodBarras.getText().trim();
        String nome = jTextFieldTitulo.getText().trim();
        String fabricante = jTextFieldFabricante.getText().trim();
        String categoria = jTextFieldCategoria.getText().trim();
        String spreco = jTextFieldPreco.getText().trim();
        String sestoque = jTextFieldEstoque.getText().trim();

        if (codigoBarras.trim().isEmpty() || nome.trim().isEmpty()) {
            ComAmorDePetApp.mostraMensagem("Preencha os campos obrigatórios!", "Erro");
            return;
        }

        int estoque = Integer.parseInt(sestoque.replace(" ", "")); 
        try {
            estoque = Integer.parseInt(sestoque);
            if (estoque < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            ComAmorDePetApp.mostraMensagem(
                "Estoque inválido! Digite um número válido (>= 0).",
                "Erro"
            );
            return;
        }

        double preco = transDouble(spreco);
        if (preco < 0) return;


        Produto produto = ComAmorDePetApp.controle.buscarProdutoCodigo(codigoBarras);

        if (produto == null) {

            produto = new Produto(codigoBarras, nome, fabricante, categoria, preco, estoque);

            ComAmorDePetApp.controle.salvarProduto(produto);

            ComAmorDePetApp.mostraMensagem("Produto salvo com sucesso!", "Sucesso");

            limparCampos();

        } else {
            ComAmorDePetApp.mostraMensagem("Produto já cadastrado!", "Erro");
        }
    }

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {
        limparCampos();
    }

    private void limparCampos() {
        jFTextFieldCodBarras.setText("");
        jTextFieldTitulo.setText("");
        jTextFieldFabricante.setText("");
        jTextFieldCategoria.setText("");
        jTextFieldPreco.setText("");
        jTextFieldEstoque.setText("");
    }

    // ===== COMPONENTES =====
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonSalvar;

    private javax.swing.JFormattedTextField jFTextFieldCodBarras;

    private javax.swing.JLabel jLabelCodBarras;
    private javax.swing.JLabel jLabelEstoque;
    private javax.swing.JLabel jLabelFabricante;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelPreco;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelNome;

    private javax.swing.JTextField jTextFieldEstoque;
    private javax.swing.JTextField jTextFieldFabricante;
    private javax.swing.JTextField jTextFieldCategoria;
    private javax.swing.JTextField jTextFieldPreco;
    private javax.swing.JTextField jTextFieldTitulo;
}