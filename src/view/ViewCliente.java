package view;

import model.Cliente;

public class ViewCliente extends javax.swing.JPanel {

    public ViewCliente() {
        initComponents();
        setName("Cadastro de Clientes");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // ===== COMPONENTES =====
        jLabelTitulo = new javax.swing.JLabel();

        jLabelNome = new javax.swing.JLabel();
        jLabelCpf = new javax.swing.JLabel();
        jLabelEndereco = new javax.swing.JLabel();
        jLabelCidade = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelTelefone = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();

        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldCidade = new javax.swing.JTextField();
        jTextFieldEstado = new javax.swing.JTextField();
        jTextFieldTelefone = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaEndereco = new javax.swing.JTextArea();

        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();

        // ===== TÍTULO =====
        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 23));
        jLabelTitulo.setText("Cadastro de Clientes");

        // ===== LABELS =====
        jLabelNome.setText("Nome");
        jLabelCpf.setText("CPF");
        jLabelEndereco.setText("Endereço");
        jLabelCidade.setText("Cidade");
        jLabelEstado.setText("Estado");
        jLabelTelefone.setText("Telefone");
        jLabelEmail.setText("E-mail");

        // ===== CPF (CORRIGIDO) =====
        try {
            javax.swing.text.MaskFormatter mask =
                new javax.swing.text.MaskFormatter("###.###.###-##");

            mask.setPlaceholderCharacter('_');
            jFTextFieldCPF = new javax.swing.JFormattedTextField(mask);

        } catch (java.text.ParseException e) {
            jFTextFieldCPF = new javax.swing.JFormattedTextField();
        }

        // cursor sempre no início correto
        jFTextFieldCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                javax.swing.SwingUtilities.invokeLater(() ->
                    jFTextFieldCPF.setCaretPosition(0)
                );
            }
        });

        // ===== ENDEREÇO =====
        jTextAreaEndereco.setRows(3);
        jTextAreaEndereco.setColumns(25);
        jScrollPane1.setViewportView(jTextAreaEndereco);

        // ===== BOTÕES =====
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(e -> salvarCliente());

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(e -> limparCampos());

        // ===== LAYOUT =====
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                    .addComponent(jLabelTitulo)

                    .addGroup(layout.createSequentialGroup()

                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNome)
                            .addComponent(jLabelCpf)
                            .addComponent(jLabelEndereco)
                            .addComponent(jLabelCidade)
                            .addComponent(jLabelEstado)
                            .addComponent(jLabelTelefone)
                            .addComponent(jLabelEmail)
                        )

                        .addGap(15)

                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        )
                    )

                    .addGroup(layout.createSequentialGroup()
                        .addGap(120)
                        .addComponent(jButtonLimpar)
                        .addGap(10)
                        .addComponent(jButtonSalvar)
                    )
                )
                .addContainerGap(20, Short.MAX_VALUE)
            )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15)
                .addComponent(jLabelTitulo)
                .addGap(20)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldNome))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCpf)
                    .addComponent(jFTextFieldCPF))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEndereco)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCidade)
                    .addComponent(jTextFieldCidade))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEstado)
                    .addComponent(jTextFieldEstado))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefone)
                    .addComponent(jTextFieldTelefone))

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail))

                .addGap(20)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpar)
                    .addComponent(jButtonSalvar))

                .addGap(20)
            )
        );
    }

    // ===== LÓGICA =====
    private void salvarCliente() {

        String cpf = jFTextFieldCPF.getText().trim();
        String nome = jTextFieldNome.getText().trim();

        String cidade = jTextFieldCidade.getText().trim();
        String estado = jTextFieldEstado.getText().trim();
        String telefone = jTextFieldTelefone.getText().trim();
        String email = jTextFieldEmail.getText().trim();
        String endereco = jTextAreaEndereco.getText().trim();

        // validação forte (evita bug de salvar vazio)
        if (cpf.contains("_") || nome.isEmpty()) {
            ComAmorDePetApp.mostraMensagem(
                "CPF e Nome são obrigatórios!",
                "Erro"
            );
            return;
        }

        Cliente cliente = ComAmorDePetApp.controle.buscarClienteCpf(cpf);

        if (cliente == null) {

            cliente = new Cliente(cpf, nome);
            cliente.setCidade(cidade);
            cliente.setEstado(estado);
            cliente.setTelefone(telefone);
            cliente.setEmail(email);
            cliente.setEndereco(endereco);

            ComAmorDePetApp.controle.salvarCliente(cliente);

            ComAmorDePetApp.mostraMensagem("Cliente cadastrado!", "Sucesso");

        } else {
            ComAmorDePetApp.mostraMensagem("CPF já cadastrado!", "Erro");
        }

        limparCampos();
    }

    private void limparCampos() {
        jTextFieldNome.setText("");
        jFTextFieldCPF.setText("");
        jTextAreaEndereco.setText("");
        jTextFieldCidade.setText("");
        jTextFieldEstado.setText("");
        jTextFieldTelefone.setText("");
        jTextFieldEmail.setText("");
    }

    // ===== COMPONENTES =====
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JLabel jLabelEmail;

    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldTelefone;
    private javax.swing.JTextField jTextFieldEmail;

    private javax.swing.JFormattedTextField jFTextFieldCPF;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaEndereco;

    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonLimpar;
}