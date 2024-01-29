package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.dto.TipoBusquedaDto;
import com.produccion.gescom.entity.TipoBusqueda;

@Repository
public interface TipoBusquedaRepository extends JpaRepository<TipoBusqueda, Long>  {
	@Transactional(readOnly=true)
	@Query(value = "select descripcion, codigobus from tipobusqueda where tipo = :tipo and vigencia = 'A' order by orden" ,nativeQuery = true)
    public List<TipoBusquedaDto> gettipobusquedae(String tipo);
}
