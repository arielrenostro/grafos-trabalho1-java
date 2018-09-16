package br.furb.grafos.trabalho1.utils;

import br.furb.grafos.trabalho1.exception.MatrizAdjacenciaException;

/**
 *
 * @author ariel, sidnei
 *
 */
public class GrafosUtils {

	public static void validarMatrizAdjacencia(int[][] matriz) throws MatrizAdjacenciaException {
		if (null == matriz) {
			throw new MatrizAdjacenciaException("Matriz não informada ou nula!");
		}

		int[] colunas;
		for (int idxLinha = 0; idxLinha < matriz.length; idxLinha++) {
			colunas = matriz[idxLinha];
			if (null == colunas) {
				throw new MatrizAdjacenciaException("Colunas da linha [" + idxLinha + "] são nulas!");
			}

			if (matriz.length != colunas.length) {
				throw new MatrizAdjacenciaException("Colunas da linha [" + idxLinha + "] não tem o mesmo tamanho!");
			}
		}
	}

	public static String[] getNomesVerticesMatrizAdjacencia(int[][] matriz) throws MatrizAdjacenciaException {
		validarMatrizAdjacencia(matriz);
		String[] nomes = new String[matriz.length];

		char ultimoChar = 'A';
		String preNome = "";

		for (int idx = 0; idx < matriz.length; idx++) {
			if (ultimoChar > 90) { // Z
				ultimoChar = 'A';
				preNome += "A";
			}
			nomes[idx] = preNome + ultimoChar;
			ultimoChar++;
		}

		return nomes;
	}

}
