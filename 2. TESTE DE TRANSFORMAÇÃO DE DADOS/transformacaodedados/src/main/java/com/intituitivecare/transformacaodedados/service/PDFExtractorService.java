package com.intituitivecare.transformacaodedados.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import com.intituitivecare.transformacaodedados.abbreviations.Abbreviations;

import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.PageIterator;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
 
@Service
public class PDFExtractorService {

	 
    public List<List<String>> extractTableData(File pdf, List<Abbreviations> abbreviations) {
        List<List<String>> tableData = new ArrayList<>();
        System.out.println("ta aqui dentro");
        
        try (PDDocument document = Loader.loadPDF(pdf)) {
        	PDFTextStripper pdfTextStripper = new PDFTextStripper();
        	pdfTextStripper.setStartPage(3); 
            pdfTextStripper.setEndPage(3);
        	String textPdf = pdfTextStripper.getText(document);
            
            Map<String, String> abbreviationsLegend = LegendExtractor.extractLegendMap(textPdf);
            
            
            boolean headerProcessed = false; 
            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
            PageIterator pi = new ObjectExtractor(document).extract();
            while (pi.hasNext()) {
                Page page = pi.next();
                List<Table> tables = sea.extract(page);
                for (Table table : tables) {
                    List<List<RectangularTextContainer>> rows = table.getRows();
 
                    for (int j = 0; j < rows.size(); j++) {
                        if (headerProcessed && j == 0) {
                            continue;
                        }
 
                        List<RectangularTextContainer> cells = rows.get(j);
                        if (cells.get(0).getText().isEmpty() || cells.get(0).getText().isBlank()) {
                            continue;
                        }
 
                        List<String> rowTable = new ArrayList<>();
                        for (int h = 0; h < cells.size(); h++) {
                            RectangularTextContainer cellContent = cells.get(h);
                            String text = cellContent.getText().replace("\r", " ").trim();
                            
                            for (Abbreviations abbr : abbreviations) {
                                if (text.equalsIgnoreCase(abbr.name())) {
                                    if (abbreviationsLegend.containsKey(abbr.name())) {
                                        text = abbreviationsLegend.get(abbr.name());
                                    }
                                }
                            }
                            
                                rowTable.add(text); 
                        }

 
                        if (!headerProcessed && j == 0) {
                            headerProcessed = true;
                        }
 
                        tableData.add(rowTable);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        printTableData(tableData);
        return tableData;
    }
 
    private void printTableData(List<List<String>> tableData) {
 
        for (List<String> row : tableData) {
            for (int i = 0; i < row.size(); i++) {
                String cell = row.get(i);
                System.out.print(cell+" | ");
                if (i < row.size() - 1) {
                }
            }
            System.out.println();
        }
    }
}
