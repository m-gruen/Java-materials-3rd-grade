package com.example.contact_manager;

import com.example.contact_manager.view.ContactPresenter;
import javafx.application.Application;
import javafx.stage.Stage;

public class ContactManagerApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ContactPresenter.show(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
