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
            List<String[]> csvData = FileReaderClass.readData(csvFile);
            formatter.printToConsole(csvData);
        } else {
            System.out.println("Invalid output format. Using default format: TABLE");
        }
    }
}