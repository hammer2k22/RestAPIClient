package ru.hammer2000;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;


/**
 * Hello world!
 */
public class Client {
    public static void main(String[] args) {

        Sensor sensor = new Sensor("weather_sensor");
        registrationSensor(sensor.getName());

        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            double value = (random.nextDouble() * 200) - 100;
            value = (double) Math.round(value * 10) / 10;
            boolean raining = random.nextBoolean();
            sendMeasurements(value,raining,sensor);
        }
    }

    private static void registrationSensor(String sensorName ){
        RestTemplate restTemplate = new RestTemplate();
        String registrationSensorUrl = "http://localhost:8080/sensors/registration";
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);
        HttpEntity<Map<String, String>> registration = new HttpEntity<>(jsonData);
        restTemplate.postForEntity(registrationSensorUrl, registration, String.class);
    }

    private static void sendMeasurements(double value, boolean raining, Sensor sensor){
        RestTemplate restTemplate = new RestTemplate();
        String measurementsAddUrl = "http://localhost:8080/measurements/add";
        Map<String, Object> measurementData = new HashMap<>();
        measurementData.put("value", value);
        measurementData.put("raining", raining);
        measurementData.put("sensor", sensor);
        HttpEntity<Map<String, Object>> addMeasurement = new HttpEntity<>(measurementData);
        restTemplate.postForEntity(measurementsAddUrl, addMeasurement, String.class);
    }
}
