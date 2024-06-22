package org.example.repository;

import java.io.File;
import java.sql.*;
import java.util.*;

import static javax.management.remote.JMXConnectorFactory.connect;

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
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private Connection connect() throws Exception {
        if (conn == null) {
            conn = DriverManager.getConnection(_CONNECTION_STRING_);
            System.out.println("Connected to " + _CONNECTION_STRING_);
        }

        return conn;
    }

    private void disconnect() throws Exception {
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
            stmt.executeUpdate("CREATE TABLE album(id INTEGER PRIMARY KEY AUTOINCREMENT, nome STRING NOT NULL, paginas INTEGER NOT NULL, capa STRING NOT NULL, figurinhas INTEGER, FOREIGN KEY (figurinhas) REFERENCES figurinha(id) ON DELETE CASCADE ON UPDATE CASCADE)");
            stmt.close();

            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}