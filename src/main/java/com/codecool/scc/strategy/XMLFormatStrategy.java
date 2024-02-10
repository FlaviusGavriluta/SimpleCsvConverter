package com.codecool.scc.strategy;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class XMLFormatStrategy implements FormatStrategy {
    @Override
    public void convert(File csvFile) {
        // Implementation for converting to XML
        System.out.println("Converting " + csvFile.getName() + " to XML format.");
    }
}