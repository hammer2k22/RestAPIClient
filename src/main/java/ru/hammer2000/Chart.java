package ru.hammer2000;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chart {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String getMeasurementsUrl = "http://localhost:8080/measurements";
        RestAPIResponse response = restTemplate.getForObject(getMeasurementsUrl, RestAPIResponse.class);
        List<Double> values = response.getMeasurements().stream()
                .map(Measurement::getValue)
                .collect(Collectors.toList());

        drawChart(values);
    }

    private static void drawChart(List<Double> values) {
        double[] yData = values.stream().mapToDouble(x -> x).toArray();
        double[] xData = IntStream.range(1, values.size()+1).asDoubleStream().toArray();
        XYChart chart = QuickChart.getChart("График температур", "Номер измерения",
                "Температура", "температура", xData, yData);
        new SwingWrapper(chart).displayChart();
    }
}
