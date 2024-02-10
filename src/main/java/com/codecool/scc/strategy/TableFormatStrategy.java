package com.codecool.scc.strategy;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class TableFormatStrategy implements FormatStrategy {
    @Override
    public void convert(File csvFile) {
        // Implementation for displaying in table format
        System.out.println("Displaying " + csvFile.getName() + " in table format.");
    }
}