module com.example.contact_manager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.contact_manager to javafx.fxml;
    exports com.example.contact_manager;
}