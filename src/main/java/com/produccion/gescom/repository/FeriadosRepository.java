package com.produccion.gescom.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.dto.FeriadosDto;
import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.entity.Feriados;

@Repository
public interface FeriadosRepository extends JpaRepository<Feriados, Long> {
	//public List<FeriadosDto> findByIdsociedaOrderByFechaferia(Long idsocieda);
	
	@Transactional(readOnly=true)
	@Query(value = "select idferiado, fechaferia, descripcion from feriados where cast((extract(year from fechaferia)) as varchar) = :anio and idsocieda = :idsocieda order by fechaferia",nativeQuery = true)
	
	public List<FeriadosDto> findByIdsociedaOrderByFechaferia(String anio, Long idsocieda);
	
	
	public FeriadosDto findByIdsociedaAndFechaferia(Long idsocieda, LocalDate fechaferia);
	public void deleteById(Long idferiado);
}
