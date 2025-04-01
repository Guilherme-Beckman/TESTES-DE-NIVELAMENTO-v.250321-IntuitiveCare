package com.intituitivecare.transformacaodedados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import com.intituitivecare.transformacaodedados.abbreviations.Abbreviations;

public class PDFExtractorServiceTest {
	@Test
	public void shouldExtractData() throws URISyntaxException {
		File anexo = new File(getClass()
				.getResource("/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf").toURI());
		PDFExtractorService pdfExtractorService = new PDFExtractorService();
		List<Abbreviations> abbreviations = new ArrayList<>();
		abbreviations.add(Abbreviations.OD);
		abbreviations.add(Abbreviations.AMB);
		List<List<String>> tableData = pdfExtractorService.extractTableData(anexo, abbreviations);
		
		assertNotNull(tableData, "A lista de dados não deve ser nula");
        assertFalse(tableData.isEmpty(), "A lista de dados não deve estar vazia");
        List<String> header = tableData.get(0);
        assertEquals(13, header.size(), "A row header deve conter 13 celulas");
        List<String> expectedList = List.of(
        	    "PROCEDIMENTO", 
        	    "RN (alteração)", 
        	    "VIGÊNCIA", 
        	    "Seg. Odontológica", 
        	    "Seg. Ambulatorial", 
        	    "HCO", 
        	    "HSO", 
        	    "REF", 
        	    "PAC", 
        	    "DUT", 
        	    "SUBGRUPO", 
        	    "GRUPO", 
        	    "CAPÍTULO"
        	);
        assertTrue(header.containsAll(expectedList), "O header deve conter todos os elementos");


	}
}
