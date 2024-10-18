package org.app;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVParser {

    public static List<Map<String, String>> readCSVToMap(String filePath) throws IOException, CsvException {
        final List<Map<String, String>> resultList = new ArrayList<>();

        try (final CSVReader reader = new CSVReader(new FileReader(filePath))) {
            final List<String[]> allRows = reader.readAll();

            if (allRows.isEmpty()) {
                return resultList;
            }

            final String[] header = allRows.get(0);

            for (int i = 1; i < allRows.size(); i++) {
                final String[] row = allRows.get(i);
                final Map<String, String> rowMap = new HashMap<>();

                for (int j = 0; j < header.length; j++) {
                    rowMap.put(header[j], row[j]);
                }

                resultList.add(rowMap);
            }
        }

        return resultList;
    }

}
