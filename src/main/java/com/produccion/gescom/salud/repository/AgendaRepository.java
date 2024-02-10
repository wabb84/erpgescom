package com.produccion.gescom.salud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.salud.dto.AgendaDto;
import com.produccion.gescom.salud.dto.AgendaObtDto;
//import com.produccion.gescom.salud.dto.AgendaResDtoR;
import com.produccion.gescom.salud.entity.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{
	
	@Transactional
	@Query(value = "SELECT f_generar_agenda(:mes, :idpersprof, :dia, :idturnos, :idsocieda ) as resultado", nativeQuery = true )
    public Integer generarAgenda( @Param("mes") String mes, @Param("idpersprof") Long idpersprof,
    					   		  @Param("dia") Long dia, @Param("idturnos") Long idturnos, 
    					   		  @Param("idsocieda") Long idsocieda );

		
	@Transactional(readOnly=true)
	@Query(value = "Select idagenda, idturnos, idsocieda, idconsultorio, \n"
				 + "idpersprof, anio,   mes, dia,  hora, \n"
				 + "observacion, fechaagenda, estado \n"
				 + "From agenda \n"
			     + "Where idagenda = :idagenda" ,nativeQuery = true)
	
	public AgendaDto FindByAgenda( @Param("idagenda") Long idagenda );
	
	@Transactional(readOnly=true)
	@Query(value = "Select * From obtener_agenda( :idsocieda, :idpersprof, :idturnos, :anio, :mes ) as ( \n"
				 + "idagenda 		bigint, \n"
				 + "idturnos 		bigint, \n"
				 + "descripcion 	character varying(500), \n"
				 + "idsocieda 		bigint, \n"
				 + "idconsultorio 	bigint, \n"
				 + "idpersprof		bigint, \n"
				 + "persprof		character varying(1000), \n"
				 + "anio			character varying(4), \n"
				 + "mes				character varying(10), \n"
				 + "dia				character varying(10), \n"
				 + "hora			character varying(10), \n"
				 + "observacion		character varying(500), \n"
				 + "fechaagenda		date, \n"
				 + "estado			character varying(1) )" ,nativeQuery = true)
	
	public List<AgendaObtDto> ListaAngenda( @Param("idsocieda") Long idsocieda,  @Param("idpersprof") Long idpersprof,
										 	@Param("idturnos") Long idturnos, @Param("anio") String anio,
										 	@Param("mes") String mes );
	
	
}