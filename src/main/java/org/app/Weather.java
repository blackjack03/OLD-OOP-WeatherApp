package org.app;

import java.util.*;

import com.google.gson.*;


// https://api.weatherapi.com/v1/current.json?key=4a135623e6644ebeb6b121450232811&q=Rome&aqi=yes
// private final String KEY = "4a135623e6644ebeb6b121450232811";


public class Weather implements WeatherInterface {
    private String city;
    private String region;
    private String country;
    private Pair<String, String> coords;
    private int ID;

    private Map<String, String> weatherInfo = new HashMap<>();

    private final String API_URL = "https://api.open-meteo.com/v1/forecast?latitude=%LAT&longitude=%LNG&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,precipitation,weather_code,wind_speed_10m,wind_direction_10m,soil_temperature_0cm&daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,sunshine_duration,uv_index_max&timezone=auto&forecast_days=8";

    final private Map<String, Map<String, Map<String, Number>>> FORECAST_HOURS = new HashMap<>();
    final private Map<String, Map<String, Number>> DAILY_GENERAL_FORECAST = new HashMap<>();
    final private Map<String, Map<String, String>> DAILY_INFO = new HashMap<>();

    final private Map<String, Number> NOW = new HashMap<>();

    private int forecast_days = 0;
    private long last_update = 0;

    private boolean requested = false;

    @Override
    public void setLocation(final Map<String, String> locationInfo) {
        this.city = locationInfo.get("city");
        this.region = locationInfo.get("admin_name");
        this.country = locationInfo.get("country");
        this.coords = new Pair<>(locationInfo.get("lat"), locationInfo.get("lng"));
        this.ID = Integer.parseInt(locationInfo.get("id"));
        this.requested = false;
    }

    @Override
    public boolean reqestsAllForecast() {
        try {

            // Get and Parse JSON with wheather info
            final var reader = new AdvancedJsonReader(API_URL
                    .replace("%LAT", this.coords.getX())
                    .replace("%LNG", this.coords.getY()));

            // Get Weekly Forecast
            final var dates_hours = reader.getJsonArray("hourly.time");
            final var temperature_2m = reader.getJsonArray("hourly.temperature_2m");
            final var humidity_2m = reader.getJsonArray("hourly.relative_humidity_2m");
            final var apparent_temperature = reader.getJsonArray("hourly.apparent_temperature");
            final var precipitation_probability = reader.getJsonArray("hourly.precipitation_probability");
            final var precipitation = reader.getJsonArray("hourly.precipitation");
            final var weather_code = reader.getJsonArray("hourly.weather_code");
            final var wind_speed_10m = reader.getJsonArray("hourly.wind_speed_10m");
            final var wind_direction_10m = reader.getJsonArray("hourly.wind_direction_10m");
            final var soil_temperature_0cm = reader.getJsonArray("hourly.soil_temperature_0cm");

            for (int i = 0; i < dates_hours.size(); i++) {
                final JsonElement elem = dates_hours.get(i);
                final var dateTime = elem.getAsString().split("T");

                if (!FORECAST_HOURS.containsKey(dateTime[0])) {
                    FORECAST_HOURS.put(dateTime[0], new HashMap<>());
                }

                final Map<String, Number> info = new HashMap<>();
                info.put("temperature", temperature_2m.get(i).getAsNumber());
                info.put("humidity", humidity_2m.get(i).getAsNumber());
                info.put("apparent_temperature", apparent_temperature.get(i).getAsNumber());
                info.put("precipitation_probability", precipitation_probability.get(i).getAsNumber());
                info.put("precipitation_mm", precipitation.get(i).getAsNumber());
                info.put("weather_code", weather_code.get(i).getAsNumber());
                info.put("wind_speed", wind_speed_10m.get(i).getAsNumber());
                info.put("wind_direction", wind_direction_10m.get(i).getAsNumber());
                try {
                    info.put("soil_temperature", soil_temperature_0cm.get(i).getAsNumber());
                } catch (final Exception e) {  // When soil_temperature is JsonNull
                    info.put("soil_temperature", null);
                }
                final String time = dateTime[1].split(":")[0];
                // Add Instance
                FORECAST_HOURS.get(dateTime[0]).put(time, info);
            }

            // Set forecast days
            this.forecast_days = FORECAST_HOURS.keySet().size();

            // Get Daily General Forecast & Daily Info
            final var days = reader.getJsonArray("daily.time");
            final var day_weather_code = reader.getJsonArray("daily.weather_code");
            final var temperature_2m_max = reader.getJsonArray("daily.temperature_2m_max");
            final var temperature_2m_min = reader.getJsonArray("daily.temperature_2m_min");
            final var daylight_duration = reader.getJsonArray("daily.daylight_duration");
            final var sunshine_duration = reader.getJsonArray("daily.sunshine_duration");
            final var uv_index_max = reader.getJsonArray("daily.uv_index_max");
            final var days_sunrise = reader.getJsonArray("daily.sunrise");
            final var days_sunset = reader.getJsonArray("daily.sunset");
            for (int i = 0; i < days.size(); i++) {
                final Map<String, Number> forecast_info = new HashMap<>();
                final Map<String, String> other_info = new HashMap<>();
                forecast_info.put("weather_code", day_weather_code.get(i).getAsNumber());
                forecast_info.put("temperature_max", temperature_2m_max.get(i).getAsNumber());
                forecast_info.put("temperature_min", temperature_2m_min.get(i).getAsNumber());
                forecast_info.put("daylight_duration", daylight_duration.get(i).getAsNumber());
                forecast_info.put("sunshine_duration", sunshine_duration.get(i).getAsNumber());
                forecast_info.put("uv_max", uv_index_max.get(i).getAsNumber());
                // Add Instance
                DAILY_GENERAL_FORECAST.put(days.get(i).getAsString(), forecast_info);

                other_info.put("sunrise", days_sunrise.get(i)
                        .getAsString().split("T")[1]);
                other_info.put("sunset", days_sunset.get(i)
                        .getAsString().split("T")[1]);
                // Add Instance
                DAILY_INFO.put(days.get(i).getAsString(), other_info);
            }

            this.requested = true;

            return true;
        } catch (final Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public int getForecastDays() {
        return this.forecast_days;
    }

    @Override
    public Optional<Map<String, Map<String, Map<String, Number>>>> getAllForecast() {
        if (!this.requested) {
            return Optional.empty();
        }
        return Optional.of(this.FORECAST_HOURS);
    }

    @Override
    public Optional<Map<String, Map<String, Number>>> getDailyGeneralForecast() {
        if (!this.requested) {
            return Optional.empty();
        }
        return Optional.of(this.DAILY_GENERAL_FORECAST);
    }

    @Override
    public Optional<Map<String, Map<String, String>>> getDailyInfo() {
        if (!this.requested) {
            return Optional.empty();
        }
        return Optional.of(this.DAILY_INFO);
    }

    @Override
    public boolean getWheatherNow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWheatherNow'");
    }

    @Override
    public Long getLocalTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocalTime'");
    }

}
