package com.produccion.gescom.services;

import java.util.List;

import com.produccion.gescom.dto.MenulistaDto;

public interface MenuService {
	public List<MenulistaDto> menulista(Long idusuario);
}
