package model;

import java.io.Serializable;

public class Cliente implements Serializable {
	private String cpf;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
//	private Venda venda;

	public Cliente(String cpf, String nome, String endereco, String telefone, String email) {
		setCpf(cpf);
		setNome(nome);
		setEndereco(endereco);
		setTelefone(telefone);
		setEmail(email);
	}

	public Cliente(String cpf, String nome) {
		setCpf(cpf);
		setNome(nome);
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
    public String imprimir() {
        return "CPF: " + getCpf() + " / Nome: " + getNome() + " / Endereço: " + getEndereco() + 
        		" / Telefone: " + getTelefone() + " / Email: " + getEmail();
    }
	
}
