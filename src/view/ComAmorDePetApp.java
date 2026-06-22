package view;

import controller.ComAmorDePetMVCController;
import model.Funcionario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComAmorDePetApp extends javax.swing.JFrame {

    static protected ComAmorDePetMVCController controle;
    private int nivelUsuarioLogado; // Armazena o nível para controle de acesso
    
    // Painéis da aplicação
    private JPanel containerPrincipal = new JPanel(new CardLayout());
    private ViewLogin painelLogin = new ViewLogin();
    private ViewCliente painelCliente = new ViewCliente();
    private ViewAnimal painelAnimal = new ViewAnimal();
    private ViewProduto painelProduto = new ViewProduto();
    private ViewServico painelServico = new ViewServico();
    private ViewVenda painelVenda = new ViewVenda();
    private ViewRelatorio painelRelatorio = new ViewRelatorio();
    private ViewBoasVindas painelHome = new ViewBoasVindas();
    private ViewFuncionario painelFuncionario = new ViewFuncionario();

    public ComAmorDePetApp() {
        controle = new ComAmorDePetMVCController();
        initComponents();
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        containerPrincipal.add(painelLogin, "login");
        containerPrincipal.add(painelHome, "home");
        containerPrincipal.add(painelCliente, "cliente");
        containerPrincipal.add(painelAnimal, "animal");
        containerPrincipal.add(painelProduto, "produto");
        containerPrincipal.add(painelServico, "servico");
        containerPrincipal.add(painelVenda, "venda");
        containerPrincipal.add(painelRelatorio, "relatorio");
        containerPrincipal.add(painelFuncionario, "funcionario");
        
        setContentPane(containerPrincipal);
        ((CardLayout) containerPrincipal.getLayout()).show(containerPrincipal, "login");
    }

    public void logar(Funcionario f) {
        this.nivelUsuarioLogado = f.getPermissao();
        ((CardLayout) containerPrincipal.getLayout()).show(containerPrincipal, "home");
        jMenuBar1.setVisible(true);
        aplicarPermissoes(nivelUsuarioLogado);
        
        revalidate();
        repaint();
        JOptionPane.showMessageDialog(this, "Bem-vindo(a), " + f.getNome());
    }

    // Método "porteiro": valida o acesso antes de trocar
    private void trocarTela(String nomeTela) {
        if (nomeTela.equals("funcionario") && nivelUsuarioLogado != 1) {
            mostraMensagem("Acesso negado: Apenas Administradores.", "Erro");
            return;
        }
        if ((nomeTela.equals("cliente") || nomeTela.equals("animal") || nomeTela.equals("produto")) 
             && nivelUsuarioLogado == 3) {
            mostraMensagem("Acesso negado: Nível de Cuidador restrito.", "Erro");
            return;
        }
        ((CardLayout) containerPrincipal.getLayout()).show(containerPrincipal, nomeTela);
    }

    private void aplicarPermissoes(int nivel) {
        jMenuFuncionario.setVisible(nivel == 1);
        jMenuProduto.setVisible(nivel == 1 || nivel == 2);
        jMenuCliente.setVisible(nivel == 1 || nivel == 2);
        jMenuAnimal.setVisible(nivel == 1 || nivel == 2);
        
        jMenuVenda.setVisible(true);
        jMenuServico.setVisible(true);
        jMenuRelatorio.setVisible(true);
        jMenuSair.setVisible(true);
    }

    private void initComponents() {
        this.setTitle("Com Amor de Pet - Gestão de PetShop");

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuHome = new javax.swing.JMenu("Início");
        jMenuVenda = new javax.swing.JMenu("Venda");
        jMenuCliente = new javax.swing.JMenu("Cliente");
        jMenuAnimal = new javax.swing.JMenu("Animal");
        jMenuProduto = new javax.swing.JMenu("Produto");
        jMenuRelatorio = new javax.swing.JMenu("Relatório");
        jMenuServico = new javax.swing.JMenu("Serviço");
        jMenuFuncionario = new javax.swing.JMenu("Funcionário");
        jMenuSair = new javax.swing.JMenu("Sair");

        jMenuBar1.setVisible(false);
        
        // Eventos de clique protegidos pelo método trocarTela
        jMenuHome.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { trocarTela("home"); } });
        jMenuCliente.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { trocarTela("cliente"); } });
        jMenuAnimal.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { trocarTela("animal"); } });
        jMenuProduto.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { trocarTela("produto"); } });
        jMenuServico.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { trocarTela("servico"); } });
        jMenuVenda.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { trocarTela("venda"); } });
        jMenuRelatorio.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { trocarTela("relatorio"); } });
        jMenuFuncionario.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { trocarTela("funcionario"); } });
        jMenuSair.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { System.exit(0); } });
        
        jMenuBar1.add(jMenuHome);
        jMenuBar1.add(jMenuVenda);
        jMenuBar1.add(jMenuCliente);
        jMenuBar1.add(jMenuAnimal);
        jMenuBar1.add(jMenuProduto);
        jMenuBar1.add(jMenuServico);
        jMenuBar1.add(jMenuRelatorio);
        jMenuBar1.add(jMenuFuncionario);
        jMenuBar1.add(jMenuSair);
        
        setJMenuBar(jMenuBar1);
    }

    static protected void mostraMensagem(String texto, String titulo) {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String args[]) {
        model.CriarTabelas.criarTabelas();
        java.awt.EventQueue.invokeLater(() -> new ComAmorDePetApp().setVisible(true));
    }

    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuProduto, jMenuCliente, jMenuAnimal, jMenuRelatorio, 
                           jMenuSair, jMenuVenda, jMenuHome, jMenuServico, jMenuFuncionario;
}