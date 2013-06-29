package com.bacarini.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bacarini.common.Pesquisa;
import com.bacarini.common.Ranking;
import com.bacarini.common.Voto;
import com.bacarini.utils.ConnectionUtils;

public class VotoDAOHibernate implements VotoDAO {
static final Logger LOG = Logger.getLogger(VotoDAOHibernate.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Voto> obterListaVotos() {
		
		LOG.info("Iniciando o metodo obterListaVotos");
		List<Voto> listVotos = null;
		Session session;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return null;
			}
			listVotos = (List<Voto>) session.createCriteria(Voto.class).list();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return null;
		}
		
		LOG.info("Saindo do metodo obterListaVotos");
		return listVotos;
	}

	@Override
	public boolean cadastrarVoto(Voto voto) {
		LOG.info("Iniciando o metodo cadastrarVoto");
		Session session = null;
		try
		{			
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return false;
			}
			session.beginTransaction();
			session.save(voto);
			session.getTransaction().commit();
			
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return false;
		}finally{
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		
		LOG.info("Saindo do metodo cadastrarVoto");
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Ranking> obterRankingFilmes() {
		LOG.info("Iniciando o metodo obterRankingFilmes");
		List listRetorno = null;
		List<Ranking> listRanking = null;
		Session session;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return null;
			}
			session.beginTransaction();
			String hql = "select count(v.idFilme),f.nome "+
						 "	  from Voto v, Filme f      "+
						 " where v.idFilme=f.idFilme "+
						 "	group by v.idFilme,f.nome " +
						 "	order by count(v.idFilme) desc";
			
				listRetorno = session.createQuery(hql).list();
		       
		       Iterator ite = listRetorno.iterator();
		       listRanking = new ArrayList<Ranking>();
		       
			   while(ite.hasNext()){
				  
			   	  Object [] objects = (Object []) ite.next();
			
			   	  Ranking ranking = new Ranking();
			      ranking.setNumeroVotos((Long)objects[0]);
			      ranking.setNomeFilme((String)objects[1]);

			      listRanking.add(ranking);
			   }

			session.getTransaction().commit();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return null;
		}
		
		LOG.info("Saindo do metodo obterRankingFilmes");
		return listRanking;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<Pesquisa> obterVotoPorUsuario(Integer idUsuario) {
		LOG.info("Iniciando o metodo obterVotoPorUsuario");
		List listRetorno = null;
		List<Pesquisa> listaPesquisa= null;
		Session session;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return null;
			}
			session.beginTransaction();
			String hql = "select u.nomeUsuario, f.nome " +
					" from Voto v, Filme f,Usuario u " +
					" where v.idUsuario=? "+
					" and v.idFilme=f.idFilme "+
					" and v.idUsuario=u.idUsuario ";
			
				listRetorno = session.createQuery(hql).setInteger(0, idUsuario).list();
		       
		       Iterator ite = listRetorno.iterator();
		       listaPesquisa = new ArrayList<Pesquisa>();
		       
			   while(ite.hasNext()){
				  
			   	  Object [] objects = (Object []) ite.next();
			
			   	  Pesquisa pesquisa = new Pesquisa();
			      pesquisa.setNomeUsuario((String)objects[0]);
			      pesquisa.setNomeFilme((String)objects[1]);

			      listaPesquisa.add(pesquisa);
			   }

			session.getTransaction().commit();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return null;
		}
		
		LOG.info("Saindo do metodo obterVotoPorUsuario");
		return listaPesquisa;
	}
	
}
