package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.ValidacionesDto;
import com.produccion.gescom.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>  {
	List<Menu> findByIdpadreIsNull();
	
	@Transactional(readOnly=true)
	@Query(value = "select e.idmenu,e.desmenu,e.tiponivel,coalesce(e.idpadre,0) as idpadre, e.icon, '0' as acceso\r\n"
			+ "   from seguridad.usuarioper a\r\n"
			+ "   inner join seguridad.perfil b on b.idperfil = a.idperfil\r\n"
			+ "   inner join seguridad.perfildet c on c.idperfil = b.idperfil\r\n"
			+ "   inner join seguridad.menurubro d on d.idmenurubro = c.idmenurubro\r\n"
			+ "   inner join seguridad.menu e on e.idmenu = d.idmenu\r\n"
			+ "   where coalesce(e.estadomenu,'') = 'A' and c.acceso = '1' and a.idusuario = :idusuario order by e.codmenu,e.orden",nativeQuery = true)
	
	public List<MenulistaDto> menuLista(@Param("idusuario") Long idusuario);
	
	/*@Transactional(readOnly=true)
	@Query(value = "select a.idmenu, a.desmenu, a.idpadre,a.tiponivel,a.orden,a.codmenu,a.estadomenu, a.icon\r\n"
			+ "	   from seguridad.menu a\r\n"
			+ "	   inner join seguridad.perfildet b on b.idmenu = a.idmenu\r\n"
			+ "	   inner join seguridad.usuarioper c on c.idperfil = b.idperfil\r\n"
			+ "	   where coalesce(a.estadomenu,'') = 'A' and a.idpadre is null and c.idusuario = :idusuario order by codmenu,orden",nativeQuery = true)
	public List<Menu> menuListaTemporal(@Param("idusuario") Long idusuario);

	@Transactional(readOnly=true)
	@Query(value = "select count(*) as cantidad\r\n"
			+ "   from seguridad.usuarioper a\r\n"
			+ "   inner join seguridad.perfildet b on b.idperfil = a.idperfil\r\n"
			+ "   where a.idusuario = :idusuario and b.idmenu = :idmenu",nativeQuery = true)
	public ValidacionesDto validaMenu(@Param("idusuario")Long idusuario,@Param("idmenu") Long idmenu);*/

	
}
