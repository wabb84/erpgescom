package com.produccion.gescom.salud.dto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;


@Getter
public class CitasListadoDtoR implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private String fechai;
	private String fechaf;
	private Long idturno;
	private Long idpaciente;
	private Long idmedico;
	private String estado;
}
