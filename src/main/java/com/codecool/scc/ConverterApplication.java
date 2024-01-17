package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;
import com.codecool.scc.strategy.FormatStrategy;
import com.codecool.scc.strategy.JSONFormatStrategy;
import com.codecool.scc.strategy.TableFormatStrategy;
import com.codecool.scc.strategy.XMLFormatStrategy;

import java.util.HashMap;
import java.util.Map;

public class ConverterApplication {
    private OutputFormat outputFormat;
    private String csvFilePath;

    public ConverterApplication(OutputFormat outputFormat, String csvFilePath) {
        this.outputFormat = outputFormat;
        this.csvFilePath = csvFilePath;
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

        Map<OutputFormat, FormatStrategy> formatStrategyMap = new HashMap<>();
        formatStrategyMap.put(OutputFormat.JSON, new JSONFormatStrategy());
        formatStrategyMap.put(OutputFormat.XML, new XMLFormatStrategy());
        formatStrategyMap.put(OutputFormat.TABLE, new TableFormatStrategy());


        ConverterApplication converterApplication = new ConverterApplication(outputFormat, csvFilePath);
        converterApplication.run(formatStrategyMap);
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

    private void run(Map<OutputFormat, FormatStrategy> formatStrategyMap) {
        System.out.println("Output Format: " + outputFormat);
        System.out.println("CSV File Path: " + csvFilePath);

        SimpleCsvConverter csvConverter = new SimpleCsvConverter(formatStrategyMap);
        csvConverter.convert(csvFilePath, outputFormat);
    }
}