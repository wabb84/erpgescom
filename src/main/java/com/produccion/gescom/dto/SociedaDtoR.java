package com.produccion.gescom.dto;

import java.io.Serializable;

import com.produccion.gescom.entity.ETipoPersona;

import lombok.Getter;

@Getter
public class SociedaDtoR  implements Serializable{
	private static final long serialVersionUID = 5926468583005150707L;
	private Long id;
	private String sociedanombre;
    private Long idrubro;
    private String tipopersona;
    private Long idtipodoc;
    private String numerodocumento;
    private String nombrecomercial;
	private Long idpais;
	private String serie;
	private String estadosocieda;
	private Long idusuario;
}
