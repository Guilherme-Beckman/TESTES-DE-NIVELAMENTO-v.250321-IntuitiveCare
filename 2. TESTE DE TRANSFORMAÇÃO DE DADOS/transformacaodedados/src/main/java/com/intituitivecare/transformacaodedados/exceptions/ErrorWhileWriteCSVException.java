package com.intituitivecare.transformacaodedados.exceptions;

public class ErrorWhileWriteCSVException extends RuntimeException {

    public ErrorWhileWriteCSVException() {
        super("Erro ao escrever o arquivo CSV.");
    }

}
