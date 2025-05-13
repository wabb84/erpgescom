package com.produccion.gescom.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogoTipoDto implements Serializable {
	private static final long serialVersionUID = 5924468583005150707L;
	
	private Long idcattip;
    private String descripcion;
}
