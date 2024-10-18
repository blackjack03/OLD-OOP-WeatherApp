package org.app;

import java.util.*;

import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
import java.io.*;

import javax.swing.*;
import java.awt.event.*;

public class Main {

    public static void main(final String[] args) {

        /*final String DAFAULT_URL = "https://www.3bmeteo.com/meteo/";
        String URL;

        URL = "https://www.3bmeteo.com/meteo/forli";

        Document METEO_3B = null;  // Inizializza con null per gestire eventuali errori

        try {
            METEO_3B = Jsoup.connect(URL).get();
        } catch (final Exception e) {
            e.printStackTrace();  // Stampa l'errore per capire cosa succede
        }

        if (METEO_3B != null) {
            final Elements FORECAST_BAR = METEO_3B.getElementsByClass("slider").get(0).getElementsByClass("navDays");
            // System.out.println(FORECAST_BAR);
        } else {
            System.out.println("Errore nel caricamento della pagina meteo.");
        }*/

        final var city_selector = new LocationSelector();

        final var LOCATIONS = city_selector.getPossibleLocations("meLdola");

        System.out.println(LOCATIONS);

        /*var c = city_selector.getByID(1380594352);

        if (c.isPresent()) {
            System.out.println(c.get());
        } else {
            System.out.println("Città non trovata");
        }*/


        // https://api.open-meteo.com/v1/forecast?latitude=44.2218&longitude=12.0414&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,rain,weather_code,wind_speed_10m,wind_direction_10m,soil_temperature_0cm&daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset,daylight_duration,sunshine_duration,uv_index_max&timezone=Europe%2FBerlin&forecast_days=14

    }

}
