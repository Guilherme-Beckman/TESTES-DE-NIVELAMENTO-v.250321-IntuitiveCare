package com.intituitivecare.transformacaodedados.exceptions;

public class ErrorWhileWriteCSVException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public ErrorWhileWriteCSVException() {
        super("Erro ao escrever o arquivo CSV.");
    }

}
