package com.produccion.gescom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.produccion.gescom.entity.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
	public Documento findByIdsociedaAndSerieAndAnio(Long didsocieda, String dserie, String danio);
}
