package com.produccion.gescom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.produccion.gescom.dto.MenulistaDto;
import com.produccion.gescom.dto.PerfilListaDto;
import com.produccion.gescom.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	@Transactional(readOnly=true)
	@Query(value = "select idperfil,desperfil,vigencia from perfil where idsocieda = :idsocieda",nativeQuery = true)
	public List<PerfilListaDto> perfilLista(@Param("idsocieda") Long idsocieda);
	
	@Transactional(readOnly=true)
	@Query(value = "select idmenu,desmenu,tiponivel,idpadre,icon,acceso, idmenurubro, idperfildet from (\r\n"
			+ " select e.idmenu,e.desmenu,e.tiponivel,coalesce(e.idpadre,0) as idpadre, e.icon, c.acceso,e.codmenu,e.orden, d.idmenurubro, c.idperfildet\r\n"
			+ "   from perfildet c\r\n"
			+ "   inner join menurubro d on d.idmenurubro = c.idmenurubro\r\n"
			+ "   inner join menu e on e.idmenu = d.idmenu\r\n"
			+ " where coalesce(e.estadomenu,'') = 'A' and c.idperfil = :idperfil\r\n"
			+ " union all\r\n"
			+ " select d.idmenu,d.desmenu,d.tiponivel,coalesce(d.idpadre,0) as idpadre, d.icon, '0' as acceso,d.codmenu,d.orden, c.idmenurubro, 0 as idperfildet \r\n"
			+ "   from perfil a \r\n"
			+ "   inner join socieda b on b.idsocieda = a.idsocieda\r\n"
			+ "   inner join menurubro c on c.idrubro = b.idrubro\r\n"
			+ "   inner join menu d on d.idmenu = c.idmenu\r\n"
			+ "   where a.idperfil = :idperfil and d.idmenu not in (select e.idmenu from perfildet c \r\n"
			+ "										   inner join menurubro d on d.idmenurubro = c.idmenurubro\r\n"
			+ "										   inner join menu e on e.idmenu = d.idmenu\r\n"
			+ "										   where coalesce(e.estadomenu,'') = 'A' and c.idperfil = :idperfil)) as uno\r\n"
			+ "   order by codmenu,orden",nativeQuery = true)
	public List<MenulistaDto> perfildetalleLista(Long idperfil);

}
