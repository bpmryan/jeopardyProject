package com.example.jeopardyapp.userAuthControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginConti extends LoginController{
    private Stage stage;
    private Scene scene;

    @FXML
    public Label nameOfUser;



    public void yesClick(ActionEvent returnToWelcomePage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)returnToWelcomePage.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome Page");
        stage.setScene(scene);
        stage.show();
    }
}
