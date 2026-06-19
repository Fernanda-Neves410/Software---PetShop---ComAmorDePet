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
    	if (funcionario.getPermissao() == 1 || funcionario.getPermissao() == 2) {
    		setFuncionario(funcionario);
            this.dataVenda = new Date();
            itensVenda = new ArrayList<ItemVenda>();
    	}
    }
	
    public Venda() { // Remover quando a questão da introdução de funcionário for resolvida
    	this.dataVenda = new Date();
        itensVenda = new ArrayList<ItemVenda>();
    }   
    
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

	public boolean inserirProduto(Produto produto, int quantidade) {
        if (produto.venderEstoque(quantidade)) {
            ItemVenda item = new ItemVenda(produto, quantidade);
            this.itensVenda.add(item);
            
            return true;
        } else {
            return false;
        }
    }
    
    public boolean inserirProduto(Produto produto) {
        if (produto.venderEstoque(1)) {
            ItemVenda item = new ItemVenda(produto);
            this.itensVenda.add(item);
            
            return true;
        } else {
            return false;
        }
    }
    
    public void inserirServico(Servico servico, int quantidade) {
            ItemVenda item = new ItemVenda(servico, quantidade);
            this.itensVenda.add(item);
    }
    
    public void inserirServico(Servico servico) {
            ItemVenda item = new ItemVenda(servico);
            this.itensVenda.add(item);
    }
    
    public String gerarNota() {
        String nota = new String();
        ItemVenda item;
        nota += "Data e hora da compra: " + retornaData() + "\n";
        nota += "Cliente: " + cliente.getNome() + " - CPF: " + cliente.getCpf() + "\n\n";
        nota += "Lista de itens vendidos: \n";

        Iterator<ItemVenda> ite = itensVenda.iterator();
        int i = 0;

        while (ite.hasNext()) {
            item = (ItemVenda) ite.next();
            i++;
            nota += i + " - ";
            switch(item.getTipo()) {
            	case 1:
                	nota += item.getProdutoVendido().getNome() + " - ";
            		break;
            	case 2:
                	nota += item.getServicoContratado().getNome() + " - ";
            		break;
            }
            nota += formataValor(item.getValorUnitario()) + " - ";
            nota += item.getQuantidade() + " - ";
            nota += formataValor(item.getSubTotal()) + "\n";
        }

        nota += "Quantidade de itens: " + getQuantidadeItensVendidos() + "\n\n";
        nota += "Valor total: " + formataValor(getTotal()) + "\n";
        nota += "Forma de pagamento: " + getFormaPagamento() + "\n";

        return nota;
    }
    
    public String retornaData() {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return formatoData.format(this.dataVenda);
    }
    
    public double getTotal() {
        double total = 0;
        Iterator<ItemVenda> ite = itensVenda.iterator();

        while (ite.hasNext()) {
            ItemVenda item;
            item = (ItemVenda) ite.next();
            total += item.getSubTotal();
        }

        return total;
    }
    
    public int getQuantidadeItensVendidos() {
        int quant = 0;
        Iterator<ItemVenda> ite = itensVenda.iterator();

        while (ite.hasNext()) {
            ItemVenda item;
            item = (ItemVenda) ite.next();
            quant += item.getQuantidade();
        }

        return quant;
    }
    
    private String formataValor(double valor) {
        String svalor;
        DecimalFormat format = new DecimalFormat("R$#,##0.00");
        svalor = format.format(valor);
        return svalor;
    }
    
    public String imprimir() {
        String relatorio = "";

        relatorio += "Data: " + retornaData();
        relatorio += " - Cliente: " + cliente.getNome();
        relatorio += " - CPF: " + cliente.getCpf();
        relatorio += " - Itens vendidos: " + getQuantidadeItensVendidos();
        relatorio += " - Valor total: " + getTotalReais();
        relatorio += " - Forma de pagamento: " + getFormaPagamento();
        return relatorio;
    }
    
    public String getTotalReais() {
        return formataValor(getTotal());
    }

}
