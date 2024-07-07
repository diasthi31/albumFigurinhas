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
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    protected Connection connect() throws SQLException {
        conn = DriverManager.getConnection(_CONNECTION_STRING_);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA journal_mode=WAL");
            stmt.execute("PRAGMA busy_timeout=5000");
        }
        System.out.println("Connected to " + _CONNECTION_STRING_);
        return conn;
    }

    protected void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }

    private void dbCreate() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, senha TEXT, perfil TEXT)");
            stmt.executeUpdate("CREATE TABLE figurinha(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, pagina INTEGER NOT NULL, capa BLOB NOT NULL, tag TEXT NOT NULL, descricao TEXT NOT NULL)");
            stmt.executeUpdate("CREATE TABLE album(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, paginas INTEGER NOT NULL, capa TEXT NOT NULL)");
            stmt.executeUpdate("CREATE TABLE figurinha_album(idAlbum INTEGER NOT NULL, idFigurinha INTEGER NOT NULL, FOREIGN KEY (idAlbum) REFERENCES album(id), FOREIGN KEY (idFigurinha) REFERENCES figurinha(id))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}