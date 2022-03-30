package com.example.lab08;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.action.Action;


import java.io.*;
import java.nio.file.Paths;

public class lab8Controller {
    private File currentFilename;

    @FXML
    private TextField StudentID1,finalexam1,assignment1,midterms1;

    @FXML
    private VBox BasePanel;

    @FXML
    private TableView tableBookList;

    @FXML
    private void AddData(final ActionEvent event) {
        StudentRecord temp = new StudentRecord(StudentID1.getText(), Float.parseFloat(assignment1.getText()), Float.parseFloat(midterms1.getText()), Float.parseFloat(finalexam1.getText()));
        tableBookList.getItems().add(temp);
        //System.out.println(StudentID1.getText() + String.valueOf(assignment1) + String.valueOf(midterms1) + String.valueOf(finalexam1));
    }

    @FXML
    private void handleExitAction(final ActionEvent event) {
        Stage stage =(Stage) BasePanel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleSaveAction(final ActionEvent event) throws IOException {
        Save();
    }

    private void Save() throws IOException {
        ObservableList<StudentRecord> Temp = tableBookList.getItems();
        FileWriter csvWriter = new FileWriter(currentFilename);
        csvWriter.append("SID");
        csvWriter.append(",");
        csvWriter.append("Assignments");
        csvWriter.append(",");
        csvWriter.append("Midterm");
        csvWriter.append(",");
        csvWriter.append("Final Exam");
        csvWriter.append("\n");
        for (StudentRecord student: Temp) {
            csvWriter.append(student.getStudentID() + "," + student.getAssignment() + "," + student.getMidterm() + ","  + student.getFinalExam() + "\n");
        }
        csvWriter.close();
    }

    private File fileChooser() {
        Window window = BasePanel.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        fileChooser.setInitialDirectory(new File(currentPath));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(window);

    }

    @FXML
    private void handleSaveASAction(final ActionEvent event) throws IOException {
        currentFilename = fileChooser();
        Save();
    }

    @FXML
    private void handleNewOption(final ActionEvent event) {
        tableBookList.getItems().clear();
    }
    @FXML
    private void handleOpenAction(final ActionEvent event) throws IOException {
        tableBookList.getItems().clear();
        currentFilename = fileChooser();
        Load();
    }

    @FXML
    private void Load() throws IOException {
        File csvFile = currentFilename;
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();
        String row;
        if (csvFile.isFile()) {
            BufferedReader csvReader = new BufferedReader(new FileReader(currentFilename));
            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                marks.add(new StudentRecord(data[0], Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3])));
            }
            csvReader.close();
        }
        tableBookList.getItems().addAll(marks);
    }
}