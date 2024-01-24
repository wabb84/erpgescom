package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.dto.PersonaDto;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	@Transactional(readOnly=true)
	@Query(value = "Select idtipperson, tipoDocumento, idsocieda, idpais, tipopersona,	nrodoc, appaterno, appmaterno, primernom, segundonom, nomlargo, razonsocial, nomabrev, fecnacimi, sexo \n"
				 	+ "from datos_persona(	:buscarpor, :buscartext ) \n"
				 	+ "as \n"
				 	+ "( idtipperson integer, tipoDocumento integer, idsocieda bigint, idpais bigint, \n"
				 	+ "	 tipopersona character varying(1), nrodoc character varying(50), appaterno character varying(250), \n"
				 	+ "	 appmaterno character varying(250), primernom character varying(250), segundonom character varying(250), \n"
				 	+ "	 nomlargo character varying(1000),	razonsocial character varying(1000), nomabrev character varying(250), \n"
				 	+ "	 fecnacimi date, sexo character(1))", nativeQuery = true )
	
	public List<PersonaDto> ListaPersona( String buscarpor, String buscartext  );	
	
}
