package com.bacarini.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.bacarini.common.Usuario;
import com.bacarini.dao.UsuarioDAOHibernate;

public class UsuarioDAOHibernateTest {

	UsuarioDAOHibernate usuarioDAOHibernate;
	Usuario usuario;
	@Before
	public void setUp() throws Exception {
		usuarioDAOHibernate = new UsuarioDAOHibernate();
		usuario = new Usuario();
	}

	@Test
	public void testIncluirNovoUsuario() throws SQLException {
		usuario.setNomeUsuario("novo");
		usuario.setEmail("654");
		assertTrue(usuarioDAOHibernate.incluir(usuario));
	}
	@Test
	public void testIncluirMesmoUsuario() throws SQLException {
		usuario.setIdUsuario(3);
		usuario.setNomeUsuario("bruno");
		usuario.setEmail("jgvdgyf");
		assertFalse(usuarioDAOHibernate.incluir(usuario));
	}

	@Test
	public void testAlterar() throws SQLException {
		usuario.setIdUsuario(1);
		usuario.setNomeUsuario("bruno");
		usuario.setEmail("BBBBBB");
		assertTrue(usuarioDAOHibernate.alterar(usuario));
	}
	@Test
	public void testAlterarErrado() throws SQLException {
		usuario.setIdUsuario(100);
		usuario.setNomeUsuario("bruno");
		usuario.setEmail("AAAAAA");
		assertFalse(usuarioDAOHibernate.alterar(usuario));
	}
	@Test
	public void testExcluir() throws SQLException {
		usuario.setIdUsuario(4);
		assertTrue(usuarioDAOHibernate.excluir(usuario));
	}

	@Test
	public void testProcurarPorIdUsuario() {
		assertNotNull(usuarioDAOHibernate.procurarPorIdUsuario(1));
	}

	@Test
	public void testProcurarPorLogin() {
		assertNotNull(usuarioDAOHibernate.procurarPorNome("bruno"));
	}

}
