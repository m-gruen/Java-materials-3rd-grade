module javafx_intro {
    requires javafx.controls;
    requires javafx.fxml;

    opens javafx_intro to javafx.fxml;
    exports javafx_intro;
}
