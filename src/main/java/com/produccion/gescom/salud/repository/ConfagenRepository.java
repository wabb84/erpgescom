package com.produccion.gescom.salud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.salud.dto.ConfAgenDto;
import com.produccion.gescom.salud.entity.Confagen;

public interface ConfagenRepository extends JpaRepository<Confagen, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select case when a.tipo = 'I' then 'Individual' else 'Traslado' end as tipo,b.descripcion as turno, \n"
			+ "c.abrevia as especialida,a.anio,a.mes, e.nomlargo as medico,  \n"
			+ "   coalesce(a.aniode||'-'||a.mesde,'') as desde,coalesce(a.anohas||'-'||a.meshas,'') as hasta \n"
		   + "from confagen a \n"
		   + "left join turnos b on b.idturnos = a.idturno \n" 
		   + "left join especialida c on c.idespecial = a.idespecial \n"
		   + "left join persprof d on d.idpersprof = a.idpersprof \n"
		   + "left join persona e on e.idpersona = d.idpersona \n"
		   + "where a.anio = :anio ",nativeQuery = true)
	
	public List<ConfAgenDto> ListadoConfAgens(String anio);
}
