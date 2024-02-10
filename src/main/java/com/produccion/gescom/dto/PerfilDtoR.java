package com.produccion.gescom.dto;

import java.io.Serializable;
//import java.sql.Date;
import java.util.List;
import lombok.Getter;

@Getter
public class PerfilDtoR implements Serializable {
	
	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long idperfil;
	private String desperfil;
	private String vigencia;
	private Long idsocieda;
	private Long idusuario;
	
	private List<MenuListaDtoR> detalleperfil;
}
