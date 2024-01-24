package com.produccion.gescom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.produccion.gescom.entity.Seriexdoc;

@Repository
public interface SeriexdocRepository extends JpaRepository<Seriexdoc, Long> {
	public Seriexdoc findByIdsociedaAndIddocumentoAndAnioAndMes(Long dsocieda, Long ddocumento, String danio, String dmes);
}
