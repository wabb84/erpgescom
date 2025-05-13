package com.produccion.gescom.services;

import java.util.List;

import com.produccion.gescom.dto.MenulistaDto;
//import com.produccion.gescom.dto.UsuarioDatosLoginDto;
//import com.produccion.gescom.dto.ValidacionesDto;
//import com.produccion.gescom.entity.Menu;

public interface MenuService {
	public List<MenulistaDto> menulista();
	
	/*public List<Menu> menulistafinal();
	
	public List<Menu> menulistatemporal(Long idusuario);
	
	public ValidacionesDto FindByMenu(Long idusuario, Long idmenu);*/
}
