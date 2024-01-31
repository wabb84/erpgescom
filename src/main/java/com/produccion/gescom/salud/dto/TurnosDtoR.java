package com.produccion.gescom.salud.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class TurnosDtoR implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long idturnos;
	private Long idsocieda;
	private String vigencia;
	private String descripcion;
	private String horainicio;
	private String horafin;
	private Long intervalo;
	private Long idusuario;
	private String colorback;
	private String abrevia;
}
