package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public boolean salvar(Produto p) {

        String sql = "INSERT INTO produto " +
                "(codigo_barras, nome, fabricante, categoria, preco_venda, quantidade_estoque) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getCodigoBarras());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getFabricante());
            ps.setString(4, p.getCategoria());
            ps.setDouble(5, p.getPrecoVenda());
            ps.setInt(6, p.getQuantidadeEstoque());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Produto> buscarTodos() {

        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT * FROM produto";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Produto(
                        rs.getString("codigo_barras"),
                        rs.getString("nome"),
                        rs.getString("fabricante"),
                        rs.getString("categoria"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("quantidade_estoque")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Produto buscarPorCodigoBarras(String cod) {

        String sql = "SELECT * FROM produto WHERE codigo_barras = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cod);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Produto(
                        rs.getString("codigo_barras"),
                        rs.getString("nome"),
                        rs.getString("fabricante"),
                        rs.getString("categoria"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("quantidade_estoque")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean atualizarEstoque(String cod, int novaQuantidade) {

        String sql = "UPDATE produto SET quantidade_estoque = ? WHERE codigo_barras = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, novaQuantidade);
            ps.setString(2, cod);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean venderEstoque(Connection conn, String cod, int quantidade) {

        String sql = "UPDATE produto SET quantidade_estoque = quantidade_estoque - ? " +
                    "WHERE codigo_barras = ? AND quantidade_estoque >= ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantidade);
            ps.setString(2, cod);
            ps.setInt(3, quantidade);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String gerarRelatorio() {

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("\n\tRelatório de Produtos\n");

        List<Produto> lista = buscarTodos();

        if (lista.isEmpty()) {
            relatorio.append(" - - não há produtos cadastrados - - ");
        } else {
            for (Produto p : lista) {
                relatorio.append(p.imprimir()).append("\n");
            }
        }

        return relatorio.toString();
    }

    public boolean atualizar(Produto p) {

        String sql = "UPDATE produto SET nome=?, fabricante=?, categoria=?, preco_venda=?, quantidade_estoque=? WHERE codigo_barras=?";

        try (Connection conn = ConexaoBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getFabricante());
            ps.setString(3, p.getCategoria());
            ps.setDouble(4, p.getPrecoVenda());
            ps.setInt(5, p.getQuantidadeEstoque());
            ps.setString(6, p.getCodigoBarras());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(String cod) {

        String sqlCheck = "SELECT COUNT(*) FROM item_venda WHERE nome_item = (SELECT nome FROM produto WHERE codigo_barras = ?)";

        String sqlDelete = "DELETE FROM produto WHERE codigo_barras = ?";

        try (Connection conn = ConexaoBD.conectar()) {

            // 1. verificar se existe venda
            try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {

                psCheck.setString(1, cod);

                ResultSet rs = psCheck.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Produto possui histórico de vendas. Não pode ser excluído.");
                    return false;
                }
            }

            // 2. excluir produto
            try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {

                ps.setString(1, cod);

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}