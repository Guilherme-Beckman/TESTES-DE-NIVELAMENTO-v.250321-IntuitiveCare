package com.intituitivecare.webscraping.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.intituitivecare.webscraping.exceptions.scraping.ErrorWhileFetchingPageContentException;

@Service
public class ScraperService {
	private static final String URL = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

	private Document connectToUrl() throws IOException {
		return Jsoup.connect(URL).get();
	}

	public List<String> fetchPdfLinks() {
		try {
			List<String> findedLinks = new ArrayList<>();
			Document doc = connectToUrl();
			Elements downloadLinks = doc.select("li a");

			for (Element link : downloadLinks) {
				String href = link.attr("href");
				String linkText = link.text();
				if (linkText.contains("Anexo") && href.endsWith(".pdf")) {
					findedLinks.add(href);
				}
			}
			return findedLinks;

		} catch (IOException e) {
			throw new ErrorWhileFetchingPageContentException();
		}
	}

}
