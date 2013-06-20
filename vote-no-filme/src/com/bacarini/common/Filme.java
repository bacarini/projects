package com.bacarini.common;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="filme")
public class Filme implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer idFilme;
	private String nome;
	
	public Integer getIdFilme() {
		return idFilme;
	}
	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@ManyToMany(mappedBy = "filme") 
	private Set<Usuario> usuario = new HashSet<Usuario>();
}
