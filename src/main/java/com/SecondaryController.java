package com;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;


public class SecondaryController {
    @FXML private LineChart<Number, Number> lineChart;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(1, 10));
        series.getData().add(new XYChart.Data<>(2, 15));
        series.getData().add(new XYChart.Data<>(3, 7));
        series.getData().add(new XYChart.Data<>(4, 12));
        series.getData().add(new XYChart.Data<>(5, 16));
        series.setName("Data");

        lineChart.getData().add(series);
    }
}