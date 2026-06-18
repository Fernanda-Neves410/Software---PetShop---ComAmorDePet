package model;

public class ComAmorDePetGerenciadora {
    private final ClienteDAO clienteDAO;
    private final FuncionarioDAO funcionarioDAO;
    private final ProdutoDAO produtoDAO;
    private final ServicoDAO servicoDAO;
    private final VendaDAO vendaDAO;
    
    public ComAmorDePetGerenciadora() {
    	clienteDAO = new ClienteDAO();
    	funcionarioDAO = new FuncionarioDAO();
    	produtoDAO = new ProdutoDAO();
    	servicoDAO = new ServicoDAO();
    	vendaDAO = new VendaDAO();
    }

    public boolean salvarCliente(Cliente novoCliente) {
        return clienteDAO.salvar(novoCliente);
    }

    public Cliente buscarClienteCpf(String cpf) {
        return clienteDAO.buscarPorCpf(cpf);
    }

    public String gerarRelatorioClientes() {
        return clienteDAO.gerarRelatorio();
    }

    public boolean salvarFuncionario(Funcionario novoFuncionario) {
        return funcionarioDAO.salvar(novoFuncionario);
    }

    public Funcionario buscarFuncionarioMatricula(String matricula) {
        return funcionarioDAO.buscarPorMatricula(matricula);
    }

    public String gerarRelatorioFuncionarios() {
        return funcionarioDAO.gerarRelatorio();
    }

    public boolean salvarProduto(Produto novoProduto) {
        return produtoDAO.salvar(novoProduto);
    }

    public Produto buscarProdutoCodigo(String cod) {
        return produtoDAO.buscarPorCodigoBarras(cod);
    }

    public String gerarRelatorioProdutos() {
        return produtoDAO.gerarRelatorio();
    }

    public boolean salvarServico(Servico novoServico) {
        return servicoDAO.salvar(novoServico);
    }

    public Servico buscarServicoCodigo(String cod) {
        return servicoDAO.buscarPorCodigoServico(cod);
    }

    public String gerarRelatorioServicos() {
        return servicoDAO.gerarRelatorio();
    }
    
    public void salvarVenda(Venda novaVenda) {
        vendaDAO.salvar(novaVenda);
    }

    public String gerarRelatorioVendas() {
        return vendaDAO.gerarRelatorio();
    }

    public String gerarRelatorioCompleto() {
        String relatorio = new String();
        relatorio += "\tRelatório Completo\n";
        relatorio += "\n" + clienteDAO.gerarRelatorio();
        relatorio += "\n" + funcionarioDAO.gerarRelatorio();
        relatorio += "\n" + produtoDAO.gerarRelatorio();
        relatorio += "\n" + servicoDAO.gerarRelatorio();
        relatorio += "\n" + vendaDAO.gerarRelatorio();
        return relatorio;
    }

}
