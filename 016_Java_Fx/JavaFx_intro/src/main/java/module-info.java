module com.example.javafx_intro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_intro to javafx.fxml;
    exports com.example.javafx_intro;
}