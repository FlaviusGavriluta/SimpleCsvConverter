package com.codecool.scc.output;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class XmlOutputFormatter implements OutputFormatter {
    @Override
    public void printToConsole(List<String[]> data) {
        System.out.println("XML Output: " + data.toString());
    }
}