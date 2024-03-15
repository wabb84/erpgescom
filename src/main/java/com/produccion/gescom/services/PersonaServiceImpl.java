package com.produccion.gescom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produccion.gescom.dto.PersonaDto;
import com.produccion.gescom.dto.PersonaMultipleDto;
import com.produccion.gescom.dto.PersonamDto;
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
	public PersonaDto consulta(Long idpersona) {
		return personarep.FindByPersona( idpersona );	
	}
	
	@Override
	public PersonamDto consultam(Long idpersona) {
		return personarep.FindByPersonam( idpersona );	
	}
	
	@Override
	public List<PersonaDto> ListaPersona( String buscarpor, String buscartext  ){
		return personarep.ListaPersona(buscarpor, buscartext);
		
	}
	
	@Override
	public List<PersonaMultipleDto> ListaPersonaMultiple( String buscartext, String buscaadi, Long vidsocieda  ){
		return personarep.ListaPersonamultiple(buscartext, buscaadi, vidsocieda);
	}
	
	@Override
	public Persona edit( Long idpersona ) {
		return personarep.findById(idpersona).orElse(null);
	}
	
	@Override
	public PersonaDto consultanuevoxdoc(Long idtipodoc, String nrodoc, Long idsocieda) {
		return personarep.FindByPersonaNuevaxDoc( idtipodoc, nrodoc, idsocieda );	
	}

	@Override
	public PersonaDto consultaeditaxdoc(Long idtipodoc, String nrodoc, Long idsocieda, Long idpersona) {
		return personarep.FindByPersonaEditaxDoc( idtipodoc, nrodoc, idsocieda, idpersona );	
	}
}