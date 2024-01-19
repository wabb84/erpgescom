package com.produccion.gescom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.produccion.gescom.controller.UsuarioController;
import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.dto.UsuarioEditaDto;
import com.produccion.gescom.dto.UsuarioListaDto;
import com.produccion.gescom.entity.UserEntity;
import com.produccion.gescom.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	private static final Log logger = LogFactory.getLog(UsuarioService.class);
	
	@Autowired
    private UserLoginRepository userRepository;
	
	@Override
	public UserEntity save(UserEntity usuario) {
		return userRepository.save(usuario);
	}	
	
	@Override
	public UsuarioDatosLoginDto FindByDatosLogin(String codusuario) {
   		return userRepository.FindByDatosLogin(codusuario);
   	}	
	
	@Override
	public List<UsuarioListaDto> ListaUsuarios(Long idsocieda) {
		//logger.info("Service " + idsocieda);
		return userRepository.usuarioLista(idsocieda);
	}
	
	@Override
	public UserEntity Datospruebauser(Long iduser) {
		logger.info("wbarrantes2");
		return userRepository.findById(iduser).orElse(null);
	};
	
	@Override
	public UsuarioEditaDto consulta(Long idusuario) {
		return userRepository.EditaUsuario(idusuario);
	};
	
	public UserEntity edita(Long idusuario){
		return userRepository.findById(idusuario).orElse(null);
	};
}
