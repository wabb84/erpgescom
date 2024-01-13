package com.produccion.gescom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.dto.SociedaDto;
import com.produccion.gescom.entity.Socieda;

@Repository
public interface SociedaRepository  extends JpaRepository<Socieda, Long> {
	
	@Transactional(readOnly=true)
	@Query(value = "select dessocieda as sociedanombre, tipopersona,idtipodoc,numerodoc as numerodocumento,idrubro,idpais,nombrecome as nombrecomercial,serie,estadosocieda\r\n"
			+ "   from socieda\r\n"
			+ "   where idsocieda = :idsocieda" ,nativeQuery = true)
	
	public SociedaDto FindBySocieda(@Param("idsocieda") Long idsocieda);
}