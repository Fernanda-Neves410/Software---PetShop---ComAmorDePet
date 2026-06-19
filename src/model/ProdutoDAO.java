package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public ProdutoDAO() {
    }

    public boolean salvar(Produto novoProduto) {

        String sql =
            "INSERT INTO produto " +
            "(codigo_barras, nome, fabricante, categoria, preco_venda, quantidade_estoque) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, novoProduto.getCodigoBarras());
            ps.setString(2, novoProduto.getNome());
            ps.setString(3, novoProduto.getFabricante());
            ps.setString(4, novoProduto.getCategoria());
            ps.setDouble(5, novoProduto.getPrecoVenda());
            ps.setInt(6, novoProduto.getQuantidadeEstoque());

            ps.executeUpdate();

            System.out.println("Produto salvo.");

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Produto> buscarTodos() {

        List<Produto> listaProdutos = new ArrayList<>();

        String sql = "SELECT * FROM produto";

        try (
            Connection conn = ConexaoBD.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Produto p = new Produto(
                        rs.getString("codigo_barras"),
                        rs.getString("nome"),
                        rs.getString("fabricante"),
                        rs.getString("categoria"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("quantidade_estoque")
                );

                listaProdutos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProdutos;
    }

    public Produto buscarPorCodigoBarras(String cod) {

        cod = retiraPontuacao(cod);

        String sql =
                "SELECT * FROM produto WHERE codigo_barras = ?";

        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

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

    public String gerarRelatorio() {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n\tRelatório de Produtos\n");

        List<Produto> listaProdutos = buscarTodos();

        if (listaProdutos.isEmpty()) {

            relatorio.append(
                    " - - não há produtos cadastrados - - "
            );

        } else {

            for (Produto produto : listaProdutos) {

                relatorio.append(
                        produto.imprimir()
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