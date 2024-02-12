package com.produccion.gescom.salud.dto;

import java.io.Serializable;

import lombok.Getter;
@Getter
public class EspecialidaDtoR implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long idespecialida;
	private Long idsocieda;
	private String vigencia;
	private String descripcion;
	private Long idusuario;
	private String abrevia;
}
