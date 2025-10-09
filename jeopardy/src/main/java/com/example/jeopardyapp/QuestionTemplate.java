package com.example.jeopardyapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuestionTemplate {

    /*
     * This is where the user will input the question.
     * It should be saved into some sort of file
     * There should be a save button that sends the text to a file that goes to the
     * database.
     * 
     */

    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField pointValueTextField;
    @FXML
    private TextField questionText;
    @FXML
    private Button returnQuestionBoardButton;
    @FXML
    private Button revealAnswerButton;
    @FXML
    private Button saveQuestion;

    // Stage of this question window
    private Stage stage;

    // Init method for catVal
    private CatVal categoryInfo;

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
    public void setCategoryInfo(CatVal categoryInfo) {
        this.categoryInfo = categoryInfo;
        if (categoryTextField != null) {
            categoryTextField.setText(categoryInfo.getCategory());
        }
        if (pointValueTextField != null) {
            pointValueTextField.setText("$" + categoryInfo.getValue());
        }

        // saves the questions to the userQnA.txt file along with what category and value it has
        if (saveQuestion != null) {
            saveQuestion.setOnAction(e -> {
                String question = questionText.getText();
                if (question != null && !question.trim().isEmpty()) {
                    FileSaver.saveQuestionMethod(categoryInfo, question);
                }
            });
        }
    }

    // When user is opening the answer window
    // controller.setCategoryAndValue(this.categoryInfo);

    // Allow caller to pass the Stage reference
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
            
            // pulls up answerTemplate in a new window
            FXMLLoader loader = new FXMLLoader(FirstPage.class.getResource("answerTemplate.fxml")); 
            Parent root = loader.load();
            AnswerTemplate controller = loader.getController();

            // fills in the question/answer point value and category
            controller.setCategoryAndValue(categoryInfo); 

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
