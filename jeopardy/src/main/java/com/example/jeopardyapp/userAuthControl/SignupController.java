package com.example.newproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.FileWriter;
import java.io.IOException;


public class SignupController extends Encrypt {

    private Stage stage;
    private Scene scene;

    @FXML
    public TextField nameTextField;

    @FXML
    public TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    public Button confirmSignup;

    @FXML
    public Label signupError;


    //when user clicks cancel button on Signup page, they return to the welcome page
    public void cancelSignup(ActionEvent cancelSignup) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)cancelSignup.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome Page");
        stage.setScene(scene);
        stage.show();
    }

    //create methods to save name, email, and password
    //create salt
    //actives when signup button in the signup scene is clicked
    public void confirmSignupClick(javafx.event.ActionEvent event) throws IOException {

        //Check for command line to see where program for the signup screen gets to
        System.out.println("confirmSignUpClicked");

        //create a route to Account.txt via filewriter
        try (FileWriter writer = new FileWriter("src/main/resources/Account.txt", true)) {
            System.out.println("Writer Created");
            //save user input from textfields for name, email, and password
            String nameUserInput = nameTextField.getText();
            String emailUserInput = emailTextField.getText();
            String passwordUserInput = passwordTextField.getText();

            //encrypt user input of name and email
            String nameEncrypt = encrypt(nameUserInput, secret);
            String emailEncrypt = encrypt(emailUserInput, secret);

            //get salt
            String salt = getSalt();

            //hashed password and salt (seasoned)
            String hashPassword = hashPassword(passwordUserInput, salt);


            //Check for command line to see where program for the signup screen gets to
            System.out.println("Writing");

            //Filewriter writes the salt, encrypted, and hashed account info into Account.txt
            writer.write(nameEncrypt);
            writer.write(",");
            writer.write(emailEncrypt);
            writer.write(",");
            writer.write(hashPassword);
            writer.write(",");
            writer.write(salt);
            writer.write("\n");
            writer.close();

            /*
            - confirmSignUpClick should display a "Signup Successful" label
            - should return user back to Welcome Screen
            - User should be able to enter account info into login page
            - if-else to make sure that they fill out info
            */

            /*
            if (nameTextField.equals("")) {
                signupError = new Label("Fill out the following information above");
                System.out.println("did it work?"); //check
            } else if (emailTextField.equals("")){
                 signupError = new Label("Fill out the following information above");
                System.out.println("did it work?"); //check
            } else if (hashPassword.equals("")){
                 signupError = new Label("Fill out the following information above");
                System.out.println("did it work?"); //check
            } else {}

             */
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignupConti.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load());
                stage.setTitle("Welcome New User");
                stage.setScene(scene);
                stage.show();
                System.out.println("did it work?");


        } catch (Exception e) {
            System.out.println(e);
        }







    }
}
