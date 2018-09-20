package br.furb.grafos.trabalho1.test;

import org.junit.Assert;
import org.junit.Test;

import br.furb.grafos.trabalho1.problema.RatoNoLabirinto;

public class RatoNoLabirintoTest {

	private RatoNoLabirinto ratoNoLabirinto = new RatoNoLabirinto();

	@Test
	public void teste01() {
		String problema = "16 20\n" + "Entrada A\n" + "A F\n" + "F C\n" + "C B\n" + "B D\n" + "C D\n" + "F J\n" + "J H\n" + "H G\n" + "J G\n" + "J *\n" + "* I\n" + "I L\n" + "L M\n" + "M K\n" + "K Saida\n" + "A K\n" + "C E\n" + "E I\n" + "I M";
		int resultado = ratoNoLabirinto.resolverProblema(problema);
		Assert.assertEquals(8, resultado);
	}

	@Test
	public void teste02() {
		String problema = "10 11\n" + "B A\n" + "Entrada A\n" + "B GT\n" + "GT H\n" + "H *\n" + "B *\n" + "* C\n" + "C I\n" + "I D\n" + "C D\n" + "D Saida";
		int resultado = ratoNoLabirinto.resolverProblema(problema);
		Assert.assertEquals(6, resultado);
	}

}
