package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    public ServicoDAO() {
    }

    public boolean salvar(Servico novoServico) {

        String sql =
            "INSERT INTO servico " +
            "(codigo_servico, nome, preco_servico) " +
            "VALUES (?, ?, ?)";

        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, novoServico.getCodigoServico());
            ps.setString(2, novoServico.getNome());
            ps.setDouble(3, novoServico.getPrecoServico());

            ps.executeUpdate();

            System.out.println("Serviço salvo.");

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Servico> buscarTodos() {

        List<Servico> listaServicos = new ArrayList<>();

        String sql = "SELECT * FROM servico";

        try (
            Connection conn = ConexaoBD.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Servico s = new Servico(
                        rs.getString("codigo_servico"),
                        rs.getString("nome"),
                        rs.getDouble("preco_servico")
                );

                listaServicos.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaServicos;
    }

    public Servico buscarPorCodigoServico(String cod) {

        cod = retiraPontuacao(cod);

        String sql =
                "SELECT * FROM servico WHERE codigo_servico = ?";

        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, cod);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Servico(
                        rs.getString("codigo_servico"),
                        rs.getString("nome"),
                        rs.getDouble("preco_servico")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String gerarRelatorio() {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n\tRelatório de Serviços\n");

        List<Servico> listaServicos = buscarTodos();

        if (listaServicos.isEmpty()) {

            relatorio.append(
                    " - - não há serviços cadastrados - - "
            );

        } else {

            for (Servico servico : listaServicos) {

                relatorio.append(
                        servico.imprimir()
                ).append("\n");
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