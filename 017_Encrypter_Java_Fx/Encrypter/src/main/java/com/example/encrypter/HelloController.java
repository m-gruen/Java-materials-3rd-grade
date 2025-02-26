package com.example.encrypter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelloController {
    @FXML
    private TextField txtAlphabet, txtKey;

    @FXML
    private TextArea txtPlainText, txtEncrypedText;

    public void initialize() {
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
    }

    public void onStandardButtonClicked(ActionEvent actionEvent) {
        txtAlphabet.setText("abcdefghijklmnopqrstuvwxyzäöüß");
    }

    public void onRecalcButtonClicked(ActionEvent actionEvent) {
        String alphabet = txtAlphabet.getText();

        Random random = new Random();
        String key = Stream.generate(() -> alphabet.charAt(random.nextInt(alphabet.length())))
                .distinct()
                .limit(alphabet.length())
                .map(String::valueOf)
                .collect(Collectors.joining());

        txtKey.setText(key);
    }

    private void encrypt() {

    }
}