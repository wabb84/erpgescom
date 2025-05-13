package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.SociedaDto;
import com.produccion.gescom.dto.SociedaListaDto;
import com.produccion.gescom.entity.Socieda;

@Repository
public interface SociedaRepository extends JpaRepository<Socieda, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select dessocieda as sociedanombre, tipopersona,idtipodoc,numerodoc as numerodocumento,idrubro,idpais,nombrecome as nombrecomercial,serie,estadosocieda\r\n"
			+ "   from general.socieda\r\n"
			+ "   where idsocieda = :idsocieda" ,nativeQuery = true)
	
	public SociedaDto FindBySocieda(@Param("idsocieda") Long idsocieda);
	
	@Transactional(readOnly=true)
	@Query(value = "select idsocieda,dessocieda,serie,estadosocieda from (\r\n"
			+ "select idsocieda,dessocieda,serie,estadosocieda,'0' as tipo from general.socieda where idsocieda = 1\r\n"
			+ " union all \r\n"
			+ " select idsocieda,dessocieda,serie,estadosocieda,'1' as tipo from general.socieda where idsocieda <> 1) \r\n"
			+ " as uno order by tipo,dessocieda",nativeQuery = true)
	
	public List<SociedaListaDto> sociedaLista();
	
}