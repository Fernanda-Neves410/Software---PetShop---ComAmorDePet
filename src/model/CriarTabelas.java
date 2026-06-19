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

        try (
            Connection conn = ConexaoBD.conectar();
            Statement stmt = conn.createStatement()
        ) {

            stmt.execute(sqlCliente);
            stmt.execute(sqlVenda);
            stmt.execute(sqlItemVenda);

            System.out.println("Tabelas criadas com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}