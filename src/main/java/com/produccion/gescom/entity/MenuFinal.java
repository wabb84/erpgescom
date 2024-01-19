package com.produccion.gescom.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class MenuFinal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long idmenu;
	 
	 @NotNull
	 @NotBlank(message = "Nombre de Menú")
	 @Column(name="desmenu")
	 private String name;
 
	 @Column(name="idpadre")
	 private Long idpadre;
	 
	 @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "idpadre")
	 private List<MenuFinal> subItems;

	 //private String url;
	 private String tiponivel;
	 private String estadomenu;
	 private String icon;
}
