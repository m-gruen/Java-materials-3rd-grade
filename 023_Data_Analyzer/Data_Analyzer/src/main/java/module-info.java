module com.example.data_analyzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.data_analyzer to javafx.fxml;
    opens com.example.data_analyzer.view to javafx.fxml;

    exports com.example.data_analyzer;
    exports com.example.data_analyzer.model;
    exports com.example.data_analyzer.view;
    exports com.example.data_analyzer.database;
}