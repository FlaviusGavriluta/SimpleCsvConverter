package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;

import java.util.HashMap;
import java.util.Map;

public class SimpleCsvConverter {
    private final Map<OutputFormat, Runnable> converters;

    public SimpleCsvConverter() {
        converters = new HashMap<>();
        converters.put(OutputFormat.JSON, this::convertToJson);
        converters.put(OutputFormat.TABLE, this::convertToTable);
        converters.put(OutputFormat.XML, this::convertToXml);
    }

    public void convert(String file, String outputFormat) {
        converters.getOrDefault(outputFormat, this::convertToTable).run();
    }

    private void convertToJson() {
        System.out.println("I convert CSV to JSON format");
    }

    private void convertToTable() {
        System.out.println("I convert CSV to table format");
    }

    private void convertToXml() {
        System.out.println("I convert CSV to XML format");
    }
}
