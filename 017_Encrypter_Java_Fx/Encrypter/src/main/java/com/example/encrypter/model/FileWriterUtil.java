package com.example.encrypter.model;


import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {

    private static final String DIRECTORY = "files/";

    public static void writeToFile(String content, String path) {
        try {
            var dir = new File(DIRECTORY);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            var file = new File(DIRECTORY + path);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
        }
    }
}