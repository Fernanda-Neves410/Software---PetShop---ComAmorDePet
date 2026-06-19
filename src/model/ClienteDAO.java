package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public ClienteDAO() {
    }

    public boolean salvar(Cliente novoCliente) {

        String sql =
            "INSERT INTO cliente(cpf,nome,telefone,endereco) VALUES(?,?,?,?)";

        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, retiraPontuacao(novoCliente.getCpf()));
            ps.setString(2, novoCliente.getNome());
            ps.setString(3, novoCliente.getTelefone());
            ps.setString(4, novoCliente.getEndereco());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> buscarTodos() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try (
            Connection conn = ConexaoBD.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Cliente c = new Cliente(
                        rs.getString("cpf"),
                        rs.getString("nome")
                );

                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));

                clientes.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public Cliente buscarPorCpf(String cpf) {

        cpf = retiraPontuacao(cpf);

        String sql =
                "SELECT * FROM cliente WHERE cpf = ?";

        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, cpf);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Cliente c = new Cliente(
                        rs.getString("cpf"),
                        rs.getString("nome")
                );

                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));

                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String gerarRelatorio() {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\tRelatório de Clientes\n");

        List<Cliente> clientes = buscarTodos();

        if (clientes.isEmpty()) {

            relatorio.append(" - - não há clientes cadastrados - - ");

        } else {

            for (Cliente c : clientes) {
                relatorio.append(c.imprimir()).append("\n");
            }

        }

        return relatorio.toString();
    }

    private String retiraPontuacao(String texto) {

        texto = texto.replace(".", "");
        texto = texto.replace("-", "");
        texto = texto.replace("/", "");
        texto = texto.replace("*", "");

        return texto;
    }
}