package com.example.jeopardyapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;


public class QuestionController {

    private Stage stage;

    // Opens the final jeopardy question
    public void finalJeopardyButtonClick(ActionEvent actionEvent) {
    }

    // opens the Question Board scene from selections
    public void submitButtonClick(javafx.event.ActionEvent ActionEvent) throws IOException {
        // Determine selections from ComboBoxes; default to 5 if not chosen
        Integer catSel = categoryCombo != null ? categoryCombo.getSelectionModel().getSelectedItem() : null;
        Integer qSel = questionCombo != null ? questionCombo.getSelectionModel().getSelectedItem() : null;
        int categories = catSel != null ? catSel : 5;
        int questionsPerCategory = qSel != null ? qSel : 5;

        // Open in a NEW window (Stage)
        openDynamicBoardWindow(categories, questionsPerCategory);

        // Optionally keep the original window open; if you want to switch windows instead, uncomment below:
        ((Stage) ((Node) ActionEvent.getSource()).getScene().getWindow()).close();
    }

    // Inputs from questionPage.fxml
    // The ComboBox<Integer> are field declarations for each combo box
    @FXML
    private javafx.scene.control.ComboBox<Integer> categoryCombo; // number of categories
    @FXML
    private javafx.scene.control.ComboBox<Integer> questionCombo; // number of questions per category

    // way to save user input category names
    @FXML
    private TextField userCategoryInput;
    @FXML
    private Button saveCategoryButton;

    @FXML
    // Setup continuation for combo boxes
    // each combo has a list of Integers for the user to choose from
    // What the user selects generates the next scene
    public void initialize() {

        // this creates the combo box choices (the numbers) for each question
        if (categoryCombo != null) {
            categoryCombo.getItems().setAll(5, 6, 7, 8);
            categoryCombo.getSelectionModel().select(Integer.valueOf(5));
        }
        if (questionCombo != null) {
            questionCombo.getItems().setAll(5, 6, 7, 8);
            questionCombo.getSelectionModel().select(Integer.valueOf(5));
        }

        // Way for user input category names
        // TextField userCategoryInput = new TextField();
        // userCategoryInput.setPromptText("Enter category name");
        // Button saveCategoryButton = new Button("Save Category");

        // saveCategoryButton.setOnAction(e -> {
        //     String categoryName = userCategoryInput.getText();
        //     if(categoryName != null && !categoryName.trim().isEmpty()){
        //         // Calls FileSaver.java saveUserCategory method and writes to UserQnA.txt file
        //         FileSaver.saveUserCategory(categoryName);
        //     }
        // });
    }

    // Method that dynamically builds the jeopardy board based off user selections in questionPage.fxml
    private void openDynamicBoardWindow(int categories, int questionsPerCategory) {
        // GridPane with (categories) columns and (questionsPerCategory + 1) rows (top
        // row = category headers)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-padding: 20; -fx-background-color: #0b1b3b;");

        // Column constraints so it stretches nicely
        for (int c = 0; c < categories; c++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / categories);
            grid.getColumnConstraints().add(cc);
        }
        // Row constraints
        for (int r = 0; r < questionsPerCategory + 1; r++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / (questionsPerCategory + 1));
            grid.getRowConstraints().add(rc);
        }

        // Place for user to change the category names 
        // Header labels (Category 1..N)
        for (int c = 0; c < categories; c++) {
            Label header = new Label("Category " + (c + 1));
            header.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-alignment: center;");
            header.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            // grid.add(userCategoryInput, 0, categories + 1, categories, 1);
            // grid.add(saveCategoryButton, 0, categories + 2, categories, 1);
            grid.add(header, c, 0);
        }

        // Question value buttons
        int baseValue = 100; // classic Jeopardy values: 100,200,...
        for (int r = 1; r <= questionsPerCategory; r++) {
            int value = baseValue * r;
            for (int c = 0; c < categories; c++) {
                Button cell = new Button("$" + value);
                cell.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                cell.setStyle(
                        "-fx-font-size: 14; -fx-font-weight: bold; -fx-background-color: #123b8b; -fx-text-fill: #ffd700;");
                
                // On click: open question window for this category/value and disable button
                final int colIndex = c;
                cell.setOnAction(e -> {
                    CatVal categoryInfo = new CatVal("Category " + (colIndex + 1), value);
                    openQuestionWindow(categoryInfo, (Stage) ((Button) e.getSource()).getScene().getWindow());


                    // get rid of this function once you know how to separate the user view vs game presentation
                    // different txt file that saves which question has been answered and by which team 
                    // cell.setDisable(true);
                });
                grid.add(cell, c, r);
            }
        }

        BorderPane root = new BorderPane(grid);
        Scene scene = new Scene(root, 800, 600);
        Stage boardStage = new Stage();
        boardStage.setTitle("Jeopardy Board");
        boardStage.setScene(scene);
        boardStage.show();
    }

    private void openQuestionWindow(CatVal categoryInfo, Stage owner) {
        try {
            FXMLLoader loader = new FXMLLoader(FirstPage.class.getResource("questionTemplate.fxml"));
            javafx.scene.Parent root = loader.load();
            QuestionTemplate controller = loader.getController();
            Stage questionStage = new Stage();
            controller.setCategoryInfo(categoryInfo);
            controller.setStage(questionStage);
            questionStage.setTitle(categoryInfo.getCategory() + " - $" + categoryInfo.getValue());
            questionStage.setScene(new Scene(root));
            if (owner != null) {
                questionStage.initOwner(owner);
                questionStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            }
            questionStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
}