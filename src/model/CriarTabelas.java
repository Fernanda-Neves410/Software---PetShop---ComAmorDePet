package model;

import java.sql.Connection;
import java.sql.Statement;

public class CriarTabelas {

    public static void criarTabelas() {

        String sqlCliente =
                "CREATE TABLE IF NOT EXISTS CLIENTE (" +
                "cpf TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "endereco TEXT," +
                "cidade TEXT," +
                "estado TEXT," +
                "telefone TEXT," +
                "email TEXT" +
                ");";

        String sqlVenda =
                "CREATE TABLE IF NOT EXISTS VENDA (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cpf_cliente TEXT," +
                "matricula_funcionario TEXT," +
                "data TEXT," +
                "forma_pagamento TEXT," +
                "total REAL," +
                "FOREIGN KEY (cpf_cliente) REFERENCES CLIENTE(cpf)" +
                ");";

        String sqlItemVenda =
                "CREATE TABLE IF NOT EXISTS ITEM_VENDA (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "venda_id INTEGER," +
                "tipo INTEGER," +
                "nome_item TEXT," +
                "quantidade INTEGER," +
                "valor_unitario REAL," +
                "subtotal REAL," +
                "FOREIGN KEY (venda_id) REFERENCES VENDA(id)" +
                ");";

        String sqlProduto =
                "CREATE TABLE IF NOT EXISTS PRODUTO (" +
                "codigo_barras TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "fabricante TEXT," +
                "categoria TEXT," +
                "preco_venda REAL," +
                "quantidade_estoque INTEGER" +
                ");";

        String sqlServico =
                "CREATE TABLE IF NOT EXISTS SERVICO (" +
                "codigo_servico TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "preco_servico REAL" +
                ");";

        String sqlFuncionario =
                "CREATE TABLE IF NOT EXISTS funcionario (" +
                "matricula TEXT PRIMARY KEY, " +
                "cpf TEXT, " +
                "nome TEXT, " +
                "telefone TEXT, " +
                "email TEXT, " +
                "login TEXT, " +
                "permissao INTEGER" +
                ");";
                

        try (
            Connection conn = ConexaoBD.conectar();
            Statement stmt = conn.createStatement()
        ) {

            stmt.execute(sqlCliente);
            stmt.execute(sqlVenda);
            stmt.execute(sqlItemVenda);
            stmt.execute(sqlProduto);
            stmt.execute(sqlServico);
            stmt.execute(sqlFuncionario);

            System.out.println("Tabelas criadas com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}