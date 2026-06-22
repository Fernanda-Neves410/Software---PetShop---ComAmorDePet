package view;

import model.Funcionario;
import javax.swing.*;
import java.awt.*;

public class ViewFuncionario extends JPanel {
    private JTextField txtMatricula = new JTextField(20);
    private JTextField txtCpf = new JTextField(20);
    private JTextField txtNome = new JTextField(20);
    private JTextField txtTelefone = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtLogin = new JTextField(20);
    private JComboBox<String> cbPermissao = new JComboBox<>(new String[]{"Administrador", "Atendente", "Cuidador"});
    private JButton btnSalvar = new JButton("Salvar");

    public ViewFuncionario() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adicionando campos com alinhamento
        adicionarCampo("Matrícula:", txtMatricula, 0, gbc);
        adicionarCampo("CPF:", txtCpf, 1, gbc);
        adicionarCampo("Nome:", txtNome, 2, gbc);
        adicionarCampo("Telefone:", txtTelefone, 3, gbc);
        adicionarCampo("Email:", txtEmail, 4, gbc);
        adicionarCampo("Login:", txtLogin, 5, gbc);
        adicionarCampo("Permissão:", cbPermissao, 6, gbc);
        
        gbc.gridx = 1; gbc.gridy = 7;
        add(btnSalvar, gbc);

        btnSalvar.addActionListener(e -> {
            Funcionario f = new Funcionario(txtMatricula.getText(), txtCpf.getText(), txtNome.getText(), 
                                           txtTelefone.getText(), txtEmail.getText(), txtLogin.getText(), 
                                           cbPermissao.getSelectedIndex() + 1);
            if (ComAmorDePetApp.controle.salvarFuncionario(f)) {
                JOptionPane.showMessageDialog(this, "Funcionário salvo!");
            }
        });
    }

    private void adicionarCampo(String label, JComponent campo, int linha, GridBagConstraints gbc) {
        gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(campo, gbc);
    }
}