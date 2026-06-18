package model;

import java.io.Serializable;

public class Funcionario implements Serializable {
	private String matricula;
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private String login;
	private int permissao;
//	private Venda[] venda;
	
	public Funcionario(String matricula, String cpf, String nome, String telefone, 
			String email, String login, int permissao) {
		setMatricula(matricula);
		setCpf(cpf);
		setNome(nome);
		setTelefone(telefone);
		setEmail(email);
		setLogin(login);
		setPermissao(permissao);
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getPermissao() {
		return permissao;
	}

	public void setPermissao(int permissao) {
		this.permissao = permissao;
	}
	
    public String imprimir() {
        return "Matrícula:" + getMatricula() + " / CPF: " + getCpf() + 
        		" / Nome: " + getNome() + " / Telefone: " + getTelefone() + 
        		" / Email: " + getEmail() + " / Login: " + getLogin() + 
        		" / Permissão: " + getPermissao();
    }

}
