package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final String sensorName="testClientSensor";
        registerSensor(sensorName);

        Random random = new Random();
        double minValue = -40.0;
        double maxValue = 40.0;

        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            sendMeasurement(random.nextDouble(minValue,maxValue),random.nextBoolean(),sensorName);
        }

    }

    private static void registerSensor(String sensorName) {
        final String url ="http://localhost:8080/sensors/registration";
        Map<String ,Object>jsonToSend=new HashMap<>();
        jsonToSend.put("name",sensorName);
        makeRequestWithJSON(url,jsonToSend);
    }

    private static  void sendMeasurement(Double value,Boolean raining,String sensorName){
        final String url="http://localhost:8080/measurement/add";
        Map<String,Object> jsonToSend = new HashMap<>();
        jsonToSend.put("value",value);
        jsonToSend.put("raining",raining);
        jsonToSend.put("sensor",Map.of("name",sensorName));
        makeRequestWithJSON(url,jsonToSend);

    }
    private static void makeRequestWithJSON(String url,Map<String,Object> jsonData){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData);
        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Upload successful");
        }catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
        }

    }
}