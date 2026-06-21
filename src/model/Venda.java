package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Venda implements Serializable {

    private int id;
    private Date dataVenda;
    private Funcionario funcionario;
    private Cliente cliente;
    private List<ItemVenda> itensVenda;
    private String formaPagamento;

    public Venda(Funcionario funcionario) {
        if (funcionario != null && (funcionario.getPermissao() == 1 || funcionario.getPermissao() == 2)) {
            setFuncionario(funcionario);
        }
        this.dataVenda = new Date();
        this.itensVenda = new ArrayList<>();
    }

    public Venda() {
        this.dataVenda = new Date();
        this.itensVenda = new ArrayList<>();
    }

    // ===================== GETTERS / SETTERS =====================

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    // ===================== REGRA DE ESTOQUE =====================

    public boolean temEstoque(Produto produto, int quantidade) {
        return produto != null && quantidade > 0 &&
               produto.getQuantidadeEstoque() >= quantidade;
    }

    // ===================== INSERÇÃO DE PRODUTOS =====================

    public boolean inserirProduto(Produto produto, int quantidade) {

        if (!temEstoque(produto, quantidade)) {
            return false;
        }

        ItemVenda item = new ItemVenda(produto, quantidade);
        this.itensVenda.add(item);

        return true;
    }

    public boolean inserirProduto(Produto produto) {
        return inserirProduto(produto, 1);
    }

    // ===================== SERVIÇOS =====================

    public void inserirServico(Servico servico, int quantidade) {
        ItemVenda item = new ItemVenda(servico, quantidade);
        this.itensVenda.add(item);
    }

    public void inserirServico(Servico servico) {
        ItemVenda item = new ItemVenda(servico);
        this.itensVenda.add(item);
    }

    // ===================== DATA =====================

    public String retornaData() {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatoData.format(this.dataVenda);
    }

    // ===================== TOTAL =====================

    public double getTotal() {
        double total = 0;

        for (ItemVenda item : itensVenda) {
            total += item.getSubTotal();
        }

        return total;
    }

    public int getQuantidadeItensVendidos() {
        int quant = 0;

        for (ItemVenda item : itensVenda) {
            quant += item.getQuantidade();
        }

        return quant;
    }

    // ===================== FORMATAÇÃO =====================

    private String formataValor(double valor) {
        DecimalFormat format = new DecimalFormat("R$#,##0.00");
        return format.format(valor);
    }

    public String getTotalReais() {
        return formataValor(getTotal());
    }

    // ===================== NOTA / RELATÓRIO =====================

    public String gerarNota() {

        String nota = "";

        nota += "Data e hora da compra: " + retornaData() + "\n";

        if (cliente != null) {
            nota += "Cliente: " + cliente.getNome() + " - CPF: " + cliente.getCpf() + "\n\n";
        } else {
            nota += "Cliente não identificado\n\n";
        }

        nota += "Lista de itens vendidos:\n";

        Iterator<ItemVenda> ite = itensVenda.iterator();
        int i = 0;

        while (ite.hasNext()) {
            ItemVenda item = ite.next();
            i++;

            nota += i + " - ";

            if (item.getTipo() == 1) {
                nota += item.getProdutoVendido().getNome() + " - ";
            } else {
                nota += item.getServicoContratado().getNome() + " - ";
            }

            nota += formataValor(item.getValorUnitario()) + " - ";
            nota += item.getQuantidade() + " - ";
            nota += formataValor(item.getSubTotal()) + "\n";
        }

        nota += "\nQuantidade de itens: " + getQuantidadeItensVendidos();
        nota += "\nValor total: " + formataValor(getTotal());
        nota += "\nForma de pagamento: " + getFormaPagamento();

        return nota;
    }

    public String imprimir() {

        String relatorio = "";

        relatorio += "Data: " + retornaData();

        if (cliente != null) {
            relatorio += " - Cliente: " + cliente.getNome();
            relatorio += " - CPF: " + cliente.getCpf();
        } else {
            relatorio += " - Cliente não identificado";
        }

        relatorio += " - Itens vendidos: " + getQuantidadeItensVendidos();
        relatorio += " - Valor total: " + getTotalReais();
        relatorio += " - Forma de pagamento: " + getFormaPagamento();

        return relatorio;
    }
}