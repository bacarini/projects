package com.bacarini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.bacarini.common.Usuario;
import com.bacarini.utils.ConnectionUtils;

public class UsuarioDAOJDBC implements UsuarioDAO{
	static final Logger LOG = Logger.getLogger(UsuarioDAOJDBC.class);
	
	@Override
	public boolean incluir(Usuario usuario) throws SQLException {
		LOG.info("Iniciando o metodo inserir");
		
		Connection con = null;
		try
		{
			if(procurarPorNome(usuario.getNomeUsuario())!=null){
				LOG.info("Usuário "+usuario.getNomeUsuario()+" já cadastrado!");
				LOG.info("Saindo do metodo inserir");
				return false;
			}
			con = ConnectionUtils.abrirConnectionJDBC();
	
			String sql = "insert into usuario values(?,?,?)";
			PreparedStatement st  = (PreparedStatement) con.prepareStatement(sql);
			
			st.setInt(1,ConnectionUtils.nextval("usuario_idusuario_seq"));
			st.setString(2,usuario.getNomeUsuario());
			st.setString(3,usuario.getEmail());
			st.executeUpdate();
			con.commit();
			
			LOG.info("Cadastrado com sucesso");
			st.close();
			ConnectionUtils.fecharConnectionJDBC(con);
		}
		catch(Exception e)
		{
			if(con!=null){con.rollback();}
			LOG.error("Falha....",e);
			return false;
		}
		
		LOG.info("Saindo do metodo inserir");
		return true;
	}

	@Override
	public boolean alterar(Usuario usuario) throws SQLException {
		LOG.info("Iniciando o metodo alterar");
		Connection con = null;
		
		try
		{
			con = ConnectionUtils.abrirConnectionJDBC();
			
			String sql = "UPDATE usuario SET nomeusuario = ? , email=? where idusuario=?";
			
			PreparedStatement st  = con.prepareStatement(sql);
			st.setString(1, usuario.getNomeUsuario());
			st.setString(2, usuario.getEmail());
			st.setInt(3, usuario.getIdUsuario());
			
			if(st.executeUpdate() == 0){
				LOG.info("Usuário ["+usuario.getIdUsuario()+"/"+usuario.getNomeUsuario()+"] não alterado.");
				LOG.info("Saindo do metodo alterar");
				return false;
			}
			con.commit();
			st.close();
		}
		catch(Exception e)
		{
			if(con!=null){con.rollback();}	
			LOG.error("Falha....",e);
			return false;
		}finally{
					
			ConnectionUtils.fecharConnectionJDBC(con);			
		}
		LOG.info("Saindo do metodo alterar");

		return true;
	}

	@Override
	public boolean excluir(Usuario usuario) throws SQLException {
		LOG.info("Iniciando o metodo excluir");
		Connection con = null;
		
		try
		{
			con = ConnectionUtils.abrirConnectionJDBC();
			
			String sql = "delete from usuario where idusuario=?";
			
			PreparedStatement st  = con.prepareStatement(sql);
			st.setInt(1, usuario.getIdUsuario());
			
			if(st.executeUpdate() == 0){
				LOG.info("Não excluído nenhum Usuário. Id["+usuario.getIdUsuario()+"]");
				LOG.info("Saindo do metodo excluir");
				return false;
			}
			con.commit();
			st.close();
		}
		catch(Exception e)
		{
			if(con!=null){con.rollback();}	
			LOG.error("Falha....",e);
			return false;
		}finally{	
			ConnectionUtils.fecharConnectionJDBC(con);			
		}
		LOG.info("Saindo do metodo excluir");

		return true;
	}
	
	@Override
	public Usuario procurarPorIdUsuario(Integer idUsuario) {
		LOG.info("Iniciando o metodo procurarPorIdUsuario");
		Usuario retorno = null;
		Connection con = null;
		
		try
		{
			con = ConnectionUtils.abrirConnectionJDBC();
			
			String sql = "select idusuario,nomeusuario,email from usuario where idusuario = ?";
			
			PreparedStatement st  = con.prepareStatement(sql);
			st.setInt(1, idUsuario);
			
			ResultSet resultado = st.executeQuery();
		
			if(resultado.next())
			{
				retorno = new Usuario();
				retorno.setIdUsuario(Integer.parseInt(resultado.getString("idusuario")));
				retorno.setNomeUsuario(resultado.getString("nomeusuario"));	
				retorno.setEmail(resultado.getString("email"));				
			}
			
			st.close();
			ConnectionUtils.fecharConnectionJDBC(con);
		}
		catch(SQLException e)
		{
			LOG.error("Falha....",e);
		}
		LOG.info("Saindo do metodo procurarPorIdUsuario");
		return retorno;
	}
	
	@Override
	public Usuario procurarPorNome(String nomeUsuario) {
		LOG.info("Iniciando o metodo procurarPorNome");
		Usuario retorno = null;
		Connection con = null;
		
		try
		{
			con = ConnectionUtils.abrirConnectionJDBC();
			
			String sql = "select idusuario,login,senha from usuario where login  = ?";
			
			PreparedStatement st  = con.prepareStatement(sql);
			st.setString(1, nomeUsuario);
			
			ResultSet resultado = st.executeQuery();
			
			if(resultado.next())
			{
				retorno = new Usuario();
				retorno.setIdUsuario(Integer.parseInt(resultado.getString("idusuario")));
				retorno.setNomeUsuario(resultado.getString("nomeusuario"));	
				retorno.setEmail(resultado.getString("email"));				
			}
			
			st.close();
			ConnectionUtils.fecharConnectionJDBC(con);
		}
		catch(SQLException e)
		{
			LOG.error("Falha....",e);
		}
		LOG.info("Saindo do metodo procurarPorNome");
		return retorno;
	}

	@Override
	public List<Usuario> obterListaUsuario() {
		// TODO Auto-generated method stub
		return null;
	}
}
