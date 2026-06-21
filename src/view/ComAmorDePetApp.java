package view;

import controller.ComAmorDePetMVCController;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ComAmorDePetApp extends javax.swing.JFrame {

    static protected ComAmorDePetMVCController controle;

    /** Creates new form ComAmorDePet */
    public ComAmorDePetApp() {
        controle = new ComAmorDePetMVCController();
        initComponents();
        setSize(1000, 700);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Iniciando componentes">
    private void initComponents() {
        this.setTitle("Brinque Feliz");

        String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();

        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
        }

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuHome = new javax.swing.JMenu();
        jMenuVenda = new javax.swing.JMenu();
        jMenuCliente = new javax.swing.JMenu();
        jMenuProduto = new javax.swing.JMenu();
        jMenuRelatorio = new javax.swing.JMenu();
        jMenuServico = new javax.swing.JMenu(); // 🔥 ADICIONADO
        jMenuSair = new javax.swing.JMenu();

        painelCliente = new ViewCliente();
        painelRelatorio = new ViewRelatorio();
        painelProduto = new ViewProduto();
        painelHome = new ViewBoasVindas();
        painelVenda = new ViewVenda();
        painelServico = new ViewServico(); // 🔥 ADICIONADO

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuBar1.setName("jMenuBar1");

        jMenuHome.setText("Home");
        jMenuHome.setName("jMenuHome");
        jMenuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuHomeMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuHome);

        jMenuVenda.setText("Vendas");
        jMenuVenda.setName("jMenuVenda");
        jMenuVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuVendaMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuVenda);

        jMenuCliente.setText("Clientes");
        jMenuCliente.setName("jMenuCliente");
        jMenuCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuClienteMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuCliente);

        jMenuProduto.setText("Produtos");
        jMenuProduto.setName("jMenuProduto");
        jMenuProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuProdutoMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuProduto);

        
        jMenuServico.setText("Serviços");
        jMenuServico.setName("jMenuServico");
        jMenuServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                painelHome.setVisible(false);
                painelCliente.setVisible(false);
                painelProduto.setVisible(false);
                painelRelatorio.setVisible(false);
                painelVenda.setVisible(false);
                painelServico.setVisible(true);
            }
        });
        jMenuBar1.add(jMenuServico);

        jMenuRelatorio.setText("Relatório");
        jMenuRelatorio.setName("jMenuRelatorio");
        jMenuRelatorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuRelatorioMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuRelatorio);

        jMenuSair.setText("Sair");
        jMenuSair.setName("jMenuSair");
        jMenuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSairMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        painelCliente.setVisible(false);
        painelRelatorio.setVisible(false);
        painelProduto.setVisible(false);
        painelHome.setVisible(true);
        painelVenda.setVisible(false);
        painelServico.setVisible(false); // 🔥 ADICIONADO

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(painelVenda)
                .addComponent(painelHome)
                .addComponent(painelCliente)
                .addComponent(painelRelatorio)
                .addComponent(painelProduto)
                .addComponent(painelServico) // 🔥 ADICIONADO
                .addContainerGap(121, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(painelVenda)
                .addComponent(painelHome)
                .addComponent(painelCliente)
                .addComponent(painelRelatorio)
                .addComponent(painelProduto)
                .addComponent(painelServico) // 🔥 ADICIONADO
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void jMenuSairMouseClicked(java.awt.event.MouseEvent evt) {
        int valor = 0;
        valor = JOptionPane.showConfirmDialog(null, "Fim do sistema?",
                "Finaliza sistema", JOptionPane.YES_NO_OPTION);
        if (valor == 0) {
            System.exit(0);
        }
    }

    private void jMenuHomeMouseClicked(java.awt.event.MouseEvent evt) {
        painelHome.setVisible(true);
        painelCliente.setVisible(false);
        painelProduto.setVisible(false);
        painelRelatorio.setVisible(false);
        painelVenda.setVisible(false);
        painelServico.setVisible(false);
    }

    private void jMenuClienteMouseClicked(java.awt.event.MouseEvent evt) {
        painelHome.setVisible(false);
        painelCliente.setVisible(true);
        painelProduto.setVisible(false);
        painelRelatorio.setVisible(false);
        painelVenda.setVisible(false);
        painelServico.setVisible(false);
    }

    private void jMenuRelatorioMouseClicked(java.awt.event.MouseEvent evt) {
        painelHome.setVisible(false);
        painelCliente.setVisible(false);
        painelProduto.setVisible(false);
        painelRelatorio.setVisible(true);
        painelVenda.setVisible(false);
        painelServico.setVisible(false);
    }

    private void jMenuProdutoMouseClicked(java.awt.event.MouseEvent evt) {
        painelHome.setVisible(false);
        painelCliente.setVisible(false);
        painelProduto.setVisible(true);
        painelRelatorio.setVisible(false);
        painelVenda.setVisible(false);
        painelServico.setVisible(false);
    }

    private void jMenuVendaMouseClicked(java.awt.event.MouseEvent evt) {
        painelHome.setVisible(false);
        painelCliente.setVisible(false);
        painelProduto.setVisible(false);
        painelRelatorio.setVisible(false);
        painelVenda.setVisible(true);
        painelServico.setVisible(false);
    }

    static protected void mostraMensagem(String texto, String titulo) {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ComAmorDePetApp().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuProduto;
    private javax.swing.JMenu jMenuCliente;
    private javax.swing.JMenu jMenuRelatorio;
    private javax.swing.JMenu jMenuSair;
    private javax.swing.JMenu jMenuVenda;
    private javax.swing.JMenu jMenuHome;
    private javax.swing.JMenu jMenuServico;

    private JPanel painelCliente;
    private JPanel painelRelatorio;
    private JPanel painelProduto;
    private JPanel painelHome;
    private JPanel painelVenda;
    private JPanel painelServico; 
}