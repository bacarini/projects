package com.bacarini.common;

import java.util.List;

public class ListaRetorno {

	private String tipoListaRetorno;
	private List<Usuario> listaUsuario;
	private List<Filme> listaFilmes;
	private List<Pesquisa> listaPresquisa;
	private List<Ranking> listaRaking;
	public String getTipoListaRetorno() {
		return tipoListaRetorno;
	}
	public void setTipoListaRetorno(String tipoListaRetorno) {
		this.tipoListaRetorno = tipoListaRetorno;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	public List<Filme> getListaFilmes() {
		return listaFilmes;
	}
	public void setListaFilmes(List<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}
	public List<Pesquisa> getListaPresquisa() {
		return listaPresquisa;
	}
	public void setListaPresquisa(List<Pesquisa> listaPresquisa) {
		this.listaPresquisa = listaPresquisa;
	}
	public List<Ranking> getListaRaking() {
		return listaRaking;
	}
	public void setListaRaking(List<Ranking> listaRaking) {
		this.listaRaking = listaRaking;
	}
}
