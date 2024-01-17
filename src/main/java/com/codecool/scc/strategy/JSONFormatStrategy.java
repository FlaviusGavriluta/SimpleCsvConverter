package com.codecool.scc.strategy;

import java.io.File;

public class JSONFormatStrategy implements FormatStrategy {
    @Override
    public void convert(File csvFile) {
        // Implementation for converting to JSON
        System.out.println("Converting " + csvFile.getName() + " to JSON format.");
    }
}
