package com.produccion.gescom.salud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.salud.dto.PersprofDto;
import com.produccion.gescom.salud.dto.PersprofdatosDto;
import com.produccion.gescom.salud.entity.Persprof;
import com.produccion.gescom.salud.repository.PersprofRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersprofServiceImpl implements PersprofService{
	
	@Autowired
	private PersprofRepository persprofrep;
	
	@Override
	public Persprof save( Persprof persprof ){
		return persprofrep.save( persprof );
	}

	@Override
	public PersprofDto consulta(Long idpersprof) {
		
		return persprofrep.findByPersprof( idpersprof );
	}

	@Override
	public List<PersprofdatosDto> ListaPersprof( Long idsocieda ) {

		return persprofrep.ListaPersprof( idsocieda );

	}

	@Override
	public Persprof edit(Long idpersprof) {
		
		return persprofrep.findById( idpersprof ).orElse( null );
	}

}