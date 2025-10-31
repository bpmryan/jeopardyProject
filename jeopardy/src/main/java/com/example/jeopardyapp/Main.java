package com.example.jeopardyapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    // Right now:  Main opens questionPage.fxml

    /*
     * Main should open up user authentication 
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("questionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Question");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Test saving to csv
        // CatVal newCat = new CatVal("Geography", 200);
        // FileSaver.saveQuestionMethod(newCat, "Paris?");
        // FileSaver.saveAnswerMethod(newCat, "France");
        launch();

        
    }
}