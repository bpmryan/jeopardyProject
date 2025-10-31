package com.example.newproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupConti {
    private Scene scene;
    private Stage stage;

    public void returnClick(ActionEvent returnToWelcomePage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)returnToWelcomePage.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome Page");
        stage.setScene(scene);
        stage.show();

    }
}
