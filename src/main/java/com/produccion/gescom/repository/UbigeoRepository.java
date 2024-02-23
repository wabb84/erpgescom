package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.dto.DepartamentoDto;
import com.produccion.gescom.dto.DistritoDto;
import com.produccion.gescom.dto.ProvinciaDto;
import com.produccion.gescom.entity.Ubigeo;

public interface UbigeoRepository extends JpaRepository<Ubigeo, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select distinct iddepart, depart_des from ubigeo order by depart_des",nativeQuery = true)
	public List<DepartamentoDto> listadepartamentos();
	
	@Transactional(readOnly=true)
	@Query(value = "select distinct idprovin, provin_des from ubigeo where iddepart = :departamento \n"
			+ " order by provin_des",nativeQuery = true)
	public List<ProvinciaDto> listaprovincias(String departamento);
	
	@Transactional(readOnly=true)
	@Query(value = "select distinct codubigeo, distri_des from ubigeo where iddepart = :departamento \n"
			+ " and idprovin = :provincia order by distri_des",nativeQuery = true)
	public List<DistritoDto> listadistritos(String departamento, String provincia);
}
