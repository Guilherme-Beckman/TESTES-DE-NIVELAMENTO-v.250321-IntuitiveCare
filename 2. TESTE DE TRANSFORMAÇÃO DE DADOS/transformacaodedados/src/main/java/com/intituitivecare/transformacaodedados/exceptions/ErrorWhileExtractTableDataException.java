package com.intituitivecare.transformacaodedados.exceptions;

public class ErrorWhileExtractTableDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public ErrorWhileExtractTableDataException() {
        super("Erro ao extrair os dados da tabela.");
    }

}
