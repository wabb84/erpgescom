package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.PersonaDto;
import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService{
	
	@Autowired
	private PersonaRepository personarep;
	
	@Override
	public Persona save( Persona persona ){
		return personarep.save( persona );
	}
	
	@Override
	public List<PersonaDto> ListaPersona( String buscarpor, String buscartext  ){
		return personarep.ListaPersona(buscarpor, buscartext);
		
	}
	
	@Override
	public Persona edit( Long idpersona ) {
		return personarep.findById(idpersona).orElse(null);
		
	}
	
}