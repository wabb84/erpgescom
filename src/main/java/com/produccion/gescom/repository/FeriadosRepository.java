package com.produccion.gescom.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produccion.gescom.dto.FeriadosDto;
import com.produccion.gescom.entity.Feriados;

@Repository
public interface FeriadosRepository extends JpaRepository<Feriados, Long> {
	public List<FeriadosDto> findByIdsociedaOrderByFechaferia(Long idsocieda);
	public FeriadosDto findByIdsociedaAndFechaferia(Long idsocieda, LocalDate fechaferia);
	public void deleteById(Long idferiado);
}
