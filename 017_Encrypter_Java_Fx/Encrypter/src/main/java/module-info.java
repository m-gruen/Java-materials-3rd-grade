module com.example.encrypter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.encrypter to javafx.fxml;
    exports com.example.encrypter;
}