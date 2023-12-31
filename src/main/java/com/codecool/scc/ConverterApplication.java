package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;

public class ConverterApplication {
    public static void main(String[] args) {
        OutputFormat outputFormat = OutputFormat.TABLE;
        String csvFilePath = null;
        switch (args.length) {
            case 1:
                csvFilePath = args[0];
                break;
            case 2:
                outputFormat = OutputFormat.valueOf(args[0].toUpperCase());
                csvFilePath = args[1];
                break;
            default:
                System.out.println("No input file defined");
                System.exit(1);
        }

        System.out.println("Output Format: " + outputFormat);
        System.out.println("CSV File Path: " + csvFilePath);
    }
}