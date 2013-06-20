package com.bacarini.action;

import org.apache.log4j.Logger;

import com.bacarini.common.ListaRetorno;
import com.bacarini.dao.FilmeDAOHibernate;
import com.opensymphony.xwork2.ActionSupport;

public class FilmeAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(FilmeAction.class);
	private ListaRetorno listaRetorno;
	
	public ListaRetorno getListaRetorno() {
		return listaRetorno;
	}

	public void setListaRetorno(ListaRetorno listaRetorno) {
		this.listaRetorno = listaRetorno;
	}
	
	public String obterListaFilmes(){	
		LOG.info("Entrei no método obterListaFilmes");
		try {
			FilmeDAOHibernate filmeDAOHibernate = new FilmeDAOHibernate();
	
			listaRetorno = new ListaRetorno();
			listaRetorno.setListaFilmes(filmeDAOHibernate.obterListaFilmes());
			listaRetorno.setTipoListaRetorno("listaFilmes");
			
			if(listaRetorno.getListaFilmes()==null){
				LOG.info("Lista de Votos retornor nula!");
				return ERROR;
			}
		} catch (Exception e) {
			LOG.info("Falha no método obterListaFilmes",e);
			return ERROR;
		}
		LOG.info("Sai no método obterListaFilmes");
		return SUCCESS;
	}
}
