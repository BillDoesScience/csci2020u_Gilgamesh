package com.example.lab101;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.security.MessageDigest;

public class HelloController {
    @FXML
    private TextField Username;

    @FXML
    private TextField Msg;

    @FXML
    protected void SendMessage() {
        String User = Username.getText();
        String Message = Msg.getText();
        String output = String.format("%s: %s",User,Message);
        HelloApplication.client.SendMessage(output);
    }

    @FXML
    protected void Exit() {
        HelloApplication.client.CloseConnection();
        Stage stage = (Stage) Username.getScene().getWindow();
        stage.close();
    }
}