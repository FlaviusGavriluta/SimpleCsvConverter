package com.codecool.scc;

import com.codecool.scc.model.OutputFormat;
import com.codecool.scc.strategy.FormatStrategy;
import com.codecool.scc.strategy.TableFormatStrategy;

import java.io.File;
import java.util.Map;

public class SimpleCsvConverter {

    private Map<OutputFormat, FormatStrategy> formatStrategyMap;

    public SimpleCsvConverter(Map<OutputFormat, FormatStrategy> formatStrategyMap) {
        this.formatStrategyMap = formatStrategyMap;
    }

    public void convert(String csvFilePath, OutputFormat outputFormat) {
        File csvFile=new File(csvFilePath);
        FormatStrategy strategy = formatStrategyMap.getOrDefault(outputFormat, new TableFormatStrategy());
        strategy.convert(csvFile);
    }
}
