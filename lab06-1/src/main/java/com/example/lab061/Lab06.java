package com.example.lab061;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Lab06 extends Application {

    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };

    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @Override
    public void start(Stage stage) throws IOException {
        int WinHeight = (int)(avgCommercialPricesByYear[(avgCommercialPricesByYear.length - 1)]/2000 + 100);
        int WinWidth = 1200;
        Group root = new Group();
        for (int i = 0; i < avgHousingPricesByYear.length; i++) {
            double Bar1H = (avgHousingPricesByYear[i]/2000);
            double Bar2H = (avgCommercialPricesByYear[i]/2000);
            Rectangle rectangle1 = new Rectangle(50 + (i*60), WinHeight - 50 - Bar1H, 20, Bar1H);
            rectangle1.setFill(Color.RED);
            Rectangle rectangle2 = new Rectangle(70 + (i*60), WinHeight - 50 - Bar2H, 20, Bar2H);
            rectangle2.setFill(Color.BLUE);
            root.getChildren().addAll(rectangle1,rectangle2);
        }

        float sum = 0;
        for (int j: purchasesByAgeGroup) {
            sum += j;
        }
        float startPoint = 0;
        for (int a = 0; a < ageGroups.length; a++) {
            Arc arc = new Arc();
            arc.setCenterX(870);
            arc.setCenterY(WinHeight/2);
            arc.setRadiusX(300);
            arc.setRadiusY(300);
            arc.setStartAngle(startPoint);
            startPoint += (360) * (purchasesByAgeGroup[a]/sum);
            arc.setLength((360) * (purchasesByAgeGroup[a]/sum));
            arc.setType(ArcType.ROUND);
            arc.setFill(pieColours[a]);

            root.getChildren().add(arc);
        }



        Scene scene = new Scene(root,WinWidth,WinHeight);
        stage.setTitle("Lab 06");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}