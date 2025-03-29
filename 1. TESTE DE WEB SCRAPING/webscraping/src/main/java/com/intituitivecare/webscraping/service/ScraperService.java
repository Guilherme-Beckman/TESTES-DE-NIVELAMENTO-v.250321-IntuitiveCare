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
			Document doc = connectToUrl();
			Elements downloadLinks = doc.select("li a");

			return extractValidPdfLinks(downloadLinks);

		} catch (IOException e) {
			throw new ErrorWhileFetchingPageContentException();
		}
	}

	private List<String> extractValidPdfLinks(Elements elements) {
		List<String> findedLinks = new ArrayList<>();
		for (Element link : elements) {
			String href = link.attr("href");
			String linkText = link.text();
			if (isValidPdfLink(linkText, href)) {
				findedLinks.add(href);
			}
		}
		return findedLinks;
	}

	private boolean isValidPdfLink(String linkText, String linkUrl) {
		return linkText.contains("Anexo") && linkUrl.endsWith(".pdf");
	}

}
