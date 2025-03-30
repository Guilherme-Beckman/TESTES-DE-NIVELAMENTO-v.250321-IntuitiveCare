package com.intituitivecare.transformacaodedados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

public class PDFExtractorServiceTest {
	@Test
	public void shouldExtractData() throws URISyntaxException {
		// pegar o pdf na pasta resources
		File anexo = new File(getClass()
				.getResource("/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf").toURI());
		// instanciar servico de extracao
		PDFExtractorService pdfExtractorService = new PDFExtractorService();
		// extrair dados da tabela
		List<List<String>> tableData = pdfExtractorService.extractTableData(anexo);
		
		assertNotNull(tableData, "A lista de dados não deve ser nula");
        assertFalse(tableData.isEmpty(), "A lista de dados não deve estar vazia");
        List<String> header = tableData.get(0);
        assertEquals(13, header.size(), "A row header deve conter 13 celulas");
        List<String> expectedList = List.of(
        	    "PROCEDIMENTO, ", 
        	    "RN (alteração), ", 
        	    "VIGÊNCIA, ", 
        	    "OD, ", 
        	    "AMB, ", 
        	    "HCO, ", 
        	    "HSO, ", 
        	    "REF, ", 
        	    "PAC, ", 
        	    "DUT, ", 
        	    "SUBGRUPO, ", 
        	    "GRUPO, ", 
        	    "CAPÍTULO"
        	);        assertTrue(header.containsAll(expectedList), "O header deve conter todos os elementos");


	}
}
