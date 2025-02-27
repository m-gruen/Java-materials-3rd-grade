package com.example.encrypter.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Encrypter {

    private final String DEFAULT_ALPHABET = "abcdefghijklmnopqrstuvwxyzäöüß";
    private StringProperty alphabet = new SimpleStringProperty("");
    private StringProperty key = new SimpleStringProperty("");
    private StringProperty plainText = new SimpleStringProperty("");
    private StringProperty encryptedText = new SimpleStringProperty("");

    public Encrypter() {
        alphabet.addListener((observable, oldValue, newValue) -> generateRandomKey());
        key.addListener((observable, oldValue, newValue) -> encrypt());
        plainText.addListener((observable, oldValue, newValue) -> encrypt());

        setDefaultAlphabet();
        setPlainText("Please enter your text here.");
    }

    public String getAlphabet() {
        return alphabet.get();
    }

    public StringProperty alphabetProperty() {
        return alphabet;
    }

    public String getKey() {
        return key.get();
    }

    public StringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public String getPlainText() {
        return plainText.get();
    }

    public StringProperty plainTextProperty() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText.set(plainText);
    }

    public String getEncryptedText() {
        return encryptedText.get();
    }

    public StringProperty encryptedTextProperty() {
        return encryptedText;
    }

    public void setEncryptedText(String encryptedText) {
        this.encryptedText.set(encryptedText);
    }

    public void setAlphabet(String alphabet) {
        this.alphabet.set(alphabet);
    }

    // Methoden
    public void setDefaultAlphabet() {
        setAlphabet(DEFAULT_ALPHABET);
    }

    public void generateRandomKey() {
        String distinctAlphabet = getAlphabet().chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char)c))
                .collect(Collectors.joining());

        Random random = new Random();
        String key = Stream.generate(() -> distinctAlphabet.charAt(random.nextInt(distinctAlphabet.length())))
                .distinct()
                .limit(distinctAlphabet.length())
                .map(String::valueOf)
                .collect(Collectors.joining());

        setKey(key);
    }

    private void encrypt() {
        String alphabet = getAlphabet();
        String key = getKey();
        String plainText = getPlainText().toLowerCase();

        String encryptedText = plainText.chars()
                .mapToObj(c -> (char) c)
                .map(c -> {
                    int index = alphabet.indexOf(c);
                    return index >= 0 ? key.charAt(index) : c;
                })
                .map(String::valueOf)
                .collect(Collectors.joining());

        setEncryptedText(encryptedText);
    }

    public void saveData() {
        FileWriterUtil.writeToFile(getEncryptedText(), "encrypted.txt");
        FileWriterUtil.writeToFile(getPlainText(), "plainText.txt");
        FileWriterUtil.writeToFile(getAlphabet() + "\n" + getKey(), "alphabetAndKey.txt");
    }
}
