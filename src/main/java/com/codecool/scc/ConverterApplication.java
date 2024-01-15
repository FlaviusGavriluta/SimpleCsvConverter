package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;

public class ConverterApplication {
    private OutputFormat outputFormat;
    private String csvFilePath;
    private SimpleCsvConverter csvConverter;

    public ConverterApplication(OutputFormat outputFormat, String csvFilePath) {
        this.outputFormat = outputFormat;
        this.csvFilePath = csvFilePath;
        this.csvConverter = new SimpleCsvConverter();
    }

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
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
        csvConverter.convert(csvFilePath,outputFormat.name().toLowerCase());
    }
}