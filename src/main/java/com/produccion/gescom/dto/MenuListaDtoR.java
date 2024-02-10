package com.produccion.gescom.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class MenuListaDtoR implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long idmenu;
	private String desmenu;
	private String tiponivel;
	private String icon;
	private Long idpadre;
	private String acceso;
	private Long idmenurubro;
	private Long idperfildet;
}
