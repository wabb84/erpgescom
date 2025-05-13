package com.produccion.gescom.exceptions;

public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String codigoError;

    public CustomException(String codigoError) {
        super(ErrorMessages.getErrorMessage(codigoError)); // Obtiene el mensaje de ErrorMessages
        this.codigoError = codigoError;
    }

    public String getCodigoError() {
        return codigoError;
    }

    @Override
    public String getMessage() {
        return ErrorMessages.getErrorMessage(codigoError); // Obtiene el mensaje de ErrorMessages
    }
}