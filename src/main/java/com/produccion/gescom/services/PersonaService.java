package com.produccion.gescom.services;

import java.util.List;

import com.produccion.gescom.dto.PersonaDto;
import com.produccion.gescom.dto.PersonaMultipleDto;
import com.produccion.gescom.entity.Persona;

public interface PersonaService {
	
	public Persona save( Persona persona );
	
	public PersonaDto consulta(Long idpersona);
	
	public List<PersonaDto> ListaPersona( String buscarpor, String buscartext  );
	
	public Persona edit( Long idpersona );
	
	public List<PersonaMultipleDto> ListaPersonaMultiple( String buscartext, String buscaadi, Long vidsocieda  );
	
}