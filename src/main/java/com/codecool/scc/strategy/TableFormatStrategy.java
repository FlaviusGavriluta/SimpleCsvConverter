package com.codecool.scc.strategy;

import java.io.File;

public class TableFormatStrategy implements FormatStrategy {
    @Override
    public void convert(File csvFile) {
        // Implementation for displaying in table format
        System.out.println("Displaying " + csvFile.getName() + " in table format.");
    }
}