package com.codecool.scc.output;

public class XmlOutputFormatter implements OutputFormatter {
    @Override
    public void printToConsole(Object data) {
        System.out.println("XML Output: " + data.toString());
    }
}