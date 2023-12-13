package com.authdemo.auth.models;

public class WeatherResponseDataMean implements WeatherResponseDataOperations {

    public static class Builder {
        private WeatherResponseDataMean instance = new WeatherResponseDataMean();

        public Builder setTemperatureMean(Double v) {
            instance.temperatureMean = v;
            return this;
        }

        public Builder setWindDirectionMean(Double v) {
            instance.windDirectionMean = v;
            return this;
        }

        public Builder setWindSpeedMean(Double v) {
            instance.windSpeedMean = v;
            return this;
        }

        public Builder setMeanSeaLevelMean(Double v) {
            instance.meanSeaLevelMean = v;
            return this;
        }

        public Builder setSeaLevelPressureMean(Double v) {
            instance.seaLevelPressureMean = v;
            return this;
        }

        public Builder setAmountPrecipitationMean(Double v) {
            instance.amountPrecipitationMean = v;
            return this;
        }

        public Builder setUvRayMean(Double v) {
            instance.uvRaysMean = v;
            return this;
        }

        public Builder setSunriseTimeMean(Double v) {
            instance.sunriseTimeMean = v;
            return this;
        }

        public Builder setSunsetTimeMean(Double v) {
            instance.sunsetTimeMean = v;
            return this;
        }

        public WeatherResponseDataMean build() {
            return instance;
        }

    }

    private Double temperatureMean;

    private Double windDirectionMean;

    private Double windSpeedMean;

    private Double meanSeaLevelMean;

    private Double seaLevelPressureMean;

    private Double amountPrecipitationMean;

    private Double uvRaysMean;

    private Double sunriseTimeMean;

    private Double sunsetTimeMean;

    private WeatherResponseDataMean() {
    }

    public Double getTemperatureMean() {
        return temperatureMean;
    }

    public Double getWindDirectionMean() {
        return windDirectionMean;
    }

    public Double getWindSpeedMean() {
        return windSpeedMean;
    }

    public Double getMeanSeaLevelMean() {
        return meanSeaLevelMean;
    }

    public Double getSeaLevelPressureMean() {
        return seaLevelPressureMean;
    }

    public Double getAmountPrecipitationMean() {
        return amountPrecipitationMean;
    }

    public Double getUvRaysMean() {
        return uvRaysMean;
    }

    public Double getSunriseTimeMean() {
        return sunriseTimeMean;
    }

    public Double getSunsetTimeMean() {
        return sunsetTimeMean;
    }

}
