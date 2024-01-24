package com.produccion.gescom.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class CitaDtoR implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long idagenda;
	private Long idhistoria;
	private Long idsocieda;
	private Long idusuario;
	
	/*private Long idconsultorio;
	private Long idsocieda;
	private String vigencia;
	private String descripcion;
	private Long idusuario;*/

}
