package com.authdemo.auth.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.authdemo.auth.models.Location;
import com.authdemo.auth.models.LocationRequest;
import com.authdemo.auth.models.WeatherApiResponse;
import com.authdemo.auth.models.WeatherData;
import com.authdemo.auth.models.WeatherResponseData;

@Service
public class WeatherResponseDataService {

    private WeatherResponseDataRepositoryService weatherResponseRepositoryService;

    private TokenClass tokenClass;

    private CoordinatesService coordinatesService;

    public WeatherResponseDataService(
            WeatherResponseDataRepositoryService weatherResponseDataRepositoryService,
            TokenClass tokenClass,
            CoordinatesService coordinatesService) {
        this.weatherResponseRepositoryService = weatherResponseDataRepositoryService;
        this.tokenClass = tokenClass;
        this.coordinatesService = coordinatesService;
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

    public WeatherResponseData getCurrentWeatherData(LocationRequest locationRequest) throws NullPointerException {

        String weatherApiBaseUrl = getWeatherApiBaseUrl();

        String tokenString = tokenClass.getToken();

        Location location = coordinatesService
                .getCoordinatesByCityAndCountry(locationRequest.getCity(),
                        locationRequest.getCountry(), locationRequest.getFormat());

        String coordinates = String.join(",", location.getLon(), location.getLat());

        String localDate = getLocalDate();

        String uriString = UriComponentsBuilder.fromUriString(weatherApiBaseUrl)
                .pathSegment(localDate)
                .pathSegment(getWeatherApiParameters())
                .pathSegment(coordinates)
                .pathSegment("json")
                .build()
                .toUriString();

        List<WeatherData> weatherResponse = WebClient.create(uriString)
                .get()
                .uri("?access_token={token}", tokenString)
                .retrieve()
                .bodyToMono(WeatherApiResponse.class)
                .block()
                .getData();

        WeatherResponseData weatherResponseData = toWeatherResponseDataObject(
                weatherResponse, locationRequest.getCountry(), locationRequest.getCity());

        return weatherResponseRepositoryService
                .saveWeatherResponseData(weatherResponseData);

    }

    private String getLocalDate() {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(-5);
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        return localDateTime.atOffset(zoneOffset).format(formatter);

    }

}
