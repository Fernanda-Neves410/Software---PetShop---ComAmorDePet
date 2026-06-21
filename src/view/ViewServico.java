package view;

import model.Servico;

public class ViewServico extends javax.swing.JPanel {

    public ViewServico() {
        initComponents();
        setName("Cadastro de Serviços");
    }

    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jLabelCodigo = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelPreco = new javax.swing.JLabel();

        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldPreco = new javax.swing.JTextField();

        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 23));
        jLabelTitulo.setText("Cadastro de Serviços");

        jLabelCodigo.setText("Código:");
        jLabelNome.setText("Nome:");
        jLabelPreco.setText("Preço:");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(e -> salvar());

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(e -> limpar());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                    .addComponent(jLabelTitulo)

                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCodigo)
                            .addComponent(jLabelNome)
                            .addComponent(jLabelPreco)
                        )
                        .addGap(15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        )
                    )

                    .addGroup(layout.createSequentialGroup()
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
                .addGap(20)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigo)
                    .addComponent(jTextFieldCodigo))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldNome))

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

    private void salvar() {

        String codigo = jTextFieldCodigo.getText().trim();
        String nome = jTextFieldNome.getText().trim();
        String precoStr = jTextFieldPreco.getText().trim();

        if (codigo.isEmpty() || nome.isEmpty() || precoStr.isEmpty()) {
            ComAmorDePetApp.mostraMensagem("Preencha todos os campos!", "Serviço");
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(precoStr.replace(",", "."));
        } catch (Exception e) {
            ComAmorDePetApp.mostraMensagem("Preço inválido!", "Serviço");
            return;
        }

        Servico servico = new Servico(codigo, nome, preco);

        if (ComAmorDePetApp.controle.buscarServicoCodigo(codigo) != null) {
            ComAmorDePetApp.mostraMensagem("Serviço já existe!", "Serviço");
            return;
        }

        boolean salvou =
                ComAmorDePetApp.controle.salvarServico(servico);

        if (salvou) {
            ComAmorDePetApp.mostraMensagem(
                    "Serviço salvo!",
                    "Sucesso");
        } else {
            ComAmorDePetApp.mostraMensagem(
                    "Falha ao salvar serviço!",
                    "Erro");
        }
    }

    private void limpar() {
        jTextFieldCodigo.setText("");
        jTextFieldNome.setText("");
        jTextFieldPreco.setText("");
    }

    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelPreco;

    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPreco;

    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonLimpar;
}