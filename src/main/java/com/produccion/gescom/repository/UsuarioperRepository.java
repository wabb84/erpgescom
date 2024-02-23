package com.produccion.gescom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.produccion.gescom.entity.Usuarioper;

@Repository
public interface UsuarioperRepository extends JpaRepository<Usuarioper, Long>{
	public Usuarioper findByIdusuario(Long idusuario); 
}
