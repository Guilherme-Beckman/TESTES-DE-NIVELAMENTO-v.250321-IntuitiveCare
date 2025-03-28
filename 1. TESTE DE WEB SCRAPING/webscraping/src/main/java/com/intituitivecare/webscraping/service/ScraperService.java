package com.intituitivecare.webscraping.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.intituitivecare.webscraping.exceptions.scraping.ErrorWhileFetchingPageContentException;


@Service
public class ScraperService {
	private String URL = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
	public String fetchPageContent() {
		try {
			Document doc = Jsoup.connect(URL).get();
			return doc.html();
		} catch (IOException e) {
			throw new ErrorWhileFetchingPageContentException();
		}
	}

}
