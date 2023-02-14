package org.example.dto;

import java.util.List;

public class MeasurementResponse {
    private List<MeasurementDTO> measurementDTOS;

    public MeasurementResponse(List<MeasurementDTO> measurementDTOS) {
        this.measurementDTOS = measurementDTOS;
    }

    public List<MeasurementDTO> getMeasurementDTOS() {
        return measurementDTOS;
    }

    public void setMeasurementDTOS(List<MeasurementDTO> measurementDTOS) {
        this.measurementDTOS = measurementDTOS;
    }
}
