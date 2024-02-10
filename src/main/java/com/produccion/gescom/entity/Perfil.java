package com.produccion.gescom.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
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
@Table(name="perfil")
public class Perfil extends Auditable<String> implements Serializable  {
	 private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="idperfil", unique=true, nullable=false)
	 private Long id;
	 
	 @NotNull
	 @NotBlank(message = "Nombre de Perfil")
	 @Column(name="desperfil")
	 private String desperfil;
	 
	 @Enumerated(EnumType.STRING)
	 private EVigencia vigencia;
	 
	 @NotNull
	 @Column(name="idsocieda")
	 private Long idsocieda;
	 
	 @OneToMany(mappedBy = "perfilmapdet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<Perfildet> perfildet;
	 
	 public void Perfil(){
		perfildet = new ArrayList<Perfildet>();
	 }
}

