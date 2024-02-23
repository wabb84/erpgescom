package com.produccion.gescom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.dto.UsuarioEditaDto;
import com.produccion.gescom.dto.UsuarioListaDto;
import com.produccion.gescom.entity.UserEntity;

@Repository
public interface UserLoginRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);

	@Query("select u.username, u.password from UserEntity u where u.username = ?1")
    UserEntity getName(String username);
	
	@Transactional(readOnly=true)
	@Query(value = "select a.iduser as idusuario, a.codusuario, a.desusuario,a.idsocieda,b.idrubro,b.nombrecome,c.idperfil, b.serie, a.sexo,\r\n"
			+ "   a.email, a.telefono, a.estadopas\r\n"
			+ "   from usuario a\r\n"
			+ "   inner join socieda b on b.idsocieda = a.idsocieda\r\n"
			+ "   left join usuarioper c on c.idusuario = a.iduser\r\n"
			+ "   where a.codusuario = :codusuario" ,nativeQuery = true)
	public UsuarioDatosLoginDto FindByDatosLogin(@Param("codusuario") String codusuario);
	
	@Transactional(readOnly=true)
	@Query(value = "select a.iduser as idusuario,a.codusuario,a.desusuario, b.idperfil,c.desperfil \n"
			+ "   from usuario a \n"
			+ "   left join usuarioper b on b.idusuario = a.iduser \n"
			+ "   left join perfil c on c.idperfil = b.idperfil \n"
			+ "   where a.idsocieda = :idsocieda",nativeQuery = true)
	public List<UsuarioListaDto> usuarioLista(@Param("idsocieda") Long idsocieda);

	@Transactional(readOnly=true)
	@Query(value = "select desusuario,email,telefono,idtipodoc,numerodoc,estadousuario,fechaini,fechafin,estadopas,sexo\r\n"
			+ "	   from usuario\r\n"
			+ "	   where iduser = :iduser" ,nativeQuery = true)
	
	public UsuarioEditaDto EditaUsuario(@Param("iduser") Long iduser);
}