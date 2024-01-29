package com.produccion.gescom.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tipobusqueda")
public class TipoBusqueda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="idtipobusqueda", unique=true, nullable=false)
	 private Long id;
	 
	 @Column(name="tipo")
	 private String tipo;
	 
	 @Column(name="descripcion")
	 private String descripcion;
	 
	 @Column(name="codigobus")
	 private String codigobus;
	 
	 @Column(name="orden")
	 private Long orden;
	 
	 @Column(name="vigencia")
	 private String vigencia;
	 
}
