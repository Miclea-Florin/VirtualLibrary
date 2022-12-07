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
import org.w3c.dom.Text;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerRegister {

    @FXML Button backButtone;
    @FXML
    private Button backButton;

    @FXML
    private Button createAccountButton;

    @FXML
    private TextField numeTextField;

    @FXML
    private TextField prenumeTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    public void add_toDB(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String q = "insert into useraccounts(Firstname,Lastname,Username,Password) values ('"+prenumeTextField.getText()+"','"+numeTextField.getText()+"','" +  usernameTextField.getText()+"','"+ passwordPasswordField.getText()+"')";
        //System.out.println(q);

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(q);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //TODO add back button


    public void backButtonOnAction(ActionEvent e) throws IOException {
        //System.out.println("Eeee");
        //System.out.println("fasbjkdfsadflsad");

        SceneController.switchToLogin(e);


    }

    public void registerButtonOnAction(ActionEvent e){
        System.out.println("fasbdkhfsajd");
        if(usernameTextField.getText().isBlank() == false &&
        numeTextField.getText().isBlank() == false &&
        prenumeTextField.getText().isBlank() == false &&
        passwordPasswordField.getText().isBlank() == false
        ) {

            add_toDB();
            usernameTextField.setText("");
            numeTextField.setText("");
            prenumeTextField.setText("");
            passwordPasswordField.setText("");
        }

        else {
            //System.out.println("Nu toate campurile sunt completate");
            // TODO add message to scene

        }
    }

}
