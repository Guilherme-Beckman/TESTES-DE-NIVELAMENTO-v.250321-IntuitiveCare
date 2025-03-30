package com.intituitivecare.transformacaodedados.service;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegendExtractor {

    /**
     * Extrai e armazena os mapeamentos da legenda a partir de um texto.
     * Exemplo de entrada:
     * "Legenda:\nOD: Seg. Odontológica\nAMB: Seg. Ambulatorial\nHCO: Seg. Hospitalar Com Obstetrícia\n..."
     */
    public static Map<String, String> extractLegendMap(String legendText) {
        Map<String, String> legendMap = new HashMap<>();
        
        // Caso o texto contenha o cabeçalho "Legenda:", removemos-o
        legendText = legendText.replace("Legenda:", "").trim();
        
        // Dividindo o texto por linhas
        String[] lines = legendText.split("\\r?\\n");
        
        // Expressão regular para capturar: abreviação, dois pontos e a descrição
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
