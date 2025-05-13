package com.produccion.gescom.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.CatalogoTipoDto;
import com.produccion.gescom.mapper.CatalogoTipoMapper;
import com.produccion.gescom.repository.CatalogoTipoRepository;
import com.produccion.gescom.services.CatalogoTipoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogoTipoServiceImpl implements CatalogoTipoService {

	private final CatalogoTipoRepository catalogotiporepo;
    private final CatalogoTipoMapper catalogotipomapper;
	
    @Override
	public List<CatalogoTipoDto> listaCatalogo(boolean mantenimiento) {
		
    	return catalogotipomapper.toListaCatalogoTipo(catalogotiporepo.findByMantenimiento(true));
    			
	}
}
