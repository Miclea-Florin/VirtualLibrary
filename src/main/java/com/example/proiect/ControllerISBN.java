package com.example.proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerISBN {

    @FXML
    private Button logoutButton;

    private Button CartiButton;

    @FXML
    private TextField isbnTextField;


    public void addBook_toDB(Carte c){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String q = "insert into carti(title,publisher,ISBN,language,authors,year,pages) values ('"+c.getTitle()+"','"+c.getPublisher()+"','"+c.getISBN()+"','"+c.getLanguage()+"','"+c.getAuthors()+"','"+c.getDate_published()+"','"+c.getPages()+"')";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(q);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void logoutButtonOnAction(ActionEvent e) throws IOException {

        SceneController.switchToLogin(e);

    }

    public void CartiButtonOnAction(ActionEvent e) throws  IOException{
        SceneController.switchToAdminBooks(e);

    }

    public void addBook_toDB(ActionEvent event) throws IOException {

            if(isbnTextField.getText().isBlank() == false){

                Carte c = ISBN_DB.ISBN_to_Object(isbnTextField.getText());
                System.out.println(c);
                addBook_toDB(c);
            }
            else{
                System.out.println("eroare");



            }

        }
}


