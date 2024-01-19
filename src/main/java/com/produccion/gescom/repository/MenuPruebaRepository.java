package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.dto.MenuPruebaDto;
import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.entity.MenuPrueba;

@Repository
public interface MenuPruebaRepository extends JpaRepository<MenuPrueba, Long>  {
	List<MenuPrueba> findByParentidIsNull();
	
	@Transactional(readOnly=true)
	@Query(value = "select id,name,url,parentid from menuPrueba where parentid is null",nativeQuery = true)

	public List<MenuPrueba> menuListaFinal();
	
	
	//@Transactional(readOnly=true)
	//@Query("select id,name,url,parentid,subItems from MenuPrueba where parentid is null")
	//public List<MenuPrueba> menuListaFinal();


	
	//List<MenuPrueba> findByNameIsNull();
	
	/*@Transactional(readOnly=true)
	@Query(value = "select id,name,url,parentid from MenuPrueba where id = :idm",nativeQuery = true)

	public List<MenuPrueba> menuLista(@Param("idm") Long idm);*/
	
	
	//@Transactional(readOnly=true)
	//@Query("select id,name,url,parentid from MenuPrueba where parentid is null")
	//public List<MenuPrueba> menuLista();

	//@Query("select id,name,url,parentid from Menuprueba where id = :idm")
	//public List<MenuPruebaDto> menuLista2(@Param("idm") Long idm);
	
	//@Transactional(readOnly=true)
	/*@Query(value = "select e.idmenu,e.desmenu,e.tiponivel,e.idpadre\r\n"
			+ "   from usuarioper a\r\n"
			+ "   inner join perfil b on b.idperfil = a.idperfil\r\n"
			+ "   inner join perfildet c on c.idperfil = b.idperfil\r\n"
			+ "   inner join menurubro d on d.idmenurubro = c.idmenurubro\r\n"
			+ "   inner join MenuFinal e on e.idmenu = d.idmenu\r\n"
			+ "   where e.parentid is null and a.idusuario = :idusuario order by e.codmenu,e.orden")
	
	public List<MenulistaDto> menuListaFinal(@Param("idusuario") Long idusuario);*/

}
