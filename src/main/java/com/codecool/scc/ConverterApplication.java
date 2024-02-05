package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;
import com.codecool.scc.output.OutputFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class ConverterApplication {
    private final OutputFormatterFactory formatterFactory;

    @Autowired
    public ConverterApplication(OutputFormatterFactory formatterFactory) {
        this.formatterFactory = formatterFactory;
    }

    public void run(String csvFilePath, OutputFormat outputFormat) {
        System.out.println("Output Format: " + outputFormat);
        System.out.println("CSV File Path: " + csvFilePath);

        List<String[]> csvData = FileReaderClass.readData(new File(csvFilePath));
        System.out.println("Data from CSV file:");

        OutputFormatter formatter = formatterFactory.createByFormat(outputFormat);

        if (formatter != null) {
            formatter.printToConsole(csvData);
            System.out.println("Conversion completed successfully.");
        } else {
            System.out.println("Invalid output format. Using default format: TABLE");
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConverterApplication converterApplication = context.getBean(ConverterApplication.class);

        if (args.length < 1 || args.length > 2) {
            System.out.println("Invalid number of arguments. Usage: java -jar app.jar [format] file.csv");
            System.exit(1);
        }

        OutputFormat outputFormat = OutputFormat.TABLE;
        if (args.length == 2) {
            outputFormat = getOutputFormat(args[0]);
        }
        String csvFilePath = args[args.length - 1];

        converterApplication.run(csvFilePath, outputFormat);
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
}