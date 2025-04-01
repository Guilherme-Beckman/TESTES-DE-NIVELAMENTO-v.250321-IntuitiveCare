package com.intituitivecare.webscraping.exceptions.compress;

public class ErrorWhileCompressFilesException extends RuntimeException {
	   private static final long serialVersionUID = 1L;

		public ErrorWhileCompressFilesException() {
	        super("Erro ao comprimir os arquivos");
	    }
}
