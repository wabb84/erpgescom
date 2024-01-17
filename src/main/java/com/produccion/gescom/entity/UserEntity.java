package com.produccion.gescom.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="usuario")
public class UserEntity extends Auditable<String> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iduser", unique=true, nullable=false)
	private Long iduser;
	
	@Column(name="codusuario")
    private String username;
	  
	@Column(name="password")
	private String password;
	
	//@Email(message = "Email no válido", regexp="{(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])}")
	@Email(message = "Email no válido")
	@Column(name="email")
	private String email;
	 
	@Column(name="telefono")
	private String telefono;
	 
	@Column(name="desusuario")
	private String nombreusuario;
	 
	@Column(name="numerodoc")
	private String numdocu;
	
	/*@Size(max=1)
	@Column(name="estadousuario")
	private String estado;*/
	
	@Enumerated(EnumType.STRING)
	private EEstadoUsuario estadousuario;
	 
	@Column(name="fechaini")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechai;
	 
	@Column(name="fechafin")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaf;
	
	@NotNull
	@Column(name="idtipodoc")
	private Long idtipodoc;
	
	@NotNull
	@Column(name="idsocieda")
	private Long idsocieda;
	
	@Column(name="estadopas")
	private String estadopas;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Rol.class, cascade = CascadeType.PERSIST)
	@JoinTable(name = "usuarol", joinColumns = @JoinColumn(name = "idusuario"), inverseJoinColumns = @JoinColumn(name = "idrol"))
	private Set<Rol> roles;
}





//estadousuario A=Activo, I = Inactivo, T = Temporal
//fechaini
//fechafin



//import java.util.Date;
//import org.springframework.format.annotation.DateTimeFormat;


//import jakarta.validation.constraints.Email;

/*
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="usuario")
public class UserEntity extends Auditable<String> implements Serializable{
	 private static final long serialVersionUID = 1L;
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="id", unique=true, nullable=false)
	 private Long id;
	 
	 @Column(name="codusuario")
	 private String username;
	  
	 @Column(name="password")
	 private String password;*/
	 
	 /*@Email(message = "Email no válido", regexp="{(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])}")
	 @Column(name="email")
	 private String email;
	 
	 @Column(name="telefono")
	 private String telefono;
	 
	 @Column(name="desusuario")
	 private String nombreusuario;
	 
	 @Column(name="numerodoc")
	 private String numdocu;
	 
	 @Column(name="estadousuario")
	 private String estado;
	 
	 @Column(name="fechaini")
	 @Temporal(TemporalType.DATE)
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date fechai;
	 
	 @Column(name="fechafin")
	 @Temporal(TemporalType.DATE)
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date fechaf;*/
	 
	/* @ManyToMany(fetch = FetchType.EAGER, targetEntity = Rol.class, cascade = CascadeType.PERSIST)
	 @JoinTable(name = "usuarol", joinColumns = @JoinColumn(name = "idusuario"), inverseJoinColumns = @JoinColumn(name = "idrol"))
	 private Set<Rol> roles;
	 
}*/
