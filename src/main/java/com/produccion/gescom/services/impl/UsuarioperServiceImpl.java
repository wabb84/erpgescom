package com.produccion.gescom.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.entity.Usuarioper;
import com.produccion.gescom.repository.UsuarioperRepository;
import com.produccion.gescom.services.UsuarioperService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioperServiceImpl implements UsuarioperService {
	@Autowired
    private UsuarioperRepository usuarioperrep;
	
	@Override
	public Usuarioper save(Usuarioper usuarioper) {
		return usuarioperrep.save(usuarioper);
	}	
	
	@Override
	public Usuarioper BuscaUsuarioPerfil(Long idusuario) {
		return usuarioperrep.findByIdusuario(idusuario);
	};
}
