package br.furb.grafos.trabalho1.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.furb.grafos.trabalho1.exception.MatrizAdjacenciaException;
import br.furb.grafos.trabalho1.matrizadjacencia.OperacaoMatrizAdjacencia;

/**
 * @author ariel, sidnei
 */
public class MatrizAdjacenciaTest {

	private OperacaoMatrizAdjacencia operacaoMatrizAdjacencia;

	@Before
	public void before() {
		operacaoMatrizAdjacencia = new OperacaoMatrizAdjacencia();
		System.out.println("-------------------------------------");
	}

	@Test
	public void simplesNaoDirigidoBipartidoCompleto() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[8][8];

		matrizAdjacencia[0][5] = 1;
		matrizAdjacencia[0][6] = 1;
		matrizAdjacencia[0][7] = 1;

		matrizAdjacencia[1][5] = 1;
		matrizAdjacencia[1][6] = 1;
		matrizAdjacencia[1][7] = 1;

		matrizAdjacencia[2][5] = 1;
		matrizAdjacencia[2][6] = 1;
		matrizAdjacencia[2][7] = 1;

		matrizAdjacencia[3][5] = 1;
		matrizAdjacencia[3][6] = 1;
		matrizAdjacencia[3][7] = 1;

		matrizAdjacencia[4][5] = 1;
		matrizAdjacencia[4][6] = 1;
		matrizAdjacencia[4][7] = 1;

		matrizAdjacencia[5][0] = 1;
		matrizAdjacencia[5][1] = 1;
		matrizAdjacencia[5][2] = 1;
		matrizAdjacencia[5][3] = 1;
		matrizAdjacencia[5][4] = 1;

		matrizAdjacencia[6][0] = 1;
		matrizAdjacencia[6][1] = 1;
		matrizAdjacencia[6][2] = 1;
		matrizAdjacencia[6][3] = 1;
		matrizAdjacencia[6][4] = 1;

		matrizAdjacencia[7][0] = 1;
		matrizAdjacencia[7][1] = 1;
		matrizAdjacencia[7][2] = 1;
		matrizAdjacencia[7][3] = 1;
		matrizAdjacencia[7][4] = 1;

		assertEquals("A = 3\nB = 3\nC = 3\nD = 3\nE = 3\nF = 5\nG = 5\nH = 5", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Simples, Não dirigido, Bipartido Completo", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 15\nOs conjuntos encontrados no grafo são: [(A,F), (F,A), (A,G), (G,A), (A,H), (H,A), (B,F), (F,B), (B,G), (G,B), (B,H), (H,B), (C,F), (F,C), (C,G), (G,C), (C,H), (H,C), (D,F), (F,D), (D,G), (G,D), (D,H), (H,D), (E,F), (F,E), (E,G), (G,E), (E,H), (H,E)]", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	@Test
	public void simplesNaoDirigidoBipartido() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[6][6];

		matrizAdjacencia[0][3] = 1;
		matrizAdjacencia[0][5] = 1;
		matrizAdjacencia[1][3] = 1;
		matrizAdjacencia[1][4] = 1;
		matrizAdjacencia[2][4] = 1;
		matrizAdjacencia[2][5] = 1;
		matrizAdjacencia[3][0] = 1;
		matrizAdjacencia[3][1] = 1;
		matrizAdjacencia[4][1] = 1;
		matrizAdjacencia[4][2] = 1;
		matrizAdjacencia[5][0] = 1;
		matrizAdjacencia[5][2] = 1;

		assertEquals("A = 2\nB = 2\nC = 2\nD = 2\nE = 2\nF = 2", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Simples, Não dirigido, Bipartido, Regular", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 6\nOs conjuntos encontrados no grafo são: [(A,D), (D,A), (A,F), (F,A), (B,D), (D,B), (B,E), (E,B), (C,E), (E,C), (C,F), (F,C)]", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	@Test
	public void multigrafoDirigido() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[4][4];

		matrizAdjacencia[0][2] = 1;
		matrizAdjacencia[1][0] = 1;
		matrizAdjacencia[1][2] = 2;
		matrizAdjacencia[2][3] = 1;
		matrizAdjacencia[3][0] = 1;
		matrizAdjacencia[3][2] = 1;
		matrizAdjacencia[3][3] = 1;

		assertEquals("A = E(2), S(1)\nB = E(0), S(3)\nC = E(4), S(1)\nD = E(2), S(3)", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Multigrafo, Dirigido", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 8\nOs conjuntos encontrados no grafo são: [(B,A), (D,A), (A,C), (B,C), (B,C), (D,C), (C,D), (D,D)]", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	@Test
	public void simplesDirigido() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[4][4];

		matrizAdjacencia[0][2] = 1;
		matrizAdjacencia[1][0] = 1;
		matrizAdjacencia[1][2] = 1;
		matrizAdjacencia[2][3] = 1;
		matrizAdjacencia[3][0] = 1;
		matrizAdjacencia[3][2] = 1;

		assertEquals("A = E(2), S(1)\nB = E(0), S(2)\nC = E(3), S(1)\nD = E(1), S(2)", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Simples, Dirigido", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 6\nOs conjuntos encontrados no grafo são: [(B,A), (D,A), (A,C), (B,C), (D,C), (C,D)]", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	@Test
	public void simplesNaoDirigidoNulo() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[4][4];

		assertEquals("A = 0\nB = 0\nC = 0\nD = 0", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Simples, Não dirigido, Nulo, Regular", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 0\nOs conjuntos encontrados no grafo são: []", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	@Test
	public void multigrafoNaoDirigidoCompleto() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[3][3];

		matrizAdjacencia[0][0] = 1;
		matrizAdjacencia[0][1] = 1;
		matrizAdjacencia[0][2] = 1;
		matrizAdjacencia[1][0] = 1;
		matrizAdjacencia[1][1] = 1;
		matrizAdjacencia[1][2] = 1;
		matrizAdjacencia[2][0] = 1;
		matrizAdjacencia[2][1] = 1;
		matrizAdjacencia[2][2] = 1;

		assertEquals("A = 4\nB = 4\nC = 4", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Multigrafo, Não dirigido, Completo, Regular", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 6\nOs conjuntos encontrados no grafo são: [(A,A), (A,A), (A,B), (B,A), (A,C), (C,A), (B,B), (B,B), (B,C), (C,B), (C,C), (C,C)]", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	@Test
	public void simplesNaoDirigidoCompleto() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[3][3];

		matrizAdjacencia[0][1] = 1;
		matrizAdjacencia[1][0] = 1;

		matrizAdjacencia[0][2] = 1;
		matrizAdjacencia[2][0] = 1;

		matrizAdjacencia[1][2] = 1;
		matrizAdjacencia[2][1] = 1;

		assertEquals("A = 2\nB = 2\nC = 2", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Simples, Não dirigido, Completo, Regular", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 3\nOs conjuntos encontrados no grafo são: [(A,B), (B,A), (A,C), (C,A), (B,C), (C,B)]", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	@Test
	public void simplesNaoDirigidoRegular() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[5][5];

		matrizAdjacencia[0][1] = 1;
		matrizAdjacencia[1][0] = 1;

		matrizAdjacencia[1][2] = 1;
		matrizAdjacencia[2][1] = 1;

		matrizAdjacencia[2][3] = 1;
		matrizAdjacencia[3][2] = 1;

		matrizAdjacencia[3][4] = 1;
		matrizAdjacencia[4][3] = 1;

		matrizAdjacencia[4][0] = 1;
		matrizAdjacencia[0][4] = 1;

		assertEquals("A = 2\nB = 2\nC = 2\nD = 2\nE = 2", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Simples, Não dirigido, Regular", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 5\nOs conjuntos encontrados no grafo são: [(A,B), (B,A), (A,E), (E,A), (B,C), (C,B), (C,D), (D,C), (D,E), (E,D)]", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	@Test
	public void simplesDirigidoBipartido() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[4][4];

		matrizAdjacencia[0][1] = 1;
		matrizAdjacencia[1][2] = 1;
		matrizAdjacencia[2][3] = 1;
		matrizAdjacencia[3][0] = 1;

		assertEquals("A = E(1), S(1)\nB = E(1), S(1)\nC = E(1), S(1)\nD = E(1), S(1)", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Simples, Dirigido, Bipartido, Regular", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 4\nOs conjuntos encontrados no grafo são: [(D,A), (A,B), (B,C), (C,D)]", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	@Test
	public void simplesDirigidoRegular() throws MatrizAdjacenciaException {
		int[][] matrizAdjacencia = new int[5][5];

		matrizAdjacencia[0][1] = 1;
		matrizAdjacencia[1][2] = 1;
		matrizAdjacencia[2][3] = 1;
		matrizAdjacencia[3][4] = 1;
		matrizAdjacencia[4][0] = 1;

		assertEquals("A = E(1), S(1)\nB = E(1), S(1)\nC = E(1), S(1)\nD = E(1), S(1)\nE = E(1), S(1)", operacaoMatrizAdjacencia.grausDoVertice(matrizAdjacencia));
		assertEquals("Simples, Dirigido, Regular", operacaoMatrizAdjacencia.tipoDoGrafo(matrizAdjacencia));
		assertEquals("O número de arestas deste grafo é: 5\nOs conjuntos encontrados no grafo são: [(E,A), (A,B), (B,C), (C,D), (D,E)]", operacaoMatrizAdjacencia.arestasDoGrafo(matrizAdjacencia));
	}

	private void assertEquals(String esperado, String valor) {
		System.out.println(valor);
		Assert.assertEquals(esperado, valor);
	}
}
