package com.example.lab06;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class HelloController {
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

    @FXML
    private PieChart piechart;

    @FXML
    private XYChart barchart;

    @FXML
    public void initialize() {
        ObservableList<PieChart.Data> piedata = FXCollections.observableArrayList();
        for (int i = 0; i < ageGroups.length;i++) {
            piedata.add(new PieChart.Data(ageGroups[i], purchasesByAgeGroup[i]));
        };
        piechart.setData(piedata);
        piechart.setLegendVisible(false);
        piechart.setLabelsVisible(false);

        XYChart.Series dataset1 = new XYChart.Series();
        XYChart.Series dataset2 = new XYChart.Series();
        for (int i = 0; i < avgCommercialPricesByYear.length;i++) {
            dataset1.getData().add(new XYChart.Data(Integer.toString(i),avgHousingPricesByYear[i]));
            dataset2.getData().add(new XYChart.Data(Integer.toString(i),avgCommercialPricesByYear[i]));
        };

        barchart.getData().addAll(dataset1,dataset2);
    }
}