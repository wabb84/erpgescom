package com.produccion.gescom.services;

import java.util.List;
import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.dto.UsuarioEditaDto;
import com.produccion.gescom.dto.UsuarioListaDto;
import com.produccion.gescom.entity.Socieda;
import com.produccion.gescom.entity.UserEntity;

public interface UsuarioService {
	public UsuarioDatosLoginDto FindByDatosLogin(String codusuario);
	public UserEntity save(UserEntity usuario);
	public List<UsuarioListaDto> ListaUsuarios(Long idsocieda);
	public UserEntity Datospruebauser(Long iduser);
	public UsuarioEditaDto consulta(Long idusuario);
	public UserEntity edita(Long idusuario);
	
}
