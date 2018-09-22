package br.furb.grafos.trabalho1.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.furb.grafos.trabalho1.problema.DuduFazServico;

/**
 *
 * @author ariel, sidnei
 *
 */
public class DuduFazServicoTest {

	@Before
	public void before() {
		System.out.println("---------------------------------");
	}

	@Test
	public void test01() {
		String problema = "3\r\n2 1\r\n1 2\r\n2 2\r\n1 2\r\n2 1\r\n4 4\r\n2 3\r\n3 4\r\n4 2\r\n1 3";
		assertEquals("NAO\nSIM\nSIM", new DuduFazServico().resolverProblema(problema));
	}

	private void assertEquals(String esperado, String valor) {
		System.out.println(valor);
		Assert.assertEquals(esperado, valor);
	}
}
