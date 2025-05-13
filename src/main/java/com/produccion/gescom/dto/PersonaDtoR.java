package com.produccion.gescom.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.produccion.gescom.advice.validation.anotation.ValidName;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
	//@NotBlank -- para validaciones no borrar
	private String 	apellidopaterno;
	//@NotBlank -- para validaciones no borrar
	private String 	apellidomaterno;
	//@NotBlank(message = "El Apellido Materno no debe estar Vacio")  -- para validaciones no borrar
	private String 	primernombre;
	//Toma como prioridad mostrar este mensaje , si no se envia aca mensaje toma el mensaje de default
	//@ValidName(message = "Mensaje Personalizado por cada campo")
	//@ValidName -- para validaciones no borrar
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
    @Email(message = "Correo electrónico no válido")
    private String 	email;
    private String 	domicilio;
    private String 	codubigeo;
    private String 	observacion;
    private String 	tipoper;
    private String 	nrocolegio;
    private String 	rne;
    private Long 	idpersprof;
    
    private String 	tipoparentutor;
    private Long 	idtipodoctutor;
    private String 	apellnombtutor;
	private String  numerodoctutor;
}
