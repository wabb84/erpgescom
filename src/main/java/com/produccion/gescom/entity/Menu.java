package com.produccion.gescom.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	 private Long idmenu;
	 
	 @NotNull
	 @NotBlank(message = "Nombre de Menú")
	 @Column(name="desmenu")
	 private String desmenu;
	 
	 @JsonIgnore
	 @Column(name="idpadre")
	 private Long idpadre;
	 
	 @NotNull
	 @NotBlank(message = "Nivel de Menú")
	 @Column(name="tiponivel")
	 private String tiponivel;

	 //@JsonIgnore
	 @NotBlank(message = "Código de Menú")
	 @Column(name="codmenu")
	 private String codmenu;
	 
	 //@JsonIgnore
	 @Column(name="orden")
	 private Long orden;
	 
	 
	 @JsonIgnore
	 //@NotBlank(message = "Estado de Menú")
	 @Column(name="estadomenu")
	 private String estadomenu;
	 
	 @Column(name="icon")
	 private String icon;
	 
	 @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "idpadre")
	 private List<Menu> subItems;
	 
	 /*@OneToMany(mappedBy = "menumap", cascade = CascadeType.ALL)
	 private List<Perfildet> perfildet;*/
}
