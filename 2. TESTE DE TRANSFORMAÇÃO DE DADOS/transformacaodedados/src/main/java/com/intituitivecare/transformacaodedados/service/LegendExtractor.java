package com.intituitivecare.transformacaodedados.service;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegendExtractor {
    public static Map<String, String> extractLegendMap(String legendText) {
        Map<String, String> legendMap = new HashMap<>();
        legendText = legendText.replace("Legenda:", "").trim();
        String[] lines = legendText.split("\\r?\\n");
        Pattern pattern = Pattern.compile("^(\\w+)\\s*:\\s*(.+)$");
        
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String abbreviation = matcher.group(1).trim();
                String description = matcher.group(2).trim();
                legendMap.put(abbreviation, description);
            }
        }
        return legendMap;
    }
    

}
