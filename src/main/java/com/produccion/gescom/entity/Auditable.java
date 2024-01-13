package com.produccion.gescom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.PrePersist;

//import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class Auditable<U> {
	private static final Log logger = LogFactory.getLog(Auditable.class);
	
	@Size(max=1)
	@Column(name="operacion")
	protected String operacion;
	
	@Column(name="idusuario")
	protected Long idusuario;
	
	@Column(name="idusuariom")
	protected Long idusuariom;

	@Column(name="fechacreacion")
	//protected java.sql.Timestamp fechacreacion;
	protected LocalDateTime fechacreacion;

	
	@PrePersist
	public void prePersist() throws Exception {
		/*TimeZone tz = TimeZone.getTimeZone("America/Lima");
		TimeZone.setDefault(tz);*/
		LocalDateTime now = LocalDateTime.now();
        //Timestamp timestamp = Timestamp.valueOf(now);
        //logger.info("Horassssssss");
        //logger.info(timestamp);
        //fechacreacion = timestamp;
        fechacreacion = LocalDateTime.now();
        operacion = "I";
    }
	
}