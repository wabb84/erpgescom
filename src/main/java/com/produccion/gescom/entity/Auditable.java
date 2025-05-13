package com.produccion.gescom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.produccion.gescom.commons.UserContextHolder;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class Auditable<U> {
	protected String operacion;
	protected Long idusuario;
	protected Long idusuariom;
	protected LocalDateTime fechacreacion;
	
	@PrePersist
	public void prePersist() throws Exception {
        fechacreacion = LocalDateTime.now();
        operacion = "I";
        idusuario = UserContextHolder.getUserId();
    }
}