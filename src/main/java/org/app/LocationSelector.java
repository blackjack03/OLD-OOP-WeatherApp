package org.app;

import java.util.*;
import java.io.*;

public class LocationSelector implements LocationSelectorInterface {

    private final String CSV_PATH =
            "src%Smain%Sjava%Sorg%Sfiles%Sworldcities.csv"
            .replace("%S", File.separator);

    final List<Map<String, String>> CSV;
    final Map<Integer, Map<String, String>> CITIES_MAP = new HashMap<>();

    public LocationSelector() {
        try {
            CSV = CSVParser.readCSVToMap(CSV_PATH);
            for (int i = 0; i < CSV.size(); i++) {
                final int idx = Integer.parseInt(CSV.get(i).get("id"));
                CITIES_MAP.put(idx, CSV.get(i));
            }
        } catch (final Exception err) {
            throw new Error(err);
        }
        System.out.println("Cities ready!");
    }

    @Override
    public List<Pair<String, Integer>> getPossibleLocations(final String txt) {
        final List<Pair<String, Integer>> possibleLocations = new ArrayList<>();

        for (int i = 0; i < CSV.size(); i++) {
            final Map<String, String> entry = CSV.get(i);
            final String cityName = entry.get("city").toLowerCase();
            final String cityNameAscii = entry.get("city_ascii").toLowerCase();
            if (cityName.contains(txt) || cityNameAscii.contains(txt)) {
                final var city = new Pair<>(entry.get("city"), Integer.parseInt(entry.get("id")));
                possibleLocations.add(city);
            }
        }

        return possibleLocations;
    }

    @Override
    public Map<String, String> getByID(final int ID) {
        return CITIES_MAP.get(ID);
    }

}
