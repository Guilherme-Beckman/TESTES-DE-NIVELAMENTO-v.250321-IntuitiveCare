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
import com.intituitivecare.transformacaodedados.exceptions.ErrorWhileExtractTableDataException;

import technology.tabula.*;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

@Service
public class PDFExtractorService {

    private static final int TARGET_PAGE_NUMBER = 3;
    private static final String LINE_SEPARATOR = " | ";

    public List<List<String>> extractTableData(File pdfFile, List<Abbreviations> abbreviations) {
        List<List<String>> tableData = new ArrayList<>();

        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            String pageText = extractPageText(document, TARGET_PAGE_NUMBER);
            Map<String, String> abbreviationsLegend = LegendExtractor.extractLegendMap(pageText);

            processDocumentPages(document, tableData, abbreviations, abbreviationsLegend);
        } catch (Exception e) {
        	throw new ErrorWhileExtractTableDataException();
        }

        //logTableData(tableData);
        return tableData;
    }

    private String extractPageText(PDDocument document, int pageNumber) throws Exception {
        PDFTextStripper textStripper = new PDFTextStripper();
        textStripper.setStartPage(pageNumber);
        textStripper.setEndPage(pageNumber);
        return textStripper.getText(document);
    }

    private void processDocumentPages(PDDocument document, List<List<String>> tableData,
                                     List<Abbreviations> abbreviations, Map<String, String> abbreviationsLegend) {
        SpreadsheetExtractionAlgorithm spreadsheetExtractor = new SpreadsheetExtractionAlgorithm();
        PageIterator pageIterator = new ObjectExtractor(document).extract();
        boolean isHeaderProcessed = false;

        while (pageIterator.hasNext()) {
            Page currentPage = pageIterator.next();
            processPageTables(currentPage, spreadsheetExtractor, tableData, 
                             abbreviations, abbreviationsLegend, isHeaderProcessed);
        }
    }

    private void processPageTables(Page page, SpreadsheetExtractionAlgorithm extractor,
                                  List<List<String>> tableData, List<Abbreviations> abbreviations,
                                  Map<String, String> abbreviationsLegend, boolean isHeaderProcessed) {
        List<Table> tables = extractor.extract(page);
        
        for (Table table : tables) {
            processTableRows(table.getRows(), tableData, abbreviations, 
                            abbreviationsLegend, isHeaderProcessed);
        }
    }

    private void processTableRows(List<List<RectangularTextContainer>> tableRows, List<List<String>> tableData,
                                 List<Abbreviations> abbreviations, Map<String, String> abbreviationsLegend,
                                 boolean isHeaderProcessed) {
        boolean headerProcessed = isHeaderProcessed;
        
        for (int rowIndex = 0; rowIndex < tableRows.size(); rowIndex++) {
            List<RectangularTextContainer> rowCells = tableRows.get(rowIndex);
            
            if (shouldSkipRow(headerProcessed, rowIndex, rowCells)) {
                continue;
            }

            List<String> processedRow = processRowCells(rowCells, abbreviations, abbreviationsLegend);
            tableData.add(processedRow);
            
            if (!headerProcessed && rowIndex == 0) {
                headerProcessed = true;
            }
        }
    }

    private boolean shouldSkipRow(boolean headerProcessed, int rowIndex, List<RectangularTextContainer> rowCells) {
        return (headerProcessed && rowIndex == 0) || 
               rowCells.get(0).getText().isBlank();
    }

    private List<String> processRowCells(List<RectangularTextContainer> rowCells,
                                        List<Abbreviations> abbreviations,
                                        Map<String, String> abbreviationsLegend) {
        List<String> processedRow = new ArrayList<>();
        
        for (RectangularTextContainer cell : rowCells) {
            String cellContent = cleanCellContent(cell.getText());
            processedRow.add(resolveAbbreviations(cellContent, abbreviations, abbreviationsLegend));
        }
        
        return processedRow;
    }

    private String cleanCellContent(String rawContent) {
        return rawContent.replace("\r", " ")
                         .trim();
    }

    private String resolveAbbreviations(String text, List<Abbreviations> abbreviations,
                                        Map<String, String> abbreviationsLegend) {
        for (Abbreviations abbreviation : abbreviations) {
            if (text.equalsIgnoreCase(abbreviation.name())) {
                return abbreviationsLegend.getOrDefault(abbreviation.name(), text);
            }
        }
        return text;
    }
    private void logTableData(List<List<String>> tableData) {
        for (List<String> row : tableData) {
            System.out.println(String.join(LINE_SEPARATOR, row));
        }
    }
}