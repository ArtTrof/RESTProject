package com.example.RESTApiProject.controllers;

import com.example.RESTApiProject.dto.MeasurementDTO;
import com.example.RESTApiProject.dto.MeasurementResponse;
import com.example.RESTApiProject.models.Measurement;
import com.example.RESTApiProject.services.MeasurementService;
import com.example.RESTApiProject.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.RESTApiProject.util.ErrorUtil.returnErrorToClient;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;


    @Autowired
    public MeasurementController(ModelMapper modelMapper, MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }
    @GetMapping
    public MeasurementResponse getAllMeasurements(){
        MeasurementResponse measurementResponse = new MeasurementResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
        return  measurementResponse;
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDays(){
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement,bindingResult);
        if (bindingResult.hasErrors())
            returnErrorToClient(bindingResult);

        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
