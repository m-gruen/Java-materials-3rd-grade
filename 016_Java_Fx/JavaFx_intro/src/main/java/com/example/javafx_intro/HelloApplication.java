package com.example.javafx_intro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

        btnUntis.setPrefSize(64, 64);
        btnMoodle.setPrefSize(64, 64);
        btnMail.setPrefSize(64, 64);
        btnWiki.setPrefSize(64, 64);
        btnExit.setPrefSize(64, 64);

        btnUntis.getStyleClass().add("btnUntis");
        btnMoodle.getStyleClass().add("btnMoodle");
        btnMail.getStyleClass().add("btnMail");
        btnWiki.getStyleClass().add("btnWiki");
        btnExit.getStyleClass().add("btnExit");

        root.getChildren().addAll(btnUntis, btnMoodle, btnMail, btnWiki, btnExit);

        btnUntis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument("https://mese.webuntis.com/WebUntis/?school=htbla%20linz%20leonding#/basic/login");
            }
        });

        btnMoodle.setOnAction(event -> {
                getHostServices().showDocument("https://edufs.edu.htl-leonding.ac.at/moodle/");
        });

        // Some more actions
        // ...

        btnExit.setOnAction(event -> System.exit(0));

        Scene scene = new Scene(root, 340, 100);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        // stage.setTitle("Hello!");
        stage.setScene(scene);

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        stage.setX(screenSize.getWidth() / 2 - scene.getWidth() / 2);
        stage.setY(20);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
