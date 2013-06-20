package com.bacarini.common;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer idUsuario;
	
	private String nomeUsuario;
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String email;

	@ManyToMany 
	@JoinTable(name = "voto", 
		joinColumns = { @JoinColumn(name = "idusuario") }, 
		inverseJoinColumns = { @JoinColumn(name = "idfilme") }) 
	private Set<Filme> filme = new HashSet<Filme>();
}
