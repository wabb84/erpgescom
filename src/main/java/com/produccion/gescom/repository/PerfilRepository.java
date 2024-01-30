package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.dto.UsuarioListaDto;
import com.produccion.gescom.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	@Transactional(readOnly=true)
	@Query(value = "select iduser as idusuario,codusuario,desusuario from usuario where idsocieda = :idsocieda",nativeQuery = true)
	public List<UsuarioListaDto> usuarioLista(@Param("idsocieda") Long idsocieda);
}
