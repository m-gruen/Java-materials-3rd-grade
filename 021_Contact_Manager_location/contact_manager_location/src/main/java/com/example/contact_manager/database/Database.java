package com.example.contact_manager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Database instance;

    private static final String URL = "jdbc:h2:tcp://localhost:9092/./contactDb";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private static Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void initialize() {
        String createContactTable = """
                CREATE TABLE IF NOT EXISTS Contact(
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(255) NOT NULL,
                    phone VARCHAR(255),
                    address VARCHAR(255),
                    contactType VARCHAR(255),
                    plz VARCHAR(255),
                    CONSTRAINT fk_location
                        FOREIGN KEY (plz)
                        REFERENCES Location(plz) ON DELETE CASCADE
                )
                """;
        String createLocationTable = """
                CREATE TABLE IF NOT EXISTS Location(
                    plz VARCHAR(255) PRIMARY KEY,
                    city VARCHAR(255) NOT NULL
                )
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(createLocationTable);
            statement.execute(createContactTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
