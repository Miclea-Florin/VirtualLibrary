package com.example.proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;

public class SceneController {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

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

}
