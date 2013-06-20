package com.bacarini.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.bacarini.dao.FilmeDAOHibernate;

public class FilmeDAOHibernateTest {
	FilmeDAOHibernate filmeDAOHibernate;
	@Before
	public void setUp() throws Exception {
		filmeDAOHibernate = new FilmeDAOHibernate();
	}

	@Test
	public void testObterFilmes() {
		assertTrue(filmeDAOHibernate.obterListaFilmes().size()==5);
	}

	@Test
	public void testObterFilmePorId() {
		assertNotNull(filmeDAOHibernate.obterNomeFilmePorId(1));
	}

}
