module com.example.student_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.student_manager to javafx.fxml;
    opens com.example.student_manager.model to javafx.base;

    exports com.example.student_manager;
    exports com.example.student_manager.view;
    exports com.example.student_manager.model;
}