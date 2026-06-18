package model;

import java.io.Serializable;

public class ItemVenda implements Serializable {
	private Produto produtoVendido;
	private Servico servicoContratado;
	private int quantidade;
	private int tipo;
	private double valorUnitario;
	
	public ItemVenda(Produto produtoVendido, int quantidade) {
		setProdutoVendido(produtoVendido);
		setQuantidade(quantidade);
		setTipo(1);
		setValorUnitario(produtoVendido.getPrecoVenda());
	}
	
	public ItemVenda(Produto produtoVendido) {
		setProdutoVendido(produtoVendido);
		setQuantidade(1);
		setTipo(1);
		setValorUnitario(produtoVendido.getPrecoVenda());
	}
	
	public ItemVenda(Servico servicoContratado, int quantidade) {
		setServicoContratado(servicoContratado);
		setQuantidade(quantidade);
		setTipo(2);
		setValorUnitario(servicoContratado.getPrecoServico());
	}
	
	public ItemVenda(Servico servicoContratado) {
		setServicoContratado(servicoContratado);
		setQuantidade(1);
		setTipo(2);
		setValorUnitario(servicoContratado.getPrecoServico());
	}

	public Produto getProdutoVendido() {
		return produtoVendido;
	}

	public void setProdutoVendido(Produto produtoVendido) {
		this.produtoVendido = produtoVendido;
	}

	public Servico getServicoContratado() {
		return servicoContratado;
	}

	public void setServicoContratado(Servico servicoContratado) {
		this.servicoContratado = servicoContratado;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

    public double getSubTotal() {
        return getValorUnitario() * getQuantidade();
    }

}
