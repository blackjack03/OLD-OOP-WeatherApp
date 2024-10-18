package org.app;

import java.util.*;

// https://api.weatherapi.com/v1/current.json?key=4a135623e6644ebeb6b121450232811&q=Rome&aqi=yes

public class Weather implements WeatherInterface {
    private String city;
    private String region;
    private String country;
    private Pair<String, String> coord;
    private int ID;

    private Map<String, String> weatherInfo = new HashMap<>();

    private final String API_URL = "https://api.open-meteo.com/v1/forecast?latitude=%LAT&longitude=%LNG&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,rain,weather_code,wind_speed_10m,wind_direction_10m,soil_temperature_0cm&daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,sunshine_duration,uv_index_max&timezone=Europe%2FBerlin&forecast_days=14";
    // private final String KEY = "4a135623e6644ebeb6b121450232811";

    @Override
    public void setLocation(final Map<String, String> locationInfo) {
        this.city = locationInfo.get("city");
        this.region = locationInfo.get("admin_name");
        this.country = locationInfo.get("country");
        this.coord = new Pair<>(locationInfo.get("lat"), locationInfo.get("lng"));
        this.ID = Integer.parseInt(locationInfo.get("id"));
    }

    @Override
    public boolean getAllForecast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllForecast'");
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
