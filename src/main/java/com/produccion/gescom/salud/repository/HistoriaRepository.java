package com.produccion.gescom.salud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.salud.dto.HistoriaDto;
import com.produccion.gescom.salud.entity.Historia;


@Repository
public interface HistoriaRepository extends JpaRepository<Historia, Long>  {

	@Transactional(readOnly=true)	
	@Query(value = "Select idhistoria, idpersona, histfecingr, idpersprof, idsocieda, idtippacie, idtiphisto, serie, numeroserie, anio, mes, \n"
			+ "  a.alergias, a.idtiposangre, a.observacion \n"
			+ "  From historia a \n"
			+ "  Where a.idhistoria = :idhistoria" ,nativeQuery = true)
	
	public HistoriaDto findByHistoria( @Param("idhistoria") Long idhistoria );
	
	@Transactional(readOnly=true)
	@Query(value = "Select a.idhistoria, a.idpersona, a.histfecingr, a.idpersprof, a.idsocieda, a.idtippacie,\n"
			+ " a.idtiphisto, a.serie, a.numeroserie, a.anio, a.mes, a.alergias, a.idtiposangre, a.observacion \n"
			+ " From historia a \n"
			+ " Where a.idpersona = :idpersona" ,nativeQuery = true)
	
	public HistoriaDto findByHisPers( @Param("idpersona") Long idpersona );
	

}
