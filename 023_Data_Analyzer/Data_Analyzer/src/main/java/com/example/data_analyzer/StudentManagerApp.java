package com.example.data_analyzer;

import com.example.data_analyzer.database.Database;
import com.example.data_analyzer.view.StudentPresenter;
import javafx.application.Application;
import javafx.stage.Stage;

public class StudentManagerApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize the database first
        Database database = Database.getInstance();

        // Initialize course data now that the database is set up
        database.initializeCourseData();

        // Now show the UI
        StudentPresenter.show(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
