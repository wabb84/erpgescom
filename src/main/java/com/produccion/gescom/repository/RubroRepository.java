package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.entity.Rubro;

@Repository
public interface RubroRepository extends JpaRepository<Rubro, Long> {
	
	@Transactional(readOnly=true)
	@Query(value = "select id,nombrerubro from Rubro where idrubro <> '1' order by nombrerubro" ,nativeQuery = true)
	public List<Rubro> ListaRubro();
}
