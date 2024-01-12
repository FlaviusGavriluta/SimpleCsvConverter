package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;

public class ConverterApplication {
    private OutputFormat outputFormat;
    private String csvFilePath;

    public ConverterApplication(OutputFormat outputFormat, String csvFilePath) {
        this.outputFormat = outputFormat;
        this.csvFilePath = csvFilePath;
    }

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Invalid number of arguments. Usage: java -jar app.jar [format] file.csv");
            System.exit(1);
        }

        OutputFormat outputFormat = OutputFormat.TABLE;
        String csvFilePath;

        if (args.length == 1) {
            csvFilePath = args[0];
        } else {
            outputFormat = getOutputFormat(args[0]);
            csvFilePath = args[1];
        }

        ConverterApplication converterApplication = new ConverterApplication(outputFormat, csvFilePath);
        converterApplication.run();
    }

    private static OutputFormat getOutputFormat(String format) {
        try {
            return OutputFormat.valueOf(format.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid output format. Supported formats: JSON, XML, TABLE");
            System.exit(1);
            return null; // Unreachable, added to satisfy the compiler
        }
    }

    private void run() {
        System.out.println("Output Format: " + outputFormat);
        System.out.println("CSV File Path: " + csvFilePath);

        // Now you're ready to proceed with integrating Spring Context and defining beans.
    }
}