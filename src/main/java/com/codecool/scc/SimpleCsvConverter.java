package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;
import com.codecool.scc.output.OutputFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleCsvConverter {
    private OutputFormatterFactory formatterFactory;

    public SimpleCsvConverter(OutputFormatterFactory formatterFactory) {
        this.formatterFactory = formatterFactory;
    }

    public void convert(File csvFile, OutputFormat outputFormat) {
        OutputFormatter formatter = formatterFactory.createByFormat(outputFormat);

        if (formatter != null) {
            // Read CSV data and format it using the selected formatter
            List<String[]> csvData = readData(csvFile);
            formatter.printToConsole(csvData);
        } else {
            System.out.println("Invalid output format. Using default format: TABLE");
        }
    }

    public static List<String[]> readData(File file) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading data from the file: " + e.getMessage());
        }

        return data;
    }
}