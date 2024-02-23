package com.produccion.gescom.dto;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PerfilListasDto {
	private Long idperfil;
	private String desperfil;
	private String vigencia;
	
	private List<MenulistaDto> detalles;
	
	public Long getIdperfil() {
		return idperfil;
	}

	public void setIdperfil(Long idperfil) {
		this.idperfil = idperfil;
	}

	public String getDesperfil() {
		return desperfil;
	}

	public void setDesperfil(String desperfil) {
		this.desperfil = desperfil;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public List<MenulistaDto> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<MenulistaDto> detalles) {
		this.detalles = detalles;
	}

	
	
	/*public List<MenulistaDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<MenulistaDto> detalles) {
        this.detalles = detalles;
    }*/
}
