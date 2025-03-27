package com.springboot.mrspringboot.Waqas;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class weatherApi {
    private Current current;

    @Setter
    @Getter
    public class Current{
        @JsonProperty("temp_c")
        public double tempC;
        @JsonProperty("temp_f")
        public double tempF;
        public int humidity;
        public int cloud;
        @JsonProperty("feelslike_c")
        public double feelslikeC;
        @JsonProperty("feelslike_f")
        public double feelslikeF;

    }

}


