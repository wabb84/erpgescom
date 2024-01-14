package com.produccion.gescom.services;

import com.produccion.gescom.dto.UsuarioDatosLoginDto;

public interface UsuarioService {
	public UsuarioDatosLoginDto FindByDatosLogin(Long idusuario);
}
