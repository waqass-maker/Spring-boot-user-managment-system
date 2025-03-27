package com.springboot.mrspringboot.service;

import com.springboot.mrspringboot.Waqas.weatherApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Weatherservice {

    @Autowired
    private Rediservice rediservicep;
    @Value("${waqas.api}")
    private String api;
    private static final String API="http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";
    @Autowired
    private RestTemplate restTemplate;
    public weatherApi getweather(String city){
        weatherApi weatherApi = rediservicep.get(city, weatherApi.class);
        if (weatherApi !=null){
            return weatherApi;
        }
        else {
            String finalapi= API.replace("CITY",city).replace("API_KEY",api);
            ResponseEntity<weatherApi> response = restTemplate.exchange(finalapi, HttpMethod.GET, null, weatherApi.class);
            weatherApi check = response.getBody();
            if (check !=null){
                rediservicep.set(city,check,3000l);
            }
            return check;
        }

    }

}
