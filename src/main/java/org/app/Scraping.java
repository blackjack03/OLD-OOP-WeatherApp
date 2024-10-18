package org.app;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jsoup.*;
import org.jsoup.select.*;
import org.jsoup.nodes.*;

import com.google.gson.*;
// import com.javafx.util.Pair;

import java.nio.file.*;
import java.io.*;

import java.time.LocalDate;

public class Scraping {
    public static void main(String[] args) throws Exception {
        final String URL = "https://www.3bmeteo.com/meteo/milano";

        final int TODAY_DATE = LocalDate.now().getDayOfMonth();

        final Document METEO_3B = Jsoup.connect(URL).get();
        final Elements FORECAST_BAR = METEO_3B.getElementsByClass("slider").get(0).getElementsByClass("navDays");

        final var FORECAST = new HashMap<String, HashMap<String, Pair<Integer, Integer>>>();
        final var FRCT_ORDER = new HashMap<Integer, String>();

        int count = 0;
        for (final var elem : FORECAST_BAR) {
            final String MAX_MIN_FORECAST = (elem.getElementsByTag("a").size() > 0 ?
                        elem.getElementsByTag("a").get(0).text() : "");
            if(!MAX_MIN_FORECAST.equals("")) {
                final String[] tokens = MAX_MIN_FORECAST.trim().split(" ");
                // System.err.println(Arrays.toString(tokens));
                if (tokens.length == 1) continue;
                if (tokens.length == 5) {
                    final String KEY = tokens[0] + " " + Integer.toString(TODAY_DATE + 1);
                    final HashMap<String, Pair<Integer, Integer>> MAX_MIN = new HashMap<>();
                    MAX_MIN.put("C", new Pair<Integer,Integer>(strToInt(tokens[1]), strToInt(tokens[3])));
                    MAX_MIN.put("F", new Pair<Integer,Integer>(strToInt(tokens[2]), strToInt(tokens[4])));
                    FORECAST.put(KEY.trim(), MAX_MIN);
                    FRCT_ORDER.put(count, KEY.trim());
                    count++;
                    continue;
                }
                final String KEY = tokens[0] + " " + tokens[1];
                final HashMap<String, Pair<Integer, Integer>> MAX_MIN = new HashMap<>();
                MAX_MIN.put("C", new Pair<Integer,Integer>(strToInt(tokens[2]), strToInt(tokens[4])));
                MAX_MIN.put("F", new Pair<Integer,Integer>(strToInt(tokens[3]), strToInt(tokens[5])));
                FORECAST.put(KEY.trim(), MAX_MIN);
                FRCT_ORDER.put(count, KEY.trim());
                count++;
            }

        }

        System.out.println(FORECAST.size());
        for (Integer i = 0; i < FRCT_ORDER.size(); i++) {
            final String K = FRCT_ORDER.get(i);
            System.out.println(K + ": " + FORECAST.get(K));
        }

    }

    private static int strToInt(final String str) {
        final String numberStr = str.replaceAll("[^\\d]", "");
        return Integer.parseInt(numberStr);
    }
}
