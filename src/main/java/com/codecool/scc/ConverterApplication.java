package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;
import com.codecool.scc.strategy.FormatStrategy;
import com.codecool.scc.strategy.JsonFormatStrategy;
import com.codecool.scc.strategy.TableFormatStrategy;
import com.codecool.scc.strategy.XmlFormatStrategy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConverterApplication {
    private OutputFormat outputFormat;
    private String csvFilePath;
    private SimpleCsvConverter simpleCsvConverter;

    public ConverterApplication(OutputFormat outputFormat, String csvFilePath) {
        this.outputFormat = outputFormat;
        this.csvFilePath = csvFilePath;
        initializeSimpleCsvConverter();
    }

    private void initializeSimpleCsvConverter() {
        Map<OutputFormat, FormatStrategy> strategyMap = new HashMap<>();
        strategyMap.put(OutputFormat.JSON, new JsonFormatStrategy());
        strategyMap.put(OutputFormat.XML, new XmlFormatStrategy());
        strategyMap.put(OutputFormat.TABLE, new TableFormatStrategy());

        this.simpleCsvConverter = new SimpleCsvConverter(strategyMap);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No input file defined");
            System.exit(1);
        } else if (args.length > 2) {
            System.out.println("Invalid number of arguments. Usage: java -jar app.jar [format] file.csv");
            System.exit(1);
        }

        OutputFormat outputFormat = OutputFormat.TABLE;
        String csvFilePath = null;

        switch (args.length) {
            case 1:
                csvFilePath = args[0];
                break;
            case 2:
                outputFormat = getOutputFormat(args[0]);
                csvFilePath = args[1];
                break;
            default:
                System.out.println("No input file defined");
                System.exit(1);
        }

        ConverterApplication converterApplication = new ConverterApplication(outputFormat, csvFilePath);
        converterApplication.run();
    }

    private static OutputFormat getOutputFormat(String format) {
        try {
            return OutputFormat.valueOf(format.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid output format. Supported formats: JSON, XML and TABLE");
            System.exit(1);
            return null;
        }
    }

    private void run() {
        File csvFile = new File(csvFilePath);
        simpleCsvConverter.convert(csvFile, outputFormat);
    }
}