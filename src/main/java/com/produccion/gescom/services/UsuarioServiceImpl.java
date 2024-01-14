package com.produccion.gescom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
    private UserLoginRepository userRepository;
	
	@Override
	public UsuarioDatosLoginDto FindByDatosLogin(String codusuario) {
   		return userRepository.FindByDatosLogin(codusuario);
   	}	
}
