package model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Servico implements Serializable {
	private String codigoServico;
	private String nome;
	private double precoServico;
	
	public Servico(String codigoServico, String nome, double preco) {
		setCodigoServico(codigoServico);
		setNome(nome);
		setPrecoServico(preco);
	}
	
	public String getCodigoServico() {
		return codigoServico;
	}
	
	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getPrecoServico() {
		return precoServico;
	}
	
	public void setPrecoServico(double precoServico) {
		if(precoServico >= 0.00) {
			this.precoServico = precoServico;
		}
	}
	
	public String getPrecoReais() {
		String valorReais;
		DecimalFormat format = new DecimalFormat("R$#,##0.00");
		valorReais = format.format(precoServico);
		return valorReais;
	}
	
    public String imprimir() {
        return "Código de Serviço: " + getCodigoServico() + " / Nome: " + getNome() + 
        		" / Preço: " + getPrecoReais();
    }

}
