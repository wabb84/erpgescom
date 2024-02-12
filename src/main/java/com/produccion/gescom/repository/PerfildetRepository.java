package com.produccion.gescom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produccion.gescom.entity.Perfildet;

public interface PerfildetRepository extends JpaRepository<Perfildet, Long> {
	//public Perfildet findByIdperfilAndIdmenurubro(Long idperfil, Long idmenurubro);

}
