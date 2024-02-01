package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;
import com.codecool.scc.output.OutputFormatter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConverterApplication {
    private OutputFormat outputFormat;
    private String csvFilePath;
    private OutputFormatterFactory formatterFactory;

    public ConverterApplication(OutputFormat outputFormat, String csvFilePath, OutputFormatterFactory formatterFactory) {
        this.outputFormat = outputFormat;
        this.csvFilePath = csvFilePath;
        this.formatterFactory = formatterFactory;
    }

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Invalid number of arguments. Usage: java -jar app.jar [format] file.csv");
            System.exit(1);
        }

        OutputFormat outputFormat = OutputFormat.TABLE;
        String csvFilePath = args[args.length - 1];

        if (args.length == 2) {
            outputFormat = getOutputFormat(args[0]);
        }

        OutputFormatterFactory outputFormatterFactory = new ConcreteOutputFormatterFactory();
        ConverterApplication converterApplication = new ConverterApplication(outputFormat, csvFilePath, outputFormatterFactory);
        converterApplication.run();
    }

    private static OutputFormat getOutputFormat(String format) {
        try {
            return OutputFormat.valueOf(format.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid output format. Supported formats: JSON, XML, and TABLE");
            System.exit(1);
            return null;
        }
    }

    private void run() {
        System.out.println("Output Format: " + outputFormat);
        System.out.println("CSV File Path: " + csvFilePath);

        List<String[]> csvData = SimpleCsvConverter.readData(new File(csvFilePath));
        OutputFormatter formatter = formatterFactory.createByFormat(outputFormat);

        if (formatter != null) {
            formatter.printToConsole(csvData);
        } else {
            System.out.println("Invalid output format. Using default format: TABLE");
        }

        SimpleCsvConverter csvConverter = new SimpleCsvConverter(formatterFactory);
        csvConverter.convert(new File(csvFilePath), outputFormat);
    }
}