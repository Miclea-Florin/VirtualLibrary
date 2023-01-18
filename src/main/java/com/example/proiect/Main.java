package com.example.proiect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    public static int USER_ID=-1;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        //stage.initStyle(StageStyle.UNDECORATED);
        //stage.setTitle("Biblioteca");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) throws IOException {
        //System.out.println(ISBN_DB.ISBN_to_Object("6069830717"));
        //Carte c = ISBN_DB.ISBN_to_Object("6069830717");
        //System.out.println(c.authors);

        launch();

    }
}

