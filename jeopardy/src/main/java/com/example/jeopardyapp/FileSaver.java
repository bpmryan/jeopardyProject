package com.example.jeopardyapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    /*
     * saveToFile method has not been used yet
     * only saveQuestionMethod and saveAnswerMethod have been used
     * create a button that uses saveToFile method to save questions and answers at the same time (use a separate txt file?)
     */

    // Method to save user questions and answers into a file
    // catVal gets the category and value info
    public static void saveToFile(CatVal catVal, String question, String answer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy\\src\\main\\resources\\data\\UserQnA.txt", true))) {
            writer.write("Category: " + catVal.getCategory() + ", Value: $" + catVal.getValue());
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
    public static void saveQuestionMethod(CatVal catVal, String question) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy\\src\\main\\resources\\data\\UserQnA.txt", true))) {
            writer.write("Category: " + catVal.getCategory() + ", Value: $" + catVal.getValue() + ", Q: " + question);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to send the saved Answer to the answer section of the file
    public static void saveAnswerMethod(CatVal catVal, String answer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy\\src\\main\\resources\\data\\UserQnA.txt", true))) {
            writer.write("Category: " + catVal.getCategory() + ", Value: $" + catVal.getValue() + ", A: " + answer);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}