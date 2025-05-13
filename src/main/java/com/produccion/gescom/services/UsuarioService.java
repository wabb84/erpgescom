package com.produccion.gescom.services;

import java.util.List;
import com.produccion.gescom.dto.ReinicioPasswordDtoR;
import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.dto.UsuarioDtoReq;
import com.produccion.gescom.dto.UsuarioDtoRes;
import com.produccion.gescom.dto.UsuarioEditaDto;
import com.produccion.gescom.dto.UsuarioListaDto;
//import com.produccion.gescom.entity.UserEntity;

public interface UsuarioService {
	public UsuarioDatosLoginDto findByDatosLogin(String codusuario);
	public UsuarioDtoRes nuevo(UsuarioDtoReq userDtoR);
	public UsuarioDtoRes edita(UsuarioDtoReq userDtoR);
	public String reinicioPassword(ReinicioPasswordDtoR reiniciodtor);
	public List<UsuarioListaDto> listaUsuarios(Long idsocieda);
	public UsuarioEditaDto consulta(Long idusuario);
}
