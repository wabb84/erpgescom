package com.produccion.gescom.salud.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class PersprofDtoR implements Serializable{

	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long  	id;
	private Long  	idpersona;	
	private Long  	idprofesion;
	private String	nrocolegio;
	private String	rne;
	private Long	idusuario;
	private String	vigencia;
	private Long	idsocieda;
	
}