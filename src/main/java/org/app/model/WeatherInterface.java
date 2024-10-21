package org.app.model;

import java.util.*;

/*public static enum API_TYPES {
        CURRENT("/current.json"),
        FORECAST("/forecast.json"),
        SEARCH("/search.json"),
        MARINE("/marine.json"),
        FUTURE("/future.json"),
        HISTORY("/history.json"),
        ASTRONOMY("/astronomy.json");

        private final String apiTypeURL;

        API_TYPES(final String apiTypeURL) {
            this.apiTypeURL = apiTypeURL;
        }

        @Override
        public String toString() {
            return this.apiTypeURL;
        }
    };*/

public interface WeatherInterface {

    void setLocation(Map<String, String> locationInfo);

    boolean reqestsAllForecast();

    Optional<Map<String, Map<String, Map<String, Number>>>> getAllForecast();

    Optional<Map<String, Map<String, Number>>> getDailyGeneralForecast();

    Optional<Map<String, Map<String, String>>> getDailyInfo();

    int getForecastDays();

    Optional<Pair<String, Map<String, Number>>> getWeatherNow();

    Long getLocalTime();

}
