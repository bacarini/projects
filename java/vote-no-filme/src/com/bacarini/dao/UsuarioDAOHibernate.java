package com.bacarini.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.bacarini.common.Usuario;
import com.bacarini.utils.ConnectionUtils;

public class UsuarioDAOHibernate implements UsuarioDAO {
	static final Logger LOG = Logger.getLogger(UsuarioDAOHibernate.class);
	
	@Override
	public boolean incluir(Usuario usuario) {
		LOG.info("Iniciando o metodo inserir");
		Session session;
		try
		{
			if(procurarPorNome(usuario.getNomeUsuario())!=null){
				LOG.info("Usuário "+usuario.getNomeUsuario()+" já cadastrado!");
				LOG.info("Saindo do metodo inserir");
				return false;
			}
			
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return false;
			}
			session.beginTransaction();
			session.save(usuario);
			session.getTransaction().commit();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return false;
		}
		
		LOG.info("Saindo do metodo inserir");
		return true;
	}
	
	@Override
	public boolean alterar(Usuario usuario) {
		LOG.info("Iniciando o metodo alterar");
		Session session = null;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return false;
			}
			session.beginTransaction();
			session.update(usuario);			
			session.getTransaction().commit();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			if(session!=null && session.getTransaction()!=null){session.getTransaction().rollback();}
			LOG.error("Falha....",e);
			return false;
		}
		
		LOG.info("Saindo do metodo alterar");
		return true;
	}

	@Override
	public boolean excluir(Usuario usuario) {
		LOG.info("Iniciando o metodo excluir");
		Session session = null;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return false;
			}
			session.beginTransaction();
			session.delete(usuario);
			session.getTransaction().commit();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			if(session!=null && session.getTransaction()!=null){session.getTransaction().rollback();}
			LOG.error("Falha....",e);
			return false;
		}
		
		LOG.info("Saindo do metodo excluir");
		return true;
	}

	@Override
	public Usuario procurarPorIdUsuario(Integer idUsuario) {
		LOG.info("Iniciando o metodo procurarPorIdUsuario");
		Usuario usuario = null;
		Session session;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return null;
			}
			session.beginTransaction();
			String sql = "from Usuario where idusuario = ?";
	        usuario = (Usuario) session.createQuery(sql).setInteger(0, idUsuario).uniqueResult();
			session.getTransaction().commit();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return null;
		}
		
		LOG.info("Saindo do metodo procurarPorIdUsuario");
		return usuario;
	}

	@Override
	public Usuario procurarPorNome(String nomeUsuario) {
		LOG.info("Iniciando o metodo procurarPorNome");
		Usuario usuario = null;
		Session session;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return null;
			}
			session.beginTransaction();
			String sql = "from Usuario where nomeusuario = ?";
	        usuario = (Usuario) session.createQuery(sql).setString(0, nomeUsuario).uniqueResult();
			session.getTransaction().commit();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return null;
		}
		
		LOG.info("Saindo do metodo procurarPorNome");
		return usuario;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> obterListaUsuario() {
		
		LOG.info("Iniciando o metodo obterListaUsuario");
		List<Usuario> listaUsuario = null;
		Session session;
		try
		{
			session = ConnectionUtils.abrirConnectionHibernate();
			if(session==null){
				LOG.error("Session é nula.");
				return null;
			}
			listaUsuario = (List<Usuario>) session.createCriteria(Usuario.class).list();
			ConnectionUtils.fecharConnectionHibernate(session);
		}
		catch(Exception e)
		{
			LOG.error("Falha....",e);
			return null;
		}
		
		LOG.info("Saindo do metodo obterListaUsuario");
		return listaUsuario;
	}

}
