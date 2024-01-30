package com.codecool.scc.output;

public class TableOutputFormatter implements OutputFormatter {
    @Override
    public void printToConsole(Object data) {
        System.out.println("Table Output: " + data.toString());
    }
}