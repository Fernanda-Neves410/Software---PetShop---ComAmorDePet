package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public boolean salvar(Funcionario f) {

        String sql =
            "INSERT INTO funcionario (matricula, cpf, nome, telefone, email, login, permissao) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, f.getMatricula());
            ps.setString(2, f.getCpf());
            ps.setString(3, f.getNome());
            ps.setString(4, f.getTelefone());
            ps.setString(5, f.getEmail());
            ps.setString(6, f.getLogin());
            ps.setInt(7, f.getPermissao());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Funcionario buscarPorMatricula(String matricula) {

        String sql = "SELECT * FROM funcionario WHERE matricula = ?";

        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, matricula);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Funcionario(
                        rs.getString("matricula"),
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("login"),
                        rs.getInt("permissao")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Funcionario> buscarTodos() {

        List<Funcionario> lista = new ArrayList<>();

        String sql = "SELECT * FROM funcionario";

        try (
            Connection conn = ConexaoBD.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Funcionario f = new Funcionario(
                        rs.getString("matricula"),
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("login"),
                        rs.getInt("permissao")
                );

                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public String gerarRelatorio() {

        StringBuilder rel = new StringBuilder();
        rel.append("\n\tRelatório de Funcionários\n");

        List<Funcionario> lista = buscarTodos();

        if (lista.isEmpty()) {
            rel.append(" - - nenhum funcionário cadastrado - - ");
        } else {
            for (Funcionario f : lista) {
                rel.append(f.imprimir()).append("\n");
            }
        }

        return rel.toString();
    }
}