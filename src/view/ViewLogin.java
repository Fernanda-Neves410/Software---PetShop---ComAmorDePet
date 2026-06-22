package view;

import javax.swing.*;
import java.awt.*;
import model.Funcionario;

public class ViewLogin extends JPanel {
    private JTextField txtMatricula = new JTextField(15);
    // ALTERAÇÃO: Mudamos de JPasswordField para JTextField
    private JTextField txtLogin = new JTextField(15); 
    private JButton btnEntrar = new JButton("Entrar");

    public ViewLogin() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Matrícula:"), gbc);
        gbc.gridx = 1;
        add(txtMatricula, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Login:"), gbc); // Alterado de "Login/Senha" para "Login"
        gbc.gridx = 1;
        add(txtLogin, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        add(btnEntrar, gbc);
        
        btnEntrar.addActionListener(e -> {
            String matricula = txtMatricula.getText();
            String loginDigitado = txtLogin.getText(); // Pega o texto do JTextField

            Funcionario f = ComAmorDePetApp.controle.buscarFuncionarioMatricula(matricula);

            // Agora compara o atributo 'login' do funcionário cadastrado
            if (f != null && f.getLogin().equals(loginDigitado)) {
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window instanceof ComAmorDePetApp) {
                    ((ComAmorDePetApp) window).logar(f);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Matrícula ou Login incorretos!");
            }
        });
    }
}