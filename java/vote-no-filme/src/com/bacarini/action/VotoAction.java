package com.bacarini.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bacarini.common.ListaRetorno;
import com.bacarini.common.Pesquisa;
import com.bacarini.common.Usuario;
import com.bacarini.common.Voto;
import com.bacarini.dao.FilmeDAOHibernate;
import com.bacarini.dao.UsuarioDAOHibernate;
import com.bacarini.dao.VotoDAOHibernate;
import com.opensymphony.xwork2.ActionSupport;

public class VotoAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(VotoAction.class);
	public VotoAction(){
		listaRetorno = new ListaRetorno();
	}
	
	private ListaRetorno listaRetorno;
	private Integer idUsuario;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ListaRetorno getListaRetorno() {
		return listaRetorno;
	}

	public void setListaRetorno(ListaRetorno listaRetorno) {
		this.listaRetorno = listaRetorno;
	}
	public String obterVotoPorUsuario(){
		LOG.info("Entrei no método obterVotoPorUsuario");
		try {
			VotoDAOHibernate votoDAOHibernate = new VotoDAOHibernate();

			
			listaRetorno.setListaPresquisa(votoDAOHibernate.obterVotoPorUsuario(idUsuario));
			listaRetorno.setTipoListaRetorno("listaPresquisa");
			
			if(listaRetorno.getListaPresquisa()==null){
				LOG.info("Lista do Ranking retornor nula!");
				return ERROR;
			}	
		} catch (Exception e) {
			LOG.info("Falha no método obterVotoPorUsuario",e);
			return ERROR;
		}
		LOG.info("Sai no método obterVotoPorUsuario");
		return SUCCESS;
	}
	public String obterRanking(){
		LOG.info("Entrei no método obterRanking");
		try {
			VotoDAOHibernate votoDAOHibernate = new VotoDAOHibernate();

			
			listaRetorno.setListaRaking(votoDAOHibernate.obterRankingFilmes());
			listaRetorno.setTipoListaRetorno("listaRaking");
			
			if(listaRetorno.getListaRaking()==null){
				LOG.info("Lista do Ranking retornor nula!");
				return ERROR;
			}	
		} catch (Exception e) {
			LOG.info("Falha no método obterRanking",e);
			return ERROR;
		}
		LOG.info("Sai no método obterRanking");
		return SUCCESS;
	}
	
	public String obterListaVoto(){
		LOG.info("Entrei no método obterListaVoto");
		try {
			VotoDAOHibernate votoDAOHibernate = new VotoDAOHibernate();
			FilmeDAOHibernate filmeDAOHibernate = new FilmeDAOHibernate();
			UsuarioDAOHibernate usuarioDAOHibernate = new UsuarioDAOHibernate();
				
			List<Voto> listaVotos = votoDAOHibernate.obterListaVotos();
			
			if(listaVotos!=null){
				listaRetorno.setListaPresquisa(new ArrayList<Pesquisa>());
				Usuario usuario =  new Usuario();
				
				for (Voto voto : listaVotos) {
					Pesquisa pesquisa = new Pesquisa();
					pesquisa.setNomeFilme(filmeDAOHibernate.obterNomeFilmePorId(voto.getIdFilme()));
					pesquisa.setIdFilme(voto.getIdFilme());
					
					usuario = usuarioDAOHibernate.procurarPorIdUsuario(voto.getIdUsuario());
					pesquisa.setIdUsuario(usuario.getIdUsuario());
					pesquisa.setNomeUsuario(usuario.getNomeUsuario());
					
					listaRetorno.getListaPresquisa().add(pesquisa);
				}				
			}else{
				LOG.info("Lista de Votos retornor nula!");
			}

			listaRetorno.setTipoListaRetorno("listaPesquisa");		
		} catch (Exception e) {
			LOG.info("Falha no método obterListaVoto",e);
			return ERROR;
		}
		LOG.info("Sai no método obterListaVoto");
		return SUCCESS;
	}

}
