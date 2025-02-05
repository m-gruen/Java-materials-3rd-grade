package com.example.javafx_intro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        HBox root = new HBox();
        root.setPrefSize(340, 70);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        // Buttons anlegen
        Button btnUntis = new Button();
        Button btnMoodle = new Button();
        Button btnMail = new Button();
        Button btnWiki = new Button();
        Button btnExit = new Button();

        root.getChildren().addAll(btnUntis, btnMoodle, btnMail, btnWiki, btnExit);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
