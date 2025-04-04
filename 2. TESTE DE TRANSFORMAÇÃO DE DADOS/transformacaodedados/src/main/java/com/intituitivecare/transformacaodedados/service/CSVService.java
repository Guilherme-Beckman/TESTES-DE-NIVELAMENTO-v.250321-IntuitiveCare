package com.intituitivecare.transformacaodedados.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.intituitivecare.transformacaodedados.exceptions.ErrorWhileWriteCSVException;

@Service
public class CSVService {

	public void writeCSV(List<List<String>> tableData, String filePath) {
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            for (List<String> row : tableData) {
                List<String> formattedCells = new ArrayList<>();
                for (String cell : row) {
                        formattedCells.add("\"" + cell + "\"");
                }
                csvWriter.append(String.join(",", formattedCells));
                csvWriter.append("\n");
            }
            csvWriter.flush();
        } catch (IOException e) {
        	throw new ErrorWhileWriteCSVException();
        }
    }

}
