package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>  {
	@Transactional(readOnly=true)
	@Query(value = "select e.idmenu,e.desmenu,e.tiponivel,e.idpadre\r\n"
			+ "   from usuarioper a\r\n"
			+ "   inner join perfil b on b.idperfil = a.idperfil\r\n"
			+ "   inner join perfildet c on c.idperfil = b.idperfil\r\n"
			+ "   inner join menurubro d on d.idmenurubro = c.idmenurubro\r\n"
			+ "   inner join menu e on e.idmenu = d.idmenu\r\n"
			+ "   where a.idusuario = :idusuario order by e.codmenu,e.orden",nativeQuery = true)
	
	public List<MenulistaDto> menuLista(@Param("idusuario") Long idusuario);
}
