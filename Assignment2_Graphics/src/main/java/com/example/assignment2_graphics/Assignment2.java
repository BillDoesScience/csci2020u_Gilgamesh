package com.example.assignment2_graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * <h1>Assignment2</h1>
 * The Graphics Driver that generates the bar graph using JavaFX
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-03-22
 */
public class Assignment2 extends Application {
    /** Graphics Driver that creates the BarGraph and then displays
     * @param stage - The Primary Stage for the Graphics
     * @throws IOException - Error thrown from LoadFile
     * @throws ParserConfigurationException - Error Thrown from LoadFile
     */
    @Override
    public void start(Stage stage) throws IOException, ParserConfigurationException {

        //Window Size
        int WinHeight = 1200;
        int WinWidth = 1200;

        // Call the DataProccesor
        DataProccesor Data = new DataProccesor("src/main/resources/airline_safety.csv");
        String[] Input = Data.getGraphData();

        //Create the Bar Graph
        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Number of Fatal Incidents per Airline based on Year Interval");
        xAxis.setLabel("Airline");
        yAxis.setLabel("Number of Fatal Incidents");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Fatal Incidents 1985-99");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Fatal Incidents 2000-14");

        // Add the Data the correct Series
        for (int i = 0; i < (Input.length-1); i++) {
            // Split the Input into WorkingData
            String[] WorkingData = Input[i].split(",");
            series1.getData().add(new XYChart.Data(WorkingData[0], Float.parseFloat(WorkingData[1])));
            series2.getData().add(new XYChart.Data(WorkingData[0], Float.parseFloat(WorkingData[2])));
        }

        Scene scene = new Scene(bc,WinWidth,WinHeight);
        bc.getData().addAll(series1, series2);
        stage.setTitle("Assignment 2");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}