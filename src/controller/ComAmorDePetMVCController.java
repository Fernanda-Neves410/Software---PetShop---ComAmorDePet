package controller;

import model.Cliente;
import model.Funcionario;
import model.Produto;
import model.Servico;
import model.Venda;
import model.Animal; // Import do novo modelo
import model.ComAmorDePetGerenciadora;

public class ComAmorDePetMVCController {
    private ComAmorDePetGerenciadora gerenciadora;

    public ComAmorDePetMVCController() {
        gerenciadora = new ComAmorDePetGerenciadora();
    }

    public boolean salvarCliente(Cliente novoCliente) {
        return gerenciadora.salvarCliente(novoCliente);
    }

    public Cliente buscarClienteCpf(String cpf) {
        return gerenciadora.buscarClienteCpf(cpf);
    }

    public String gerarRelatorioClientes() {
        return gerenciadora.gerarRelatorioClientes();
    }

    public boolean salvarFuncionario(Funcionario novoFuncionario) {
        return gerenciadora.salvarFuncionario(novoFuncionario);
    }

    public Funcionario buscarFuncionarioMatricula(String matricula) {
        return gerenciadora.buscarFuncionarioMatricula(matricula);
    }

    public String gerarRelatorioFuncionarios() {
        return gerenciadora.gerarRelatorioFuncionarios();
    }

    public boolean salvarProduto(Produto novoProduto) {
        return gerenciadora.salvarProduto(novoProduto);
    }

    public Produto buscarProdutoCodigo(String cod) {
        return gerenciadora.buscarProdutoCodigo(cod);
    }

    public String gerarRelatorioProdutos() {
        return gerenciadora.gerarRelatorioProdutos();
    }

    public boolean salvarServico(Servico novoServico) {
        return gerenciadora.salvarServico(novoServico);
    }

    public Servico buscarServicoCodigo(String cod) {
        return gerenciadora.buscarServicoCodigo(cod);
    }

    public String gerarRelatorioServicos() {
        return gerenciadora.gerarRelatorioServicos();
    }

    public void salvarVenda(Venda novaVenda) {
        gerenciadora.salvarVenda(novaVenda);
    }

    public String gerarRelatorioVendas() {
        return gerenciadora.gerarRelatorioVendas();
    }

    public String gerarRelatorioCompleto() {
        return gerenciadora.gerarRelatorioCompleto();
    }

    public boolean salvarAnimal(Animal novoAnimal) {
        return gerenciadora.salvarAnimal(novoAnimal);
    }

    public String gerarRelatorioAnimais() {
        return gerenciadora.gerarRelatorioAnimais();
    }

}