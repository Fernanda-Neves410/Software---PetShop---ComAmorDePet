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

    public Produto(String codigoBarras, String nome, String fabricante,
                   String categoria, double precoVenda, int quantidadeEstoque) {

        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.fabricante = fabricante;
        this.categoria = categoria;
        setPrecoVenda(precoVenda);
        this.quantidadeEstoque = Math.max(0, quantidadeEstoque);
    }

    // ===== GETTERS E SETTERS =====

    public String getCodigoBarras() {
        return codigoBarras;
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
        if (precoVenda >= 0) {
            this.precoVenda = precoVenda;
        }
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    // ===== REGRAS DE ESTOQUE =====

    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEstoque += quantidade;
        }
    }

    public boolean venderEstoque(int quantidade) {
        if (quantidade <= 0) return false;

        if (this.quantidadeEstoque >= quantidade) {
            this.quantidadeEstoque -= quantidade;
            return true;
        }

        return false;
    }

    // ===== FORMATAÇÃO =====

    public String getPrecoReais() {
        DecimalFormat format = new DecimalFormat("R$#,##0.00");
        return format.format(precoVenda);
    }

    // ===== SAÍDA =====

    public String imprimir() {
        return "Código: " + codigoBarras +
                " / Nome: " + nome +
                " / Fabricante: " + fabricante +
                " / Categoria: " + categoria +
                " / Preço: " + getPrecoReais() +
                " / Estoque: " + quantidadeEstoque;
    }
}