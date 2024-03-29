package com.produccion.gescom.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class PersonaDtoR implements Serializable {
	
	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long 	id;
	private Long 	tipoDocumento;
	private Long 	idsocieda;
	private Long 	idpais;
	private String 	tipopersona;
	private String 	numerodocumento;
	private String 	apellidopaterno; 
	private String 	apellidomaterno;
	private String 	primernombre;
	private String 	segundonombre;
	private String	nombrelargo;
	private String	razonsocial;
	private String	nomabreviado;
	private LocalDate fecnacimiento;
	private String	sexo;
	private Long 	idusuario;
	private String  vigencia;
    private String 	buscarPor;
    private String 	buscarText;
    private Long 	tutor;
    private String 	estadocivil;
    private String 	lugarnacimi;
    private String 	telefono;
    private String 	email;
    private String 	domicilio;
    private String 	codubigeo;
    private String 	observacion;
    private String 	tipoper;
    private String 	nrocolegio;
    private String 	rne;
    private Long 	idpersprof;
}
