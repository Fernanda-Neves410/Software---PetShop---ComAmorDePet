package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static final String URL =
        "jdbc:sqlite:comamordepet.db";

    public static Connection conectar() throws SQLException {

        System.out.println("BANCO EM USO: " +
            new java.io.File("comamordepet.db").getAbsolutePath()
        );

        return DriverManager.getConnection(URL);
    }
}