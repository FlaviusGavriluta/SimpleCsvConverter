package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;
import com.codecool.scc.output.JsonOutputFormatter;
import com.codecool.scc.output.OutputFormatter;
import com.codecool.scc.output.TableOutputFormatter;
import com.codecool.scc.output.XmlOutputFormatter;

public class ConcreteOutputFormatterFactory implements OutputFormatterFactory {
    @Override
    public OutputFormatter createByFormat(OutputFormat outputFormat) {
        switch (outputFormat) {
            case JSON:
                return new JsonOutputFormatter();
            case XML:
                return new XmlOutputFormatter();
            case TABLE:
                return new TableOutputFormatter();
            default:
                throw new IllegalArgumentException("Unsupported output format: " + outputFormat);
        }
    }
}