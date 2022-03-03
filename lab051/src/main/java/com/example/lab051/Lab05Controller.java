package com.example.lab051;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class Lab05Controller {
    @FXML
    private TableView tableBookList;

    @FXML
    public void initialize() {
        ObservableList<StudentRecord> marks = DataSource.getAllMarks();
        tableBookList.setItems(marks);
    }
}