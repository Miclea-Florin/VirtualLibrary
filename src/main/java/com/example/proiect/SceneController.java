package com.example.proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.action.Action;

import java.io.IOException;

public class SceneController{
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public static void switchToBooked(ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("booked.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void switchToAdminBooks(ActionEvent event) throws  IOException{
        root = FXMLLoader.load(SceneController.class.getResource("AdminBooks.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public static void switchToRegister(ActionEvent event) throws IOException {

         root = FXMLLoader.load(SceneController.class.getResource("register.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }

    public static void switchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("ISBN_to_Database.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void switchToGuest(ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("Guest.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1080,600);

        stage.setScene(scene);
        //stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }

    public static void switchToUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("UserRent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


}
