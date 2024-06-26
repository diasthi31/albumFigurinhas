package org.example.repository;

import java.io.File;
import java.sql.*;

public class Repository {
    private static final String _DATA_BASE_ = "dados.db";
    private static final String _CONNECTION_STRING_ = "jdbc:sqlite:" + _DATA_BASE_;
    private Connection conn = null;

    public Repository() {
        try {
            Class.forName("org.sqlite.JDBC");
            File file = new File(_DATA_BASE_);

            if (!file.exists()) {
                dbCreate();
            } else {
                System.out.println("Database already exists.");
            }
        } catch (Exception e) {
            e.getStackTrace();
            System.exit(0);
        }
    }

    protected Connection connect() throws Exception {
        if (conn == null) {
            conn = DriverManager.getConnection(_CONNECTION_STRING_);
            System.out.println("Connected to " + _CONNECTION_STRING_);
        }

        return conn;
    }

    protected void disconnect() throws Exception {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    private void dbCreate() {
        try {
            connect();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, login STRING, senha STRING, perfil STRING)");
            stmt.executeUpdate("CREATE TABLE figurinha(id INTEGER PRIMARY KEY AUTOINCREMENT, nome STRING NOT NULL, pagina INTEGER NOT NULL, capa STRING NOT NULL, tag STRING NOT NULL, descricao STRING NOT NULL)");
            stmt.executeUpdate("CREATE TABLE album(id INTEGER PRIMARY KEY AUTOINCREMENT, nome STRING NOT NULL, paginas INTEGER NOT NULL, capa STRING NOT NULL, figurinhas String, FOREIGN KEY (figurinhas) REFERENCES figurinha(tag) ON DELETE CASCADE ON UPDATE CASCADE)");
            stmt.close();

            disconnect();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println(e.getMessage());
        }
    }
}