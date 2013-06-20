package com.bacarini.action;

import org.apache.log4j.Logger;

import com.bacarini.common.ListaRetorno;
import com.bacarini.dao.UsuarioDAOHibernate;
import com.opensymphony.xwork2.ActionSupport;

public class UsuarioAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(UsuarioAction.class);
	private ListaRetorno listaRetorno;
	
	public ListaRetorno getListaRetorno() {
		return listaRetorno;
	}

	public void setListaRetorno(ListaRetorno listaRetorno) {
		this.listaRetorno = listaRetorno;
	}

	public String obterListaUsuario(){
		LOG.info("Entrei no método obterListaUsuario");
		try {
			UsuarioDAOHibernate usuarioDAOHibernate = new UsuarioDAOHibernate();

			listaRetorno = new ListaRetorno();
			listaRetorno.setListaUsuario(usuarioDAOHibernate.obterListaUsuario());
			listaRetorno.setTipoListaRetorno("listaUsuario");
			
			if(listaRetorno.getListaUsuario()==null){
				LOG.info("Lista de Usuários retornor nula!");
				return ERROR;
			}	
		} catch (Exception e) {
			LOG.info("Falha no método obterListaUsuario",e);
			return ERROR;
		}
		LOG.info("Sai no método obterListaUsuario");
		return SUCCESS;
	}

}
