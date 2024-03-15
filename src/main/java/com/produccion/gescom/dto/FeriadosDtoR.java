package com.produccion.gescom.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class FeriadosDtoR {
	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long idferiado;
	private LocalDate fechaferia;
	private String descripcion;
	private Long idsocieda;
	private Long idusuario;
	
}
