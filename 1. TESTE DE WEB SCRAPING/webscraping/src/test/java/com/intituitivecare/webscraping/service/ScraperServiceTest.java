package com.intituitivecare.webscraping.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ScraperServiceTest {
	@Test
	public void shouldFetchPageContent() {
		//Instanciar um novo ScraperService
		ScraperService scraperService = new  ScraperService();
		
		//Act: chamar o metodo que pega o conteudo da pagina
		String content = scraperService.fetchPageContent();
		
		//Não pode ser nulo
		assertNotNull(content, "O conteúdo da página não pode ser null");
		
	}
}
