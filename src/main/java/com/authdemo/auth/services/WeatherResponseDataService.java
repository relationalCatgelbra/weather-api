package com.authdemo.auth.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.authdemo.auth.models.WeatherData;
import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.repositories.WeatherResponseDataRepository;

@Service
public class WeatherResponseDataService {

    private WeatherResponseDataRepository weatherResponseRepository;

    public WeatherResponseDataService(WeatherResponseDataRepository weatherResponseDataRepository) {
        this.weatherResponseRepository = weatherResponseDataRepository;
    }

    public WeatherResponseData saveWeatherResponseData(WeatherResponseData weatherResponseData) {
        return weatherResponseRepository.save(weatherResponseData);
    }

    public List<WeatherResponseData> getAllWeatherDataByCountryAndCity(String countryName, String cityName) {
        return weatherResponseRepository.findByCountryAndCity(countryName, cityName);

    }

    public WeatherResponseData toWeatherResponseDataObject(List<WeatherData> weatherData, String country, String city) {
        WeatherResponseData weatherResponseData = new WeatherResponseData();

        weatherResponseData.setCity(city);
        weatherResponseData.setCountry(country);

        for (int i = 0; i < weatherData.size(); i++) {
            String weatherParam = weatherData.get(i).getParameter();
            Double weatherValue = weatherData.get(i)
                    .getCoordinates()
                    .get(0)
                    .getDates()
                    .get(0)
                    .getValue();
            if (weatherParam.equals("t_2m:C")) {
                weatherResponseData.setTemperature(weatherValue);
            } else if (weatherParam.equals("wind_dir_10m:d")) {
                weatherResponseData.setWindDirection(weatherValue);
            } else if (weatherParam.equals("wind_speed_10m:ms")) {
                weatherResponseData.setWindSpeed(weatherValue);
            } else if (weatherParam.equals("msl_pressure:hPa")) {
                weatherResponseData.setSeaLevelPressure(weatherValue);
            } else if (weatherParam.equals("precip_24h:mm")) {
                weatherResponseData.setAmountPrecipitation(weatherValue);
            } else if (weatherParam.equals("uv:idx")) {
                weatherResponseData.setUvRays(weatherValue);
            } else if (weatherParam.equals("sunrise:sql")) {
                weatherResponseData.setSunriseTime(weatherValue);
            } else if (weatherParam.equals("sunset:sql")) {
                weatherResponseData.setSunsetTime(weatherValue);
            }

        }

        String localDateTime = weatherData.get(0)
                .getCoordinates()
                .get(0)
                .getDates()
                .get(0)
                .getDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        LocalDateTime responseDateTime = LocalDateTime.parse(localDateTime, formatter).minusHours(5);
        LocalDate localDate = responseDateTime.toLocalDate();
        LocalTime localTime = responseDateTime.toLocalTime();

        weatherResponseData.setTime(localTime.toString());
        weatherResponseData.setDate(localDate.toString());

        return weatherResponseData;

    }

    public List<WeatherResponseData> filterDateTimeInRange(
            List<WeatherResponseData> weatherResponseDataCollection,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        return weatherResponseDataCollection.stream()
                .filter(w -> {
                    LocalDateTime localDateTime = fuseDateAndTime(
                            w.getDate(),
                            w.getTime(),
                            "yyyy-MM-dd'T'HH:mm:ss");

                    return !localDateTime.isBefore(startDateTime) && !localDateTime.isAfter(endDateTime);
                }

                ).collect(Collectors.toList());

    }

    public LocalDateTime fuseDateAndTime(String date, String time, String formatter) {
        String localDateTimeString = date + "T" + time;
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatter);
        return LocalDateTime.parse(localDateTimeString, format);

    }

    public String getWeatherApiParameters() {
        return "t_2m:C,wind_dir_10m:d,wind_speed_10m:ms,msl_pressure:hPa,precip_24h:mm,uv:idx,sunrise:sql,sunset:sql";
    }

    public String getWeatherApiBaseUrl() {
        return "https://api.meteomatics.com";
    }

}
