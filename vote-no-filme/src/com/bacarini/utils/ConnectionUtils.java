package com.bacarini.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public abstract class ConnectionUtils {
	static final Logger LOG = Logger.getLogger(ConnectionUtils.class);
	static Connection con;
	
	public static Connection abrirConnectionJDBC()
	{
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vote_filme_db?user=postgres&password=root");
			con.setAutoCommit(false);
			LOG.info("Abrindo conexao");
			return con;
		}
		catch(SQLException e)
		{
			LOG.error("Falha....",e);
		} catch (ClassNotFoundException e) {
			LOG.error("Classe não encontrada....",e);
		}
		return null;
	}
	public static void fecharConnectionJDBC(Connection con)
	{
		try {
			con.close();
			LOG.info("Fechando conexao");
		} catch (SQLException e) {
			LOG.error("Falha....",e);
		}
	}
	public static int nextval(String seq){
		int id = 0;
		Statement stgfid;
		try {
			stgfid = con.createStatement();
			ResultSet rsgfid = stgfid.executeQuery("SELECT nextval('"+seq+"')");
			rsgfid.next();
			id = rsgfid.getInt(1);
			rsgfid.close();
			stgfid.close();
			
		} catch (SQLException e) {
			LOG.error("Erro ao recuperar nextval da "+seq,e);
		}
		return id;
	}
	
	public static Session abrirConnectionHibernate()
	{
	
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		 
		Session session = sessionFactory.openSession();

		LOG.info("Abrindo conexao");
		return session;
	
	}
	public static void fecharConnectionHibernate(Session session)
	{
		session.close();
		LOG.info("Fechando conexao");
	}
}
