package com.bacarini.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.bacarini.common.Voto;
import com.bacarini.dao.VotoDAOHibernate;

public class VotoDAOHibernateTest {
	VotoDAOHibernate votoDAOHibernate;
	Voto voto;
	@Before
	public void setUp() throws Exception {
		votoDAOHibernate = new VotoDAOHibernate();
		voto = new Voto();
	}

	@Test
	public void testObterListaVotos() {
		assertNotNull(votoDAOHibernate.obterListaVotos());
	}

	@Test
	public void testCadastrarVoto() {
		voto.setIdUsuario(1);
		voto.setIdFilme(1);
		assertTrue(votoDAOHibernate.cadastrarVoto(voto));
	}
	@Test
	public void testCadastrarVotoErrado() {
		voto.setIdUsuario(100);
		voto.setIdFilme(1);
		assertFalse(votoDAOHibernate.cadastrarVoto(voto));
	}
	@Test
	public void testObterRankingFilmes() {
		assertNotNull(votoDAOHibernate.obterRankingFilmes());
	}
}
