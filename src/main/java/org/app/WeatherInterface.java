package org.app;

import java.util.*;

public interface WeatherInterface {

    public static enum API_TYPES {
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
    };

    void setLocation(Map<String, String> locationInfo);

    boolean getAllForecast();

    boolean getWheatherNow();

    Long getLocalTime();

}
