package com.example.jeopardyapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    // Right now: Main opens questionPage.fxml

    /*
     * Main should open up user authentication
     * After user signs in, then it should go to game dashboard
     * If user clicks on "Create new game", then it should take them to a new game
     * creation center
     *      > otherwise if they click on a current game, it should allow them to continue
     *      working on the game they've been working on so far (saved progress from last
     *      time they've worked on it)
     * 
     * User should be able to present the game if they hit a button "Play game" (something along those lines)
     * on a ready game.
     */
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