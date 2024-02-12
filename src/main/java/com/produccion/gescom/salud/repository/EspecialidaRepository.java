package com.produccion.gescom.salud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produccion.gescom.salud.entity.Especialida;

@Repository
public interface EspecialidaRepository extends JpaRepository<Especialida, Long>{
	public List<Especialida> findByIdsocieda(Long idsocieda);
}
