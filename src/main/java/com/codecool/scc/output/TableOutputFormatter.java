package com.codecool.scc.output;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TableOutputFormatter implements OutputFormatter {

    @Override
    public void printToConsole(List<String[]> csvData) {
        if (csvData == null || csvData.isEmpty()) {
            System.out.println("No data to display.");
            return;
        }

        // Determine the maximum width for each column
        int[] maxWidths = determineMaxWidths(csvData);

        // Print the table header and rows
        for (int i = 0; i < csvData.size(); i++) {
            String[] row = csvData.get(i);
            for (int j = 0; j < row.length; j++) {
                String cell = row[j];
                System.out.print(String.format("%-" + maxWidths[j] + "s", cell));
                System.out.print(" | "); // Column separator
            }
            System.out.println(); // Move to the next line after printing all columns

            // Optionally, print a separator line after the header
            if (i == 0) {
                printSeparatorLine(maxWidths);
            }
        }
    }

    private int[] determineMaxWidths(List<String[]> csvData) {
        int[] maxWidths = new int[csvData.get(0).length]; // Assume all rows have the same number of columns

        for (String[] row : csvData) {
            for (int i = 0; i < row.length; i++) {
                maxWidths[i] = Math.max(maxWidths[i], row[i].length());
            }
        }

        return maxWidths;
    }

    private void printSeparatorLine(int[] maxWidths) {
        for (int width : maxWidths) {
            System.out.print(new String(new char[width]).replace("\0", "-"));
            System.out.print("---"); // Adjust for the column separator
        }
        System.out.println();
    }
}