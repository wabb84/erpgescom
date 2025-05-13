package com.produccion.gescom.entity;

import java.io.Serializable;
import jakarta.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="perfildet", schema="seguridad")
public class Perfildet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="idperfildet", unique=true, nullable=false)
	 private Long id;
	 private Long idmenurubro;
	 private Long idmenu;
	 private String acceso;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE, targetEntity = Perfil.class)
	@JoinColumn(name="idperfil")	
	private Perfil perfilmapdet;		
}
