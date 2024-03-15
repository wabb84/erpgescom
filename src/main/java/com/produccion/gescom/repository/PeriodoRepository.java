package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.dto.PeriodoDto;
import com.produccion.gescom.entity.Periodo;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
	
	@Transactional(readOnly=true)
	@Query(value = "select distinct anio from periodo order by anio", nativeQuery = true )
	
	public List<PeriodoDto> ListaAnios();
	
	@Transactional(readOnly=true)
	@Query(value = "select distinct mes as mesc,descrmes as mesd from periodo where anio = :anio order by mes", nativeQuery = true )
	
	public List<PeriodoDto> ListaMeses(String anio);
	
	public Periodo findByAnioAndMes(String anio, String mes);	
}
