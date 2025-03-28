package com.intituitivecare.webscraping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ScraperServiceTest {
	@Test
	public void shouldFetchPdfLinks() {
		ScraperService scraperService = new ScraperService();
		
		List<String> pdfLinks = scraperService.fetchPdfLinks();
		
		assertNotNull(pdfLinks, "Os links não podem ser null");
		assertEquals(2, pdfLinks.size(), "A lista de links deve conter exatamente dois elementos");

	}
}
