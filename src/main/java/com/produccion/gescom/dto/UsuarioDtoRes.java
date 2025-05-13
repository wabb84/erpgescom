package com.produccion.gescom.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDtoRes {
	private Long iduser;
	private String codusuario;
	private String desusuario;
	private String email;
	private String telefono;
	private Long idsocieda;
	private Long idtipodoc;
	private String numerodoc;
	private String estadousuario;
	private LocalDate fechaini;
	private LocalDate fechafin;
	private String serie;
	private String estadopas;
	private String sexo;
	private Long idperfil;
}