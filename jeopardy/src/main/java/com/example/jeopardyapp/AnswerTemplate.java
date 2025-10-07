package com.example.jeopardyapp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class AnswerTemplate {

    @FXML
    private Button returnQuestonBoardButton; // note the fx:id typo in FXML
    @FXML
    private Button saveAnswer;

    @FXML
    private TextField answerText;

    private Stage stage;

    @FXML
    private void initialize() {
        if (returnQuestonBoardButton != null) {
            returnQuestonBoardButton.setOnAction(e -> closeWindow());
        }

        
    }

    // Init method for catVal
    private CatVal categoryInfo;
    public void setCategoryAndValue(CatVal categoryInfo) {
        this.categoryInfo = categoryInfo;

        // saves the answers to userQnA.txt 
        if (saveAnswer != null) {
            saveAnswer.setOnAction(e -> {
                String answer = answerText.getText();
                if (answer != null && !answer.trim().isEmpty()) {
                    FileSaver.saveAnswerMethod(categoryInfo, answer);
                }
            });
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // closes current window
    private void closeWindow() {
        if (stage == null) {
            if (returnQuestonBoardButton != null) {
                Stage s = (Stage) returnQuestonBoardButton.getScene().getWindow();
                s.close();
                return;
            }
        }
        stage.close();
    } 

    // Unused / junk code for future reference

    // public void setCategoryAndValue(String category, int value) {
    //     // If you later add fx:id fields for category/value in the answer FXML, populate
    //     // them here.
    // }

    // // to return user to the questionTemplate
    // private void returnToQuestionTemplate(){
    // closeWindow();
    // try{
    // FXMLLoader loader = new
    // FXMLLoader(FirstPage.class.getResource("questionTemplate.fxml"));
    // Parent root = loader.load();
    // QuestionTemplate controller = loader.getController();

    // Stage questionStage = new Stage();
    // controller.setStage(questionStage);
    // questionStage.setTitle("Question");
    // questionStage.setScene(new Scene(root));
    // questionStage.show();
    // } catch (IOException ex) {
    // ex.printStackTrace();
    // }
    // }

    
}
