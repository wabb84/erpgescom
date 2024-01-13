package com.produccion.gescom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produccion.gescom.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {


}
