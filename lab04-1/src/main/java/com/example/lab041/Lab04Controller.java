package com.example.lab041;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Lab04Controller {
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Name;
    @FXML
    private TextField Email;
    @FXML
    private TextField Number;
    @FXML
    private DatePicker Date;

    @FXML
    protected void onRegisterButtonClick() {
        System.out.println(Username.getText());
        System.out.println(Password.getText());
        System.out.println(Name.getText());
        System.out.println(Email.getText());
        System.out.println(Number.getText());
        System.out.println(Date.getValue());

    }
}