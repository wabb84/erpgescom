package com.produccion.gescom.salud.dto;


import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class HistoriaDtoR implements Serializable{
	
	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long 	id;
	private Long 	idpersona;
	private LocalDate 	histfecingr;
	private Long 	tutor;
	private Long 	idpersprof;
	private Long 	idsocieda;
	private Long 	idtippacie;
	private Long 	idtiphisto;
	private Long	idusuario;
	private String 	serie;
	private String 	numeroserie;
	
}