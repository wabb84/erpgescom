package com.produccion.gescom.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="profesion")
public class Profesion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idprofesion", unique=true, nullable=false)
	private Long id;
	
	@Column(name="descripcion")
	private String descripcion;	
	
	@Column(name="abreviatura")
	private String abreviatura;	
	
	@Column(name="vigencia")
	private String vigencia;

}
