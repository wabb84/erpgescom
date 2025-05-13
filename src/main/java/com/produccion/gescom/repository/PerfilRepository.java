package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.PerfilListaDto;
import com.produccion.gescom.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	@Transactional(readOnly=true)
	@Query(value = "select idperfil,desperfil,vigencia from seguridad.perfil where idsocieda = :idsocieda",nativeQuery = true)
	public List<PerfilListaDto> perfilLista(Long idsocieda);
	
	@Transactional(readOnly=true)
	@Query(value = "select idmenu,desmenu,tiponivel,idpadre,icon,acceso, idmenurubro, idperfildet from ( \n"
			+ " select e.idmenu,e.desmenu,e.tiponivel,coalesce(e.idpadre,0) as idpadre, e.icon, c.acceso,e.codmenu,e.orden, d.idmenurubro, c.idperfildet \n"
			+ "   from seguridad.perfildet c \n"
			+ "   inner join seguridad.menurubro d on d.idmenurubro = c.idmenurubro \n"
			+ "   inner join seguridad.menu e on e.idmenu = d.idmenu \n"
			+ " where coalesce(e.estadomenu,'') = 'A' and c.idperfil = :idperfil \n"
			+ " union all\n"
			+ " select a.idmenu,d.desmenu,d.tiponivel,coalesce(d.idpadre,0) as idpadre, d.icon, '0' as acceso,d.codmenu,d.orden, a.idmenurubro, 0 as idperfildet \n" 
			+ "   from seguridad.menurubro a \n" 
			+ "   inner join rubro b on b.idrubro = a.idrubro \n"
			+ "   inner join general.socieda c on c.idrubro = b.idrubro \n"
			+ "   inner join seguridad.menu d on d.idmenu = a.idmenu \n"
			+ "   where c.idsocieda = :idsocieda and d.idmenu not in (select e.idmenu from seguridad.perfildet c \n"
			+ "										   inner join seguridad.menurubro d on d.idmenurubro = c.idmenurubro \n"
			+ "										   inner join seguridad.menu e on e.idmenu = d.idmenu \n"
			+ "										   where coalesce(e.estadomenu,'') = 'A' and c.idperfil = :idperfil)) as uno \n"
			+ "   order by codmenu,orden",nativeQuery = true)
	public List<MenulistaDto> perfildetalleLista(Long idperfil, Long idsocieda);
	
	
	@Transactional(readOnly=true)
	@Query(value = " select a.idmenu,d.desmenu,d.tiponivel,coalesce(d.idpadre,0) as idpadre, d.icon, '0' as acceso,d.codmenu,d.orden, a.idmenurubro, 0 as idperfildet \n" 
			+ "   from seguridad.menurubro a \n" 
			+ "   inner join rubro b on b.idrubro = a.idrubro \n"
			+ "   inner join general.socieda c on c.idrubro = b.idrubro \n"
			+ "   inner join seguridad.menu d on d.idmenu = a.idmenu \n"
			+ "   where c.idsocieda = :idsocieda \n"
			+ "   order by codmenu,orden",nativeQuery = true)
	public List<MenulistaDto> listamenuperfilnuevo(Long idsocieda);

}
