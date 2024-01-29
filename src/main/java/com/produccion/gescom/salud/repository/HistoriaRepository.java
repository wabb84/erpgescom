package com.produccion.gescom.salud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produccion.gescom.salud.entity.Historia;


@Repository
public interface HistoriaRepository extends JpaRepository<Historia, Long>  {

	

}
