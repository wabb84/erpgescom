package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.entity.Persona;
import com.produccion.gescom.dto.PersonaDto;
import com.produccion.gescom.dto.PersonaMultipleDto;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	@Transactional(readOnly=true)
	@Query(value = "Select idpersona, idtipodoc as tipoDocumento, idsocieda, idpais, tipopersona, nrodoc as numerodocumento, appaterno as apellidopaterno, appmaterno as apellidomaterno, primernom as primernombre, segundonom as segundonombre, nomlargo as nombrelargo, razonsocial, nomabrev as nomabreviado, fecnacimi as fecnacimiento, sexo, vigencia, \n"
			    + "   estadocivil, lugarnacimi, telefono, email, domicilio, codubigeo, tutor, observacion \n"  
				+ "   from persona \n"
				+ "   where idpersona = :idpersona" ,nativeQuery = true)
	
	public PersonaDto FindByPersona( @Param("idpersona") Long idpersona ); 
	
	@Transactional(readOnly=true)
	@Query(value = "Select idpersona, tipoDocumento, idsocieda, idpais, tipopersona, nrodoc as numerodocumento, appaterno as apellidopaterno, appmaterno as apellidomaterno, primernom as primernombre, segundonom as segundonombre, nomlargo as nombrelargo, razonsocial, nomabrev as nomabreviado, fecnacimi as fecnacimiento, sexo, vigencia \n"
				 	+ "from datos_persona(	:buscarpor, :buscartext ) \n"
				 	+ "as \n"
				 	+ "( idpersona bigint, tipoDocumento bigint, idsocieda bigint, idpais bigint, \n"
				 	+ "	 tipopersona character varying(1), nrodoc character varying(50), appaterno character varying(250), \n"
				 	+ "	 appmaterno character varying(250), primernom character varying(250), segundonom character varying(250), \n"
				 	+ "	 nomlargo character varying(1000), razonsocial character varying(1000), nomabrev character varying(250), \n"
				 	+ "	 fecnacimi date, sexo character varying(1), vigencia character varying(1) )", nativeQuery = true )
	
	public List<PersonaDto> ListaPersona( String buscarpor, String buscartext  );
	
	@Transactional(readOnly=true)
	@Query(value = " Select idpersona, tipoDocumento, idsocieda, idpais, tipopersona, numerodocumento, apellidopaterno, apellidomaterno,\n"
			        + " primernombre, segundonombre, nombrelargo, razonsocial, nomabreviado, fecnacimiento, sexo, vigencia, \n"
			        + " estadocivil,lugarnacimi,telefono,email,domicilio,codubigeo,tutor,observacion,numeroserie, numeroserie \n"
				 	+ " from busca_persona_v2( :buscartext, :buscaadi, :vidsocieda )\n"
				 	+ " as\n"
				 	+ " (idpersona bigint, tipoDocumento bigint, idsocieda bigint, idpais bigint, \n"
				 	+ " tipopersona character varying(1), numerodocumento character varying(50), apellidopaterno character varying(250), \n"
				 	+ "	apellidomaterno character varying(250), primernombre character varying(250), segundonombre character varying(250), \n"
				 	+ "	nombrelargo character varying(1000), razonsocial character varying(1000), nomabreviado character varying(250), \n"
				 	+ "	fecnacimiento date, sexo character varying(1), vigencia character varying(1), \n"
				 	+ " estadocivil varchar(1), lugarnacimi varchar(150), telefono varchar(150),email varchar(150),domicilio varchar(150),\r\n"
				 	+ " codubigeo varchar(6),tutor bigint, observacion varchar(1000), numeroserie character varying(15))", nativeQuery = true )
	public List<PersonaMultipleDto> ListaPersonamultiple( String buscartext, String buscaadi, Long vidsocieda );
	
	@Transactional(readOnly=true)
	@Query(value = "Select idpersona from persona where idtipodoc = :idtipodoc and nrodoc = :nrodoc and idsocieda = :idsocieda",nativeQuery = true)
	public PersonaDto FindByPersonaNuevaxDoc(Long idtipodoc, String nrodoc, Long idsocieda); 
	
	@Transactional(readOnly=true)
	@Query(value = "Select idpersona from persona where idtipodoc = :idtipodoc and nrodoc = :nrodoc and idsocieda = :idsocieda and idpersona <> :idpersona",nativeQuery = true)
	public PersonaDto FindByPersonaEditaxDoc(Long idtipodoc, String nrodoc, Long idsocieda, Long idpersona); 
	
}