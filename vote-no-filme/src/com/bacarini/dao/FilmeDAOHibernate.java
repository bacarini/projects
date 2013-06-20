package com.bacarini.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bacarini.common.Filme;
import com.bacarini.utils.ConnectionUtils;

public class FilmeDAOHibernate implements FilmeDAO {
	static final Logger LOG = Logger.getLogger(FilmeDAOHibernate.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Filme> obterListaFilmes() {
		
		LOG.info("Iniciando o metodo obterListaFilmes");
		List<Filme> listFilme = null;
		Session session;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return null;
			}
			listFilme = (List<Filme>) session.createCriteria(Filme.class).list();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return null;
		}
		
		LOG.info("Saindo do metodo obterListaFilmes");
		return listFilme;
	}

	@Override
	public String obterNomeFilmePorId(Integer idFilme) {
		LOG.info("Iniciando o metodo obterFilmePorId");
		String retorno = null;
		Session session;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return null;
			}
			session.beginTransaction();
			String sql = "select nome from Filme where idfilme = ?";
			retorno = (String) session.createQuery(sql).setInteger(0, idFilme).uniqueResult();
			session.getTransaction().commit();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return null;
		}
		
		LOG.info("Saindo do metodo obterFilmePorId");
		return retorno;
	}

}
