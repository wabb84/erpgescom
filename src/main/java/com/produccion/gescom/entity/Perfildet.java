package com.produccion.gescom.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="perfildet")
public class Perfildet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="idperfildet", unique=true, nullable=false)
	 private Long idperfildet;
	 
	 /*@Column(name="idperfil")
	 private Long idperfil;*/
	 
	 @Column(name="idmenurubro")
	 private Long idmenurubro;
	 
	 //@Column(name="idmenu")
	 //private Long idmenu;
	 
	 /*@ManyToOne
	 @JoinColumn(name = "idperfil", nullable = false, updatable = false)
	 private Perfil perfilmap;*/
	 
	 @JsonIgnore
	 @ManyToOne(targetEntity = Perfil.class)
	 @JoinColumn(name="idperfil")	
	 private Perfil perfilmap;
	 
	 /*@JsonIgnore
	 @ManyToOne(targetEntity = Menu.class)
	 @JoinColumn(name="idmenu")	
	 private Menu menumap;*/
	 
	 
	 
}
