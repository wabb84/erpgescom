package com.produccion.gescom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.produccion.gescom.entity.UserEntity;

@Repository
public interface UserLoginRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);

	@Query("select u.username, u.password from UserEntity u where u.username = ?1")
    UserEntity getName(String username);
}
