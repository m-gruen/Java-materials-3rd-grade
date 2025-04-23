module com.example.student_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.student_manager to javafx.fxml;
    exports com.example.student_manager;
}