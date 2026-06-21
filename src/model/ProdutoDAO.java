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
}