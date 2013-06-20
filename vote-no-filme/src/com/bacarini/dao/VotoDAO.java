package com.bacarini.dao;

import java.util.List;

import com.bacarini.common.Pesquisa;
import com.bacarini.common.Ranking;
import com.bacarini.common.Voto;

public interface VotoDAO {
	public List<Voto> obterListaVotos();
	public boolean cadastrarVoto(Voto voto);
	public List<Ranking> obterRankingFilmes();
	List<Pesquisa> obterVotoPorUsuario(Integer idUsuario);
}
