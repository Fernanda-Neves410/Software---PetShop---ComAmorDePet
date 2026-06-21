package view;

import model.Animal;
import model.Cliente;

public class ViewAnimal extends javax.swing.JPanel {

    public ViewAnimal() {
        initComponents();
        setName("Cadastro de Animais");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // ===== COMPONENTES =====
        jLabelTitulo = new javax.swing.JLabel();

        jLabelNome = new javax.swing.JLabel();
        jLabelEspecie = new javax.swing.JLabel();
        jLabelRaca = new javax.swing.JLabel();
        jLabelPorte = new javax.swing.JLabel();
        jLabelTemperamento = new javax.swing.JLabel();
        jLabelCpfDono = new javax.swing.JLabel();

        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldEspecie = new javax.swing.JTextField();
        jTextFieldRaca = new javax.swing.JTextField();
        jTextFieldPorte = new javax.swing.JTextField();
        jTextFieldTemperamento = new javax.swing.JTextField();

        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();

        // ===== TÍTULO =====
        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 23));
        jLabelTitulo.setText("Cadastro de Animais");

        // ===== LABELS =====
        jLabelNome.setText("Nome do Pet");
        jLabelEspecie.setText("Espécie (ex: Cão)");
        jLabelRaca.setText("Raça");
        jLabelPorte.setText("Porte");
        jLabelTemperamento.setText("Temperamento");
        jLabelCpfDono.setText("CPF do Dono");

        // ===== CPF DO DONO (MÁSCARA) =====
        try {
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("###.###.###-##");

            mask.setPlaceholderCharacter('_');
            jFTextFieldCpfDono = new javax.swing.JFormattedTextField(mask);

        } catch (java.text.ParseException e) {
            jFTextFieldCpfDono = new javax.swing.JFormattedTextField();
        }

        // cursor sempre no início correto
        jFTextFieldCpfDono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                javax.swing.SwingUtilities.invokeLater(() -> jFTextFieldCpfDono.setCaretPosition(0));
            }
        });

        // ===== BOTÕES =====
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(e -> salvarAnimal());

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

                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelNome)
                                                        .addComponent(jLabelEspecie)
                                                        .addComponent(jLabelRaca)
                                                        .addComponent(jLabelPorte)
                                                        .addComponent(jLabelTemperamento)
                                                        .addComponent(jLabelCpfDono))

                                                .addGap(15)

                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextFieldNome,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 300,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldEspecie,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldRaca,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldPorte,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldTemperamento,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jFTextFieldCpfDono,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))

                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(120)
                                                .addComponent(jButtonLimpar)
                                                .addGap(10)
                                                .addComponent(jButtonSalvar)))
                                .addContainerGap(20, Short.MAX_VALUE)));

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15)
                                .addComponent(jLabelTitulo)
                                .addGap(20)

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelCpfDono)
                                        .addComponent(jFTextFieldCpfDono))

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelNome)
                                        .addComponent(jTextFieldNome))

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelEspecie)
                                        .addComponent(jTextFieldEspecie))

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelRaca)
                                        .addComponent(jTextFieldRaca))

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelPorte)
                                        .addComponent(jTextFieldPorte))

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelTemperamento)
                                        .addComponent(jTextFieldTemperamento))

                                .addGap(20)

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonLimpar)
                                        .addComponent(jButtonSalvar))

                                .addGap(20)));
    }

    // ===== LÓGICA =====
    private void salvarAnimal() {

        String cpfDono = jFTextFieldCpfDono.getText().trim();
        String nome = jTextFieldNome.getText().trim();
        String especie = jTextFieldEspecie.getText().trim();
        String raca = jTextFieldRaca.getText().trim();
        String porte = jTextFieldPorte.getText().trim();
        String temperamento = jTextFieldTemperamento.getText().trim();

        // 1. Validação dos campos obrigatórios
        if (cpfDono.contains("_") || nome.isEmpty()) {
            ComAmorDePetApp.mostraMensagem(
                    "O Nome do Pet e o CPF do Dono são obrigatórios!",
                    "Erro de Preenchimento");
            return;
        }

        // 2. Verifica se o dono existe no banco de dados
        Cliente dono = ComAmorDePetApp.controle.buscarClienteCpf(cpfDono);

        if (dono == null) {
            ComAmorDePetApp.mostraMensagem(
                    "Dono não encontrado! Por favor, cadastre o cliente antes de cadastrar o pet.",
                    "Cliente Inexistente");
            return;
        }

        // 3. Monta o objeto Animal e salva
        Animal animal = new Animal(0, nome, especie, raca, porte, temperamento, dono);

        boolean sucesso = ComAmorDePetApp.controle.salvarAnimal(animal);

        if (sucesso) {
            ComAmorDePetApp.mostraMensagem("Pet cadastrado com sucesso para o cliente " + dono.getNome() + "!",
                    "Sucesso");
            limparCampos();
        } else {
            ComAmorDePetApp.mostraMensagem("Ocorreu um erro ao salvar o animal no banco de dados.", "Erro");
        }
    }

    private void limparCampos() {
        jFTextFieldCpfDono.setText("");
        jTextFieldNome.setText("");
        jTextFieldEspecie.setText("");
        jTextFieldRaca.setText("");
        jTextFieldPorte.setText("");
        jTextFieldTemperamento.setText("");

        // Retorna o foco para o primeiro campo
        jFTextFieldCpfDono.requestFocus();
    }

    // ===== COMPONENTES =====
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelEspecie;
    private javax.swing.JLabel jLabelRaca;
    private javax.swing.JLabel jLabelPorte;
    private javax.swing.JLabel jLabelTemperamento;
    private javax.swing.JLabel jLabelCpfDono;

    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldEspecie;
    private javax.swing.JTextField jTextFieldRaca;
    private javax.swing.JTextField jTextFieldPorte;
    private javax.swing.JTextField jTextFieldTemperamento;

    private javax.swing.JFormattedTextField jFTextFieldCpfDono;

    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonLimpar;
}