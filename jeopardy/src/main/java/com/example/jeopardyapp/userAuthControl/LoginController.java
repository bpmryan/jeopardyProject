package com.example.jeopardyapp.userAuthControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
// import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.example.jeopardyapp.userAuthControl.Encrypt.encrypt;

public class LoginController extends Encrypt {

    @FXML
    public TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    public Button confirmLogin;

    @FXML
    public Label userErrorLabel;

    private Stage stage;
    private Scene scene;

    //when user clicks cancel button in Login page, they return to the welcome page
    public void cancelLogin(ActionEvent cancelLogin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)cancelLogin.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome Page");
        stage.setScene(scene);
        stage.show();
    }

    public void confirmLoginClick(ActionEvent event) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        /*
            - retrieve email and password
            - password characters to dots
            - account validation (try-catch)
            - Label saying "Welcome (user's name)"

         */

            //create a route to Account.txt via filewriter
            FileWriter writer = new FileWriter("src/main/resources/Account.txt", true);
            File file = new File("src/main/resources/Account.txt");
            //Scan reads Account.txt
            Scanner scan = new Scanner(file);

            //Save user's input in Login page
            String emailUserInput = emailTextField.getText();
            String passwordUserInput = passwordTextField.getText();

            //encrypt emailUserInput
            String emailEncrypt = encrypt(emailUserInput, secret);

            while(scan.hasNext()) {
            //do while check for the right account info
                    //reads line
                String line = scan.nextLine();
                //splits the line with ","
                String [] account = line.split(",");
                //finds the name, email, password, and salt of the original account
                String name = account[0];
                String email = account[1];
                String password = account[2];
                String salt = account[3];

                if(email.equals(emailEncrypt)) {
                    //decrypt name to turn into ClearText
                    stringName = decrypt(name, secret);

                    //hash passwordUserInput with salt
                    String hashPass = hashPassword(passwordUserInput, salt);
                    //save password via variable

                /*
                    if-else for account verification
                    if email and password are correct, send user to next scene
                    currently does not send user to next scene
                    error is possibly in the condiiton in the if-else statement
                 */

                    //sends user to next scene
                    if (hashPass.equals(password)) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginConti.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(fxmlLoader.load());
                        stage.setTitle("Welcome User");
                        stage.setScene(scene);
                        stage.show();
                        LoginConti loginContiController = fxmlLoader.getController();
                        loginContiController.nameOfUser.setText("Hello " + stringName);
                    } else {
                        //propmt user to re-enter their account info again
                        userErrorLabel.setText("Please try again");
                    }
                    writer.close();
                    scan.close();
                }
            }




    }


}
