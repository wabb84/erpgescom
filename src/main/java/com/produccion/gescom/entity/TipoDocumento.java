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
@Table(name="tipodoc")
public class TipoDocumento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="idtipodoc", unique=true, nullable=false)
	 private Long id;
	 
	 @Column(name="destipodoc")
	 private String nombretipodoc;
	 
	 @Column(name="abrtipodoc")
	 private String abreviaturdoc;
}