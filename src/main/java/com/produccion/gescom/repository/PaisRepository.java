package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.entity.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select id,nombrepais from Pais order by nombrepais" ,nativeQuery = true)
	public List<Pais> ListaPais();
}
