package com.produccion.gescom.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="menu")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="idmenu", unique=true, nullable=false)
	 private Long id;
	 
	 @NotNull
	 @NotBlank(message = "Nombre de Menú")
	 @Column(name="desmenu")
	 private String desmenu;
	 
	 @NotNull
	 @Column(name="idpadre")
	 private Long idpadre;
	 
	 @NotNull
	 @NotBlank(message = "Nivel de Menú")
	 @Column(name="tiponivel")
	 private String tiponivel;
	 
	 @NotNull
	 @Column(name="orden")
	 private Long orden;
	 
	 @NotNull
	 @NotBlank(message = "Código de Menú")
	 @Column(name="codmenu")
	 private String codmenu;
	 
	 @NotNull
	 @NotBlank(message = "Estado de Menú")
	 @Column(name="estadomenu")
	 private String estadomenu;
}
