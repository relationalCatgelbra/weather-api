package com.authdemo.auth.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseDataHistorical {

    private List<WeatherResponseDataOperations> weatherResponseDataOperations;

    private List<WeatherResponseData> weatherResponseDataList;

}
