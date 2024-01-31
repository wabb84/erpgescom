package com.produccion.gescom.salud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.salud.dto.TurnosDto;
import com.produccion.gescom.salud.entity.Turnos;

@Repository
public interface TurnosRepository extends JpaRepository<Turnos, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select idturnos, descripcion, horainicio, horafin, intervalo, vigencia, colorback, abrevia from turnos\r\n"
			+ "   where idturnos = :idturnos" ,nativeQuery = true)
		
	public TurnosDto FindByTurnos(@Param("idturnos") Long idturnos);
	
	@Transactional(readOnly=true)
	@Query(value = "select idturnos, descripcion, horainicio, horafin, intervalo, vigencia, colorback, abrevia from \r\n"
			+ " turnos where idsocieda = :idsocieda",nativeQuery = true)
	
	public List<TurnosDto> TurnosLista(@Param("idsocieda") Long idsocieda);
	
	@Transactional(readOnly=true)
	@Query(value = "select idturnos, descripcion, horainicio, horafin, colorback, abrevia from \r\n"
			+ " turnos where idsocieda = :idsocieda and vigencia = 'A'",nativeQuery = true)
	
	public List<TurnosDto> TurnosListaCita(@Param("idsocieda") Long idsocieda);

}
