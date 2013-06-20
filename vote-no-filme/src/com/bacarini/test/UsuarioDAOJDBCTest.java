package com.bacarini.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.bacarini.common.Usuario;
import com.bacarini.dao.UsuarioDAOJDBC;

public class UsuarioDAOJDBCTest {
	UsuarioDAOJDBC usuarioDAOJDBC;
	Usuario usuario;
	@Before
	public void setUp() throws Exception {
		usuarioDAOJDBC = new UsuarioDAOJDBC();
		usuario = new Usuario();
	}

	@Test
	public void testIncluirNovoUsuario() throws SQLException {
		usuario.setNomeUsuario("bia");
		usuario.setEmail("5151");
		assertTrue(usuarioDAOJDBC.incluir(usuario));
	}
	@Test
	public void testIncluirMesmoUsuario() throws SQLException {
		usuario.setIdUsuario(7);
		usuario.setNomeUsuario("bruno");
		usuario.setEmail("jgvdgyf");
		assertFalse(usuarioDAOJDBC.incluir(usuario));
	}

	@Test
	public void testAlterar() throws SQLException {
		usuario.setIdUsuario(1);
		usuario.setNomeUsuario("bruno");
		usuario.setEmail("WWAAA");
		assertTrue(usuarioDAOJDBC.alterar(usuario));
	}
	@Test
	public void testAlterarErrado() throws SQLException {
		usuario.setIdUsuario(100);
		usuario.setNomeUsuario("bruno");
		usuario.setEmail("AAAAAA");
		assertFalse(usuarioDAOJDBC.alterar(usuario));
	}
	@Test
	public void testExcluir() throws SQLException {
		usuario.setIdUsuario(2);
		assertTrue(usuarioDAOJDBC.excluir(usuario));
	}

	@Test
	public void testProcurarPorIdUsuario() {
		assertNotNull(usuarioDAOJDBC.procurarPorIdUsuario(1));
	}

	@Test
	public void testProcurarPorNome() {
		assertNotNull(usuarioDAOJDBC.procurarPorNome("bruno"));
	}

}
