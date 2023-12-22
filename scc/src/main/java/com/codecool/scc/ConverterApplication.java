package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;

public class ConverterApplication {
    public static void main(String[] args) {
        String csvFilePath = null;

        switch (args.length) {
            case 0 -> {
                System.out.println("No input file defined");
                System.exit(1);
            }
            case 1 -> csvFilePath = args[0];
        }
        System.out.println("CSV File Path: " + csvFilePath);
    }
}