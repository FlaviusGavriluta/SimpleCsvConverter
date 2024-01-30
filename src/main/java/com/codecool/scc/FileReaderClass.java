package com.codecool.scc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReaderClass {
    public List<String[]> readData(File file) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading data from the file: " + e.getMessage());
        }
        return data;
    }
}