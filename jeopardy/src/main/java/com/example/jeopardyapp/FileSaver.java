package com.example.jeopardyapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    /*
     * saveToFile method has not been used yet
     * only saveQuestionMethod and saveAnswerMethod have been used
     * create a button that uses saveToFile method to save questions and answers at the same time (use a separate txt file?)
     * 
     * Have saveToFile method implemented into both templates rather than use saveQuestionMethod and saveAnswerMethod
     */

    // Method to save user questions and answers into a file
    // catVal gets the category and value info
    public static void saveToFile(CatVal catVal, String question, String answer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy\\src\\main\\resources\\data\\UserQnA.csv", true))) {
            writer.write(catVal.getCategory() + ", $" + catVal.getValue());
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

    // Method to save category names 
    public static void saveUserCategory(String categoryName){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy\\src\\main\\resources\\data\\UserQnA.csv", true))) {
            writer.write("User Category: " + categoryName);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to send the saved Question to the question section of the file
    public static void saveQuestionMethod(CatVal catVal, String question) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy\\src\\main\\resources\\data\\UserQnA.csv", true))) {
            writer.write(catVal.getCategory() + ", $" + catVal.getValue() + ", " + question);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to send the saved Answer to the answer section of the file
    public static void saveAnswerMethod(CatVal catVal, String answer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jeopardy\\src\\main\\resources\\data\\UserQnA.csv", true))) {
            writer.write(catVal.getCategory() + ", $" + catVal.getValue() + ", " + answer);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}