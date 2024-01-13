package com.produccion.gescom.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.entity.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select id,nombretipodoc from TipoDocumento order by nombretipodoc" ,nativeQuery = true)
	public List<TipoDocumento> ListaTipoDocumento();
}
