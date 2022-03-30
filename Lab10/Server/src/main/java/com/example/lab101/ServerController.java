package com.example.lab101;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.util.ArrayList;

import static java.lang.Thread.currentThread;

public class ServerController {
    private static ArrayList<String> Messages = new ArrayList<String>();
    private boolean Online = true;
    @FXML
    ListView list;

    @FXML
    Button exit;

    @FXML
    protected void Exit() {
        HelloApplication.server.CloseServer();
        Online = false;
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }



    @FXML
    public void initialize() {
        new Thread(() -> {
            while (Online) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Messages);
                        list.getItems().addAll(Messages);
                        Messages.clear();
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentThread().interrupt();
        }).start();
    }

    public static synchronized void AddList(String Text) {
        Messages.add(Text);
    }
}