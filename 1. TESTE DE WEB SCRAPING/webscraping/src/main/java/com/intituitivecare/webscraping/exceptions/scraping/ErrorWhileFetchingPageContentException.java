package com.intituitivecare.webscraping.exceptions.scraping;

public class ErrorWhileFetchingPageContentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public ErrorWhileFetchingPageContentException() {
        super("Erro ao tentar buscar o conteúdo da página.");
    }
}
