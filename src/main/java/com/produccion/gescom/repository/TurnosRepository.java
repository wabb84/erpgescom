package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.dto.TurnosDto;
import com.produccion.gescom.entity.Turnos;

@Repository
public interface TurnosRepository extends JpaRepository<Turnos, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select idturnos, descripcion, horainicio, horafin, intervalo, vigencia from turnos\r\n"
			+ "   where idturnos = :idturnos" ,nativeQuery = true)
		
	public TurnosDto FindByTurnos(@Param("idturnos") Long idturnos);
	
	@Transactional(readOnly=true)
	@Query(value = "select idturnos, descripcion, horainicio, horafin, intervalo, vigencia from \r\n"
			+ " turnos where idsocieda = :idsocieda",nativeQuery = true)
	
	public List<TurnosDto> TurnosLista(@Param("idsocieda") Long idsocieda);
	
	@Transactional(readOnly=true)
	@Query(value = "select idturnos, descripcion, horainicio, horafin from \r\n"
			+ " turnos where idsocieda = :idsocieda and vigencia = 'A'",nativeQuery = true)
	
	public List<TurnosDto> TurnosListaCita(@Param("idsocieda") Long idsocieda);

}
