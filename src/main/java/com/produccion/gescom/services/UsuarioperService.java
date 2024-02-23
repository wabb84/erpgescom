package com.produccion.gescom.services;

import com.produccion.gescom.entity.Usuarioper;

public interface UsuarioperService {
	public Usuarioper save(Usuarioper usuarioper);
	public Usuarioper BuscaUsuarioPerfil(Long idusuario);
}
