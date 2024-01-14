package com.produccion.gescom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.produccion.gescom.dto.UsuarioDatosLoginDto;
import com.produccion.gescom.entity.UserEntity;

@Repository
public interface UserLoginRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);

	@Query("select u.username, u.password from UserEntity u where u.username = ?1")
    UserEntity getName(String username);
	
	@Transactional(readOnly=true)
	@Query(value = "select a.codusuario, a.desusuario,a.idsocieda,b.idrubro,b.nombrecome,c.idperfil\r\n"
			+ "   from usuario a\r\n"
			+ "   inner join socieda b on b.idsocieda = a.idsocieda\r\n"
			+ "   inner join usuarioper c on c.idusuario = a.iduser\r\n"
			+ "   where a.iduser = :idusuario" ,nativeQuery = true)
	
	public UsuarioDatosLoginDto FindByDatosLogin(@Param("idusuario") Long idusuario);
}
