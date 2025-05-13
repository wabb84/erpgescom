package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.produccion.gescom.entity.CatalogoTipo;


@Repository
public interface CatalogoTipoRepository extends JpaRepository<CatalogoTipo, Long>  {
	public List<CatalogoTipo> findByMantenimiento(boolean mantenimiento); 
}
