package com.example.lab07;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Lab07 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FileReader fileReader = new FileReader("weatherwarnings-2015.csv");
        BufferedReader input = new BufferedReader(fileReader);
        String line = null;
        String[] Types = {"FLASH FLOOD","SEVERE THUNDERSTORM","SPECIAL MARINE","TORNADO"};
        HashMap<String, Integer> WarningCounter = new HashMap<String, Integer>();
        while ((line = input.readLine()) != null) {
            String[] arrOfStr = line.split(",");
            for (String i : Types) {

                if (arrOfStr[5].equals(i)) {
                    if (WarningCounter.get(i) == null) {
                        WarningCounter.put(i,1);
                    } else {
                        WarningCounter.put(i,(WarningCounter.get(i) + 1));
                    }
                }
            }
        }

        float sum = 0;
        for (String s: Types) {
            //System.out.println("Key: " + s + " Value: " + WarningCounter.get(s));
            sum += WarningCounter.get(s);
        }
        int WinHeight = 906;
        int WinWidth = 1200;
        Group root = new Group();

        float startPoint = 0;
        int a = 0;
        for (String s: Types) {
            Arc arc = new Arc();
            arc.setCenterX(870);
            arc.setCenterY(WinHeight/2);
            arc.setRadiusX(300);
            arc.setRadiusY(300);
            arc.setStartAngle(startPoint);
            startPoint += (360) * (WarningCounter.get(s)/sum);
            arc.setLength((360) * (WarningCounter.get(s)/sum));
            arc.setType(ArcType.ROUND);
            Color CurColor = (Color.color(Math.random(), Math.random(), Math.random()));
            arc.setFill(CurColor);
            arc.setStroke(Color.BLACK);

            Rectangle rectangle1 = new Rectangle(100, WinHeight/4 + (a * 100), 150, 80);
            Text t = new Text (280 , WinHeight/4 + (a * 100) + 45, s);
            t.setFont(Font.font ("Verdana", 20));
            rectangle1.setFill(CurColor);
            rectangle1.setStroke(Color.BLACK);

            a++;
            root.getChildren().addAll(rectangle1,t,arc);


        }



        Scene scene = new Scene(root, WinWidth, WinHeight);
        stage.setTitle("Lab 07!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}