package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    public void salvar(Venda venda) {

        String sqlVenda =
                "INSERT INTO VENDA " +
                "(cpf_cliente, matricula_funcionario, data, forma_pagamento, total) " +
                "VALUES (?, ?, ?, ?, ?)";

        String sqlItem =
                "INSERT INTO ITEM_VENDA " +
                "(venda_id, tipo, nome_item, quantidade, valor_unitario, subtotal) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = ConexaoBD.conectar()
        ) {

            PreparedStatement psVenda =
                    conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);

            psVenda.setString(1, venda.getCliente().getCpf());
            psVenda.setString(2, venda.getFuncionario().getMatricula());
            psVenda.setString(3, venda.retornaData());
            psVenda.setString(4, venda.getFormaPagamento());
            psVenda.setDouble(5, venda.getTotal());

            psVenda.executeUpdate();

            ResultSet rs = psVenda.getGeneratedKeys();

            if (rs.next()) {
                venda.setId(rs.getInt(1));
            }

            PreparedStatement psItem =
                    conn.prepareStatement(sqlItem);

            for (ItemVenda item : venda.getItensVenda()) {

                psItem.setInt(1, venda.getId());

                psItem.setInt(2, item.getTipo());

                if (item.getTipo() == 1) {
                    psItem.setString(
                            3,
                            item.getProdutoVendido().getNome()
                    );
                } else {
                    psItem.setString(
                            3,
                            item.getServicoContratado().getNome()
                    );
                }

                psItem.setInt(4, item.getQuantidade());
                psItem.setDouble(5, item.getValorUnitario());
                psItem.setDouble(6, item.getSubTotal());

                psItem.executeUpdate();
            }

            System.out.println("Venda salva no banco.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Venda> buscarTodos() {

        List<Venda> vendas = new ArrayList<>();

        String sql = "SELECT * FROM VENDA";

        try (
            Connection conn = ConexaoBD.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Venda venda = new Venda();

                venda.setId(rs.getInt("id"));
                venda.setFormaPagamento(
                        rs.getString("forma_pagamento")
                );

                vendas.add(venda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendas;
    }

    public String gerarRelatorio() {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n\tRelatório de Vendas\n");

        List<Venda> vendas = buscarTodos();

        if (vendas.isEmpty()) {

            relatorio.append(
                    " - - não há vendas registradas - - "
            );

        } else {

            for (Venda venda : vendas) {
                relatorio.append(
                        "Venda #"
                        + venda.getId()
                        + " - "
                        + venda.getFormaPagamento()
                        + "\n"
                );
            }
        }

        return relatorio.toString();
    }
}