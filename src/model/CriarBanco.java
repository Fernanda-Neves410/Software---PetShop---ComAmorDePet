package model;

import java.sql.Connection;
import java.sql.Statement;

public class CriarBanco {

    public static void criarTabelas() {

        String sqlCliente = """
            CREATE TABLE IF NOT EXISTS cliente (
                cpf TEXT PRIMARY KEY,
                nome TEXT NOT NULL,
                telefone TEXT,
                endereco TEXT
            );
            """;

        try (
            Connection conn = ConexaoSQLite.conectar();
            Statement stmt = conn.createStatement()
        ) {

            stmt.execute(sqlCliente);

            System.out.println("Tabela CLIENTE criada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}