package com.example.contact_manager.database;

import java.sql.Connection;

public class Database {
    private static Database instance;

    private static final String URL = "jdbc:h2:tcp://localhost:9092/./contactDb";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private  static Connection connection;

    // TODO
}
