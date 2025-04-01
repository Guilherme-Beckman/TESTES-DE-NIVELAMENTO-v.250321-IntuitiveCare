package com.intituitivecare.webscraping.exceptions.download;

public class ErrorWhileDownloadFilesException extends RuntimeException {
	   private static final long serialVersionUID = 1L;

		public ErrorWhileDownloadFilesException() {
	        super("Erro ao fazer download dos arquivos");
	    }
}
