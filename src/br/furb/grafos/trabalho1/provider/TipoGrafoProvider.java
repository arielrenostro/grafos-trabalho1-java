package br.furb.grafos.trabalho1.provider;

import br.furb.grafos.trabalho1.exception.MatrizAdjacenciaException;

/**
 *
 * @author ariel, sidnei
 *
 */
public interface TipoGrafoProvider {

	String tipoDoGrafo(int[][] matrizAdjacencia) throws MatrizAdjacenciaException;
}
