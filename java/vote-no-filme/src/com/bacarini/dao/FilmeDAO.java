package com.bacarini.dao;

import java.util.List;

import com.bacarini.common.Filme;

public interface FilmeDAO {
	public List<Filme> obterListaFilmes();
	public String obterNomeFilmePorId(Integer idFilme);
}
