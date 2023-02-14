package com.example.RESTApiProject.services;

import com.example.RESTApiProject.models.Measurement;
import com.example.RESTApiProject.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll(){
       List<Measurement> measurements= measurementRepository.findAll();
       return measurements;
    }
    @Transactional
    public void save(Measurement measurement) {
        enrich(measurement);
        measurementRepository.save(measurement);
    }

    private void enrich(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurementTime(LocalDateTime.now());
    }
}
