package com.codecool.scc.output;

public class JsonOutputFormatter implements OutputFormatter {
    @Override
    public void printToConsole(Object data) {
        System.out.println("JSON Output: " + data.toString());
    }
}