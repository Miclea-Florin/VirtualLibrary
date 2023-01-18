package com.example.proiect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller1 extends Main{

    @FXML
    private Button UserLoginButton;
    @FXML
    private Button exitButton;

    @FXML
    private Label loginMessageLable= new Label();

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button GuestButton;

    public void exitButtonOnAction(ActionEvent e){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }



    public void registerButtonOnAction(ActionEvent e) throws IOException {
        SceneController.switchToRegister(e);


    }
    public void GuestButtonOnAction(ActionEvent e) throws IOException {
        SceneController.switchToGuest(e);


    }


    public void loginButtonOnAction(ActionEvent e) throws IOException {
        // System.out.println("asdasdasdas");
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            // if ok login
            //loginMessageLable.setText("aici1");
            //System.out.println("aici1");
            if(validateLogin()==true)
            SceneController.switchToDashboard(e);

        }
        else {  // Cand nu avem user si pass
            //loginMessageLable.setText("Please enter username and password");
            //System.out.println(usernameTextField.getText());
           // System.out.println(passwordPasswordField.getText());
        }

    }

    public void UserLoginButtonOnAction(ActionEvent e) throws IOException {
        // System.out.println("asdasdasdas");
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            // if ok login
            //loginMessageLable.setText("aici1");
            //System.out.println("aici1");
            if(validateLoginUser()==true)
                SceneController.switchToUser(e);

        }
        else {  // Cand nu avem user si pass
            //loginMessageLable.setText("Please enter username and password");
            //System.out.println(usernameTextField.getText());
            // System.out.println(passwordPasswordField.getText());
        }


    }
    public boolean validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM useraccounts where username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    System.out.println("welcome");
                    return true;
                }
                else {
                    System.out.println("User sau parola gresita");
                return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return false;
    }


    public boolean validateLoginUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM NormalUserAccounts where username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    System.out.println("welcome");

                    String aux = "SELECT idUserAccounts FROM NormalUserAccounts where username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";
                    Statement UID = connectDB.createStatement();
                    ResultSet query2Result = UID.executeQuery(aux);
                    query2Result.next();
                    USER_ID=(query2Result.getInt(1));




                    return true;
                }
                else {
                    System.out.println("User sau parola gresita");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return false;
    }



}