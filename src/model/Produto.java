package model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Produto implements Serializable {
	private String codigoBarras;
	private String nome;
	private String fabricante;
	private String categoria;
	private double precoVenda;
	private int quantidadeEstoque;
	
	public Produto(String codigoBarras, String nome, String fabricante, String categoria, double precoVenda,
			int quantidadeEstoque) {
		setCodigoBarras(codigoBarras);
		setNome(nome);
		setFabricante(fabricante);
		setCategoria(categoria);
		setPrecoVenda(precoVenda);
		atualizarEstoque(quantidadeEstoque);
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		if(precoVenda >= 0.00) {
			this.precoVenda = precoVenda;			
		}
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void atualizarEstoque(int quantidadeEstoque) {
		if(quantidadeEstoque >= 0) {
			this.quantidadeEstoque = quantidadeEstoque;
		}
	}
	
	public boolean venderEstoque(int quantidadeVendida) {
		int novoEstoque = this.getQuantidadeEstoque() - quantidadeVendida;
		if(novoEstoque >= 0) {
			this.atualizarEstoque(novoEstoque);
			return true;
		} else {
			return false;
		}
	}
	
	public String getPrecoReais() {
		String valorReais;
		DecimalFormat format = new DecimalFormat("R$#,##0.00");
		valorReais = format.format(precoVenda);
		return valorReais;
	}
	
    public String imprimir() {
        return "Código de Barras: " + getCodigoBarras() + " / Nome: " + getNome() + 
        		" / Fabricante: " + getFabricante() + " / Categoria: " + getCategoria() + 
        		" / Preço: " + getPrecoReais() + " / Estoque: " + getQuantidadeEstoque();
    }
		
}
