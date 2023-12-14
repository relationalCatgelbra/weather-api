package com.authdemo.auth.services.uploadfilestrategyclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.authdemo.auth.models.WeatherResponseData;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UploadCsvFileStrategyImpl implements UploadFileStrategyInterface {

    public List<WeatherResponseData> execute(MultipartFile file) throws IOException, CsvValidationException {

        List<WeatherResponseData> weatherResponseDataList = new ArrayList<>();

        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        CSVReader csvReader = new CSVReaderBuilder(reader).build();

        String[] headers = csvReader.readNext();

        String[] line;

        while ((line = csvReader.readNext()) != null) {
            WeatherResponseData weatherResponseData = new WeatherResponseData();

            for (int i = 0; i < headers.length; i++) {
                switch (headers[i]) {
                    case "temperature":
                        weatherResponseData.setTemperature(Double.parseDouble(line[i]));
                        break;
                    case "windDirection":
                        weatherResponseData.setWindDirection(Double.parseDouble(line[i]));
                        break;
                    case "windSpeed":
                        weatherResponseData.setWindSpeed(Double.parseDouble(line[i]));
                        break;
                    case "meanSeaLevel":
                        weatherResponseData.setMeanSeaLevel(Double.parseDouble(line[i]));
                        break;
                    case "seaLevelPressure":
                        weatherResponseData.setSeaLevelPressure(Double.parseDouble(line[i]));
                        break;
                    case "amountPrecipitation":
                        weatherResponseData.setAmountPrecipitation(Double.parseDouble(line[i]));
                        break;
                    case "uvRays":
                        weatherResponseData.setUvRays(Double.parseDouble(line[i]));
                        break;
                    case "sunriseTime":
                        weatherResponseData.setSunriseTime(Double.parseDouble(line[i]));
                        break;
                    case "sunsetTime":
                        weatherResponseData.setSunsetTime(Double.parseDouble(line[i]));
                        break;
                    case "country":
                        weatherResponseData.setCountry(line[i]);
                        break;
                    case "city":
                        weatherResponseData.setCity(line[i]);
                        break;
                    case "date":
                        weatherResponseData.setDate(line[i]);
                        break;
                    case "time":
                        weatherResponseData.setTime(line[i]);
                        break;
                }

            }

            weatherResponseDataList.add(weatherResponseData);

        }

        return weatherResponseDataList;

    }

}
