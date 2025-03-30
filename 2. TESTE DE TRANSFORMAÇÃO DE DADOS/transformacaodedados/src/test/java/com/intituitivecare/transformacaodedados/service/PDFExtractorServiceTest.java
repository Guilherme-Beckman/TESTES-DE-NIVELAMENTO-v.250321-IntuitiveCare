package com.intituitivecare.transformacaodedados.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

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
		List<String[]> tableData = pdfExtractorService.extractTableData(anexo);
		
		assertNotNull(tableData, "A lista de dados não deve ser nula");
        assertFalse(tableData.isEmpty(), "A lista de dados não deve estar vazia");
        
        String[] cabecalho = tableData.get(0);
        assertTrue(cabecalho[0].contains("Procedimentos") || cabecalho[0].contains("Eventos"),
                "O cabeçalho deve conter a palavra 'Procedimentos' ou 'Eventos'");
	}
}
