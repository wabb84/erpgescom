package com.produccion.gescom.salud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.salud.dto.AgendaDto;
import com.produccion.gescom.salud.dto.AgendaObtDto;
import com.produccion.gescom.salud.dto.AgendamesanioDto;
//import com.produccion.gescom.salud.dto.AgendaResDtoR;
import com.produccion.gescom.salud.entity.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{
	
	@Transactional
	@Query(value = "SELECT f_generar_agenda(:tipo, :medico, :especial, :turno, :anio, :mes, :dia, :idsocieda, :idconfagen, :idusuario) as resultado", nativeQuery = true )
	public Integer generarAgendaI(String tipo, Long medico, Long especial, Long turno, String anio, String mes,Long dia,Long idsocieda,Long idconfagen, Long idusuario);
	
	@Transactional
		@Query(value = "SELECT f_copiar_agenda_mes(:anioi, :mesi, :aniof, :mesf, :idsocieda, :idusuario, :idconfagen) as resultado", nativeQuery = true )
	public Integer generarAgendaM(String anioi, String mesi, String aniof, String mesf, Long idsocieda, Long idusuario, Long idconfagen);

	//@Transactional
	//@Query(value = "SELECT f_generar_agenda(:tipo, :medico, :especial, :turno, :anio, :mes, :dia, :idsocieda, :idconfagen, :idusuario) as resultado", nativeQuery = true )
	//public Integer generarAgendaM(String anioi, String mesi, String aniof, String mesf, Long idsocieda,Long idconfagen, Long idusuario);

	
	/*@Transactional
	@Query(value = "SELECT f_generar_agenda(:mes, :idpersprof, :dia, :idturnos, :idsocieda ) as resultado", nativeQuery = true )
    public Integer generarAgenda( @Param("mes") String mes, @Param("idpersprof") Long idpersprof,
    					   		  @Param("dia") Long dia, @Param("idturnos") Long idturnos, 
    					   		  @Param("idsocieda") Long idsocieda );*/

		
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

	@Transactional(readOnly=true)
	@Query(value = "select a.idagenda,b.descripcion as nombreturno, b.abrevia as abreviaturno ,b.colorback,e.descripcion as nomespecial, e.abrevia as abrespecial, \n"
			+ "       a.idpersprof, d.nomlargo as nommedico, a.anio, a.mes, a.dia, a.hora, a.estado, to_char(a.fechaagenda,'dd/mm/yyyy') as fechaagenda, a.intervalo, \n"
			+ "   f.idcita, f.idpersona, g.nomlargo as persona, case when coalesce(f.estadocita,'') = '' then '' when coalesce(f.estadocita,'') = 'C' then 'Cita' else 'Cita Adicional' end tipo  \n"
			+ "   from agenda a \n"
			+ "   left join turnos b on b.idturnos = a.idturnos \n"
			+ "   left join persprof c on c.idpersprof = a.idpersprof \n"
			+ "   left join persona d on d.idpersona = c.idpersona \n"
			+ "   left join especialida e on e.idespecial = a.idespecialida \n"
			+ "   left join cita f on f.idagenda = a.idagenda and f.idestadocita in (1,2,4) \n"
			+ "   left join persona g on g.idpersona = f.idpersona \n"
			+ "   where a.anio = :anio and a.mes = :mes and a.estado in ('P','C') and a.idsocieda = :idsocieda \n"
			+ "   order by a.dia, a.hora" ,nativeQuery = true)
	
	public List<AgendamesanioDto> ListaAngendaaniomes(Long idsocieda, String anio,String mes );
	
	
	
	
}