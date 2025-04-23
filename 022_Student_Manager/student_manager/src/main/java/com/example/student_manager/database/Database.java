package com.example.student_manager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Database instance;

    private static final String URL = "jdbc:h2:tcp://localhost:9092/./studentDb";
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
        String createStudentTable = """
                CREATE TABLE IF NOT EXISTS Student(
                    ID INT PRIMARY KEY AUTO_INCREMENT,
                    FirstName VARCHAR(255) NOT NULL,
                    LastName VARCHAR(255) NOT NULL
                )
                """;

        String createCourseTable = """
                CREATE TABLE IF NOT EXISTS Course(
                    ID VARCHAR(255) PRIMARY KEY,
                    Name VARCHAR(255) NOT NULL
                )
                """;

        String createCoursesOfStudentsTable = """
                CREATE TABLE IF NOT EXISTS CoursesOfStudents(
                    StudentID INT NOT NULL,
                    CourseID VARCHAR(255) NOT NULL,
                    PRIMARY KEY (StudentID, CourseID),
                    FOREIGN KEY (StudentID) REFERENCES Student(ID),
                    FOREIGN KEY (CourseID) REFERENCES Course(ID)
                )
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(createStudentTable);
            statement.execute(createCourseTable);
            statement.execute(createCoursesOfStudentsTable);
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
