package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    // Method to save user questions and answers into a file
    public static void saveToFile(String category, int value, String question, String answer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userQnA.txt", true))) {
            writer.write("Category: " + category + ", Value: $" + value);
            writer.write("Q: " + question);
            writer.newLine();
            writer.write("A: " + answer);
            writer.newLine();
            writer.write("----------------------------");
            // Check in terminal
            System.out.println("Questionn and answer have been saved in txt file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to send the saved Question to the question section of the file
    public static void saveQuestion(String category, int value, String question) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userQnA.txt", true))) {
            writer.write("Category: " + category + ", Value: $" + value + ", Q: " + question);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to send the saved Answer to the answer section of the file
    public static void saveAnswer(String category, int value, String answer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userQnA.txt", true))) {
            writer.write("Category: " + category + ", Value: $" + value + ", A:" + answer);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}