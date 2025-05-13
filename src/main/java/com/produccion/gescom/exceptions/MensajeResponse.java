package com.produccion.gescom.exceptions;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
//public class ErrorResponse {
public class MensajeResponse {
	private Integer resultado;
	private Object dato;
	private String mensaje;
	private Date fechHora;
    private String codigoError;
    private List<String> detalles;
    private String className;

    public MensajeResponse(Integer resultado, Object dato, String codigoError, String mensaje, List<String> detalles, String className) {
    	this.resultado = resultado;
    	this.dato = dato;
        this.fechHora = new Date();
        this.codigoError = codigoError;
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.className = className;
    }
}
