package com.authdemo.auth.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiResponse {

    private String version;

    private String user;

    private String dateGenerated;

    private String status;

    private List<WeatherData> data;

}
