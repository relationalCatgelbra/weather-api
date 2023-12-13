package com.authdemo.auth.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("weatherresponsedata")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseData {

    @Id
    private ObjectId id;

    private Double temperature;

    private Double windDirection;

    private Double windSpeed;

    private Double meanSeaLevel;

    private Double seaLevelPressure;

    private Double amountPrecipitation;

    private Double uvRays;

    private Double sunriseTime;

    private Double sunsetTime;

    private String country;

    private String city;

    private String date;

    private String time;

}
