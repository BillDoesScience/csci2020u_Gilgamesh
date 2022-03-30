package com.example.lab101;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static Client client;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Client.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("Server.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        Stage Stage2 = new Stage();
        Stage2.setScene(scene2);
        Stage2.show();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}