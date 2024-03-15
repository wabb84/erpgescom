package com.produccion.gescom.salud.entity;

import java.io.Serializable;
import com.produccion.gescom.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Confagen extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idconfagenda", unique=true, nullable=false)
	private Long id;
	
	@Column(name="tipo")
	private String tipo; 
	
	@Column(name="idespecial")
	private Long idespecial; 
	
	@Column(name="idpersprof")
	private Long idpersprof;
	
	@Column(name="idsocieda")
	private Long idsocieda; 
	
	@Column(name="idturno")
	private Long idturno; 
	
	@Column(name="anio")
	private String anio; 
	
	@Column(name="mes")
	private String mes; 

	@Column(name="dia")
	private Long dia; 
	
	@Column(name="aniode")
	private String aniode; 

	@Column(name="anohas")
	private String anohas; 

	@Column(name="mesde")
	private String mesde; 

	@Column(name="meshas")
	private String meshas; 
}