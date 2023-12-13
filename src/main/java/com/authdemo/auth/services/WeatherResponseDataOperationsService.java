package com.authdemo.auth.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.models.WeatherResponseDataMean;
import com.authdemo.auth.models.WeatherResponseDataOperations;

@Service
public class WeatherResponseDataOperationsService {

    public WeatherResponseDataOperations getMeanOfWeatherResponseData(
            List<WeatherResponseData> weatherList) {

        Double temperatureMean = weatherList.stream()
                .map(w -> w.getTemperature())
                .filter(v -> v != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        Double windDirectionMean = weatherList.stream()
                .map(w -> w.getWindDirection())
                .filter(v -> v != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        Double windSpeedMean = weatherList.stream()
                .map(w -> w.getWindSpeed())
                .filter(v -> v != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        Double meanSeaLevelMean = weatherList.stream()
                .map(w -> w.getMeanSeaLevel())
                .filter(v -> v != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        Double seaLevelPressureMean = weatherList.stream()
                .map(w -> w.getSeaLevelPressure())
                .filter(v -> v != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        Double amountPrecipitationMean = weatherList.stream()
                .map(w -> w.getAmountPrecipitation())
                .filter(v -> v != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        Double uvRaysMean = weatherList.stream()
                .map(w -> w.getUvRays())
                .filter(v -> v != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        Double sunriseTimeMean = weatherList.stream()
                .map(w -> w.getSunriseTime())
                .filter(v -> v != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        Double sunsetTimeMean = weatherList.stream()
                .map(w -> w.getSunsetTime())
                .filter(v -> v != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(Double.NaN);

        return new WeatherResponseDataMean.Builder()
                .setTemperatureMean(temperatureMean)
                .setWindDirectionMean(windDirectionMean)
                .setWindSpeedMean(windSpeedMean)
                .setMeanSeaLevelMean(meanSeaLevelMean)
                .setSeaLevelPressureMean(seaLevelPressureMean)
                .setAmountPrecipitationMean(amountPrecipitationMean)
                .setUvRayMean(uvRaysMean)
                .setSunriseTimeMean(sunriseTimeMean)
                .setSunsetTimeMean(sunsetTimeMean)
                .build();

    }

}
