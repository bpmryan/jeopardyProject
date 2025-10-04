package com.example.jeopardyapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class QuestionTemplate {

    @FXML private TextField categoryTextField;
    @FXML private TextField pointValueTextField;
    @FXML private Button returnQuestionBoardButton;
    @FXML private Button revealAnswerButton;

    private String category;
    private int pointValue;

    // Stage of this question window
    private Stage stage;

    @FXML
    private void initialize() {
        // Set up button actions once the controller is initialized
        if (returnQuestionBoardButton != null) {
            returnQuestionBoardButton.setOnAction(e -> closeWindow());
        }
        if (revealAnswerButton != null) {
            revealAnswerButton.setOnAction(e -> openAnswerWindow());
        }
    }

    // Called by the code that opens this window to pass data
    public void setCategoryAndValue(String category, int value) {
        this.category = category;
        this.pointValue = value;
        if (categoryTextField != null) {
            categoryTextField.setText(category);
        }
        if (pointValueTextField != null) {
            pointValueTextField.setText("$" + value);
        }
    }

    // Allow caller to pass the Stage reference (useful for closing)
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Method to close window 
    private void closeWindow() {
        if (stage == null) {
            // fallback: try to get stage from any button
            if (returnQuestionBoardButton != null) {
                Stage s = (Stage) returnQuestionBoardButton.getScene().getWindow();
                s.close();
                return;
            }
        }
        stage.close();
    }

    private void openAnswerWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(FirstPage.class.getResource("answerTemplate.fxml")); //pulls up the answerTemplate in a new window
            Parent root = loader.load();
            AnswerTemplate controller = loader.getController();
            controller.setCategoryAndValue(category, pointValue); //fills in the question value and category

            Stage answerStage = new Stage();
            controller.setStage(answerStage);
            answerStage.setTitle("Answer");
            answerStage.setScene(new Scene(root));
            if (stage != null) {
                answerStage.initOwner(stage);
                answerStage.initModality(Modality.APPLICATION_MODAL);
            }
            answerStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
