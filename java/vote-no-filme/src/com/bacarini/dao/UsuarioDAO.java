package com.bacarini.dao;

import java.sql.SQLException;
import java.util.List;

import com.bacarini.common.Usuario;

public interface UsuarioDAO {
	public boolean incluir(Usuario usuario) throws SQLException;
	public boolean alterar(Usuario usuario) throws SQLException;
	public boolean excluir(Usuario usuario) throws SQLException;
	public Usuario procurarPorIdUsuario(Integer idUsuario);
	public Usuario procurarPorNome(String nomeUsuario);
	public List<Usuario> obterListaUsuario();
}
