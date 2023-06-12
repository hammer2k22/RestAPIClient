package ru.hammer2000;

import java.util.List;

public class RestAPIResponse {
    private List<Measurement> measurements;

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "RestAPIResponse{" +
                "measurements=" + measurements +
                '}';
    }
}
