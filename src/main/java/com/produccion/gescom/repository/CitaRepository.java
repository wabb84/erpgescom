package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.dto.ConsultorioDto;
import com.produccion.gescom.entity.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select idconsultorio, descripcion, vigencia from consultorio\r\n"
			+ "   where idconsultorio = :idconsultorio" ,nativeQuery = true)
	
	public ConsultorioDto FindByConsultorio(@Param("idconsultorio") Long idconsultorio);
	
	@Transactional(readOnly=true)
	@Query(value = "select idconsultorio, descripcion, vigencia from\r\n"
			+ " consultorio where idsocieda = :idsocieda order by descripcion",nativeQuery = true)
	
	public List<ConsultorioDto> ConsultorioLista(@Param("idsocieda") Long idsocieda);
}