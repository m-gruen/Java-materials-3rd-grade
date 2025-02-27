package com.example.encrypter;

import com.example.encrypter.model.Encrypter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EncrypterController {
    Encrypter encrypter;

    @FXML
    private TextField txtAlphabet, txtKey;

    @FXML
    private TextArea txtPlainText, txtEncrypedText;

    public void initialize() {
        encrypter = new Encrypter();

        txtPlainText.selectedTextProperty().addListener(
            (observable, oldValue, newValue) -> {
                int start = txtPlainText.getSelection().getStart();
                int end = txtPlainText.getSelection().getEnd();
                txtEncrypedText.selectRange(start, end);
            }
        );

        txtEncrypedText.selectedTextProperty().addListener(
                (observable, oldValue, newValue) -> {
                    int start = txtEncrypedText.getSelection().getStart();
                    int end = txtEncrypedText.getSelection().getEnd();
                    txtPlainText.selectRange(start, end);
                }
        );

        txtAlphabet.textProperty().bindBidirectional(encrypter.alphabetProperty());
        txtKey.textProperty().bindBidirectional(encrypter.keyProperty());
        txtPlainText.textProperty().bindBidirectional(encrypter.plainTextProperty());
        txtEncrypedText.textProperty().bindBidirectional(encrypter.encryptedTextProperty());
    }

    public void onStandardButtonClicked(ActionEvent actionEvent) {
        encrypter.setDefaultAlphabet();
    }

    public void onRecalcButtonClicked(ActionEvent actionEvent) {
        encrypter.generateRandomKey();
    }
}