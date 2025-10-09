package com.example.jeopardyapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FirstPage extends Application {
    // FirstPage opens questionPage.fxml

    /*
     * This should be the place that the creator can add categories and questions
     * per category
     * 
     * Both templates should be able to save the question and answer to a file
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FirstPage.class.getResource("questionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Question");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        CatVal newCat = new CatVal("Geography", 200);
        FileSaver.saveQuestionMethod(newCat, "Paris?");
        FileSaver.saveAnswerMethod(newCat, "France");
        launch();

        
    }
}