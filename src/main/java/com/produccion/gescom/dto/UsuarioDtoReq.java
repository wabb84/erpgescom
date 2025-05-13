package com.produccion.gescom.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDtoReq  implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private Long iduser;
	private String codusuario;
	private String desusuario;
	private String email;
	private String telefono;
	private String password;
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
