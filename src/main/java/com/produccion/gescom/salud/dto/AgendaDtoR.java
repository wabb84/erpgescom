package com.produccion.gescom.salud.dto;

import java.io.Serializable;
import java.time.LocalDate;


import lombok.Getter;

@Getter
public class AgendaDtoR implements Serializable{
	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long 	id;
	private Long 	idturnos;
	private Long 	idsocieda;
	private Long	idconsultorio;
	private Long 	idpersprof;
	private String 	anio; 
	private String 	mes;
	private String  dia;
	private String  hora;
	private String  observacion;
	private LocalDate fechaagenda;
	private String  estado;
	private Long 	idusuario;
}