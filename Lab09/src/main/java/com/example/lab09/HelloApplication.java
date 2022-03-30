package com.example.lab09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Scanner;

public class HelloApplication extends Application {
    private File file1,file2;
    @Override
    public void start(Stage stage) throws IOException {
        int WinHeight = 1000;
        int WinWidth = 1000;
        Group root = new Group();
        Scene scene = new Scene(root,WinWidth,WinHeight);

        File file1 = downloadStockPrices("NVDA");
        File file2 = downloadStockPrices("AMD");
        this.file1 = file1;
        this.file2 = file2;

        stage.setTitle("Lab 09");
        stage.setScene(scene);
        stage.show();
        drawLinePlot(stage);
    }

    public File downloadStockPrices(String stockTicker) throws IOException {
        String url = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true",stockTicker);
        String Filename = String.format("./src/main/resources/%s.csv",stockTicker);
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(Filename)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return (new File(Filename));
    }

    public float[] ReadFile(File filename) throws FileNotFoundException {
        Scanner sc = new Scanner(filename);
        int counter = 0;
        float[] floats = new float[1];
        sc.useDelimiter("\n");   //sets the delimiter pattern
        sc.next();
        while (sc.hasNext())  //returns a boolean value
        {
            String[] data = sc.next().split(",");
            floats[counter] = Float.parseFloat(data[4]);
            floats = Arrays.copyOf(floats,floats.length+1);
            counter++;
        }
        sc.close();  //closes the scanner
        return floats;
    }

    public void drawLinePlot(Stage stage) throws FileNotFoundException {
        double WinWidth = stage.getScene().getWidth();
        double WinHeight = stage.getScene().getHeight();
        Parent root = stage.getScene().getRoot();
        Group rootg = (Group) root;

        Line YAxis = new Line(50,50, 50,WinHeight - 50);
        Line XAxis = new Line(50, WinHeight - 50, WinWidth - 50, WinHeight - 50);

        rootg.getChildren().addAll(YAxis,XAxis);

        plotLine(stage,ReadFile(file1),Color.RED);
        plotLine(stage,ReadFile(file2),Color.BLUE);

    }

    public void plotLine(Stage stage, float[] values, Color color) {
        double WinWidth = stage.getScene().getWidth();
        double WinHeight = stage.getScene().getHeight();
        int iter = (int) ((WinWidth - 50) - 50)/values.length;
        Parent root = stage.getScene().getRoot();
        Group rootg = (Group) root;


        double zero = WinHeight - 50;

        double startX = 50,startY = zero - values[0],endX = 0,endY = 0;
        for (int i = 1; i < values.length-2;i++) {
            endY = zero - values[i+1];
            endX = startX + iter;
            Line YAxis = new Line(startX,startY, endX,endY);
            YAxis.setStroke(color);
            startX = endX;
            startY = endY;
            rootg.getChildren().add(YAxis);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}