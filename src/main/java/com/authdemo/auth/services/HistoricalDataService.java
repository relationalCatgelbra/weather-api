package com.authdemo.auth.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.authdemo.auth.models.HistoricalDataRequestObject;
import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.models.WeatherResponseDataHistorical;
import com.authdemo.auth.models.WeatherResponseDataOperations;

@Service
public class HistoricalDataService {

        private WeatherResponseDataRepositoryService weatherResponseDataRepositoryService;

        private WeatherResponseDataService weatherResponseDataService;

        private WeatherResponseDataOperationsService weatherResponseDataOperationsService;

        public HistoricalDataService(WeatherResponseDataRepositoryService weatherResponseDataRepositoryService,
                        WeatherResponseDataOperationsService weatherResponseDataOperationsService,
                        WeatherResponseDataService weatherResponseDataService) {
                this.weatherResponseDataRepositoryService = weatherResponseDataRepositoryService;
                this.weatherResponseDataOperationsService = weatherResponseDataOperationsService;
                this.weatherResponseDataService = weatherResponseDataService;

        }

        public WeatherResponseDataHistorical prepareHistoricalObject(
                        HistoricalDataRequestObject historicalDataRequestObject) {

                String startDate = historicalDataRequestObject.getStartDate();

                String endDate = historicalDataRequestObject.getEndDate();

                String startTime = historicalDataRequestObject.getStartTime();

                String endTime = historicalDataRequestObject.getEndTime();

                String formatter = "yyyy-MM-dd'T'HH:mm:ss";

                LocalDateTime startLocalDateTime = weatherResponseDataService
                                .fuseDateAndTime(startDate, startTime, formatter);

                LocalDateTime endLocalDateTime = weatherResponseDataService
                                .fuseDateAndTime(endDate, endTime, formatter);

                List<WeatherResponseData> weatherResponseDataByCityCountry = weatherResponseDataRepositoryService
                                .getAllWeatherDataByCountryAndCity(
                                                historicalDataRequestObject.getCountry(),
                                                historicalDataRequestObject.getCity());

                List<WeatherResponseData> weatherResponseDataFiltered = weatherResponseDataService
                                .filterDateTimeInRange(weatherResponseDataByCityCountry, startLocalDateTime,
                                                endLocalDateTime);

                List<WeatherResponseDataOperations> weatherResponseDataOperations = List
                                .of(weatherResponseDataOperationsService
                                                .getMeanOfWeatherResponseData(weatherResponseDataFiltered));

                WeatherResponseDataHistorical weatherResponseDataHistorical = new WeatherResponseDataHistorical();

                weatherResponseDataHistorical.setWeatherResponseDataList(weatherResponseDataFiltered);
                weatherResponseDataHistorical.setWeatherResponseDataOperations(weatherResponseDataOperations);

                return weatherResponseDataHistorical;

        }

}
