package com.intituitivecare.webscraping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ScraperServiceTest {
	@Test
	public void shouldFetchPdfLinks() {
		// Instanciar um novo ScraperService
		ScraperService scraperService = new ScraperService();

		// Act: chamar o metodo que pega o conteudo da pagina
		List<String> pdfLinks = scraperService.fetchPdfLinks();

		// Não pode ser nulo
		assertNotNull(pdfLinks, "Os links não podem ser null");
		// A lista deve conter dois elementos
		assertEquals(2, pdfLinks.size(), "A lista de links deve conter exatamente dois elementos");

	}
}
