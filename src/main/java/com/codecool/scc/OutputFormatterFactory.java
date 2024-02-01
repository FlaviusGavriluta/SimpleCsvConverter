package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;
import com.codecool.scc.output.OutputFormatter;

public interface OutputFormatterFactory {
    OutputFormatter createByFormat(OutputFormat outputFormat);
}
