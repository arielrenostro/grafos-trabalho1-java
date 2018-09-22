package br.furb.grafos.trabalho1.matrizadjacencia;

import br.furb.grafos.trabalho1.exception.MatrizAdjacenciaException;

/**
 *
 * @author ariel, sidnei
 *
 */
public class OperacaoMatrizAdjacencia {

	public String tipoDoGrafo(int[][] matriz) throws MatrizAdjacenciaException {
		return new TipoGrafo().getTipoGrafoPorMatrizAdjacencia(matriz);
	}

	public String arestasDoGrafo(int[][] matriz) throws MatrizAdjacenciaException {
		return new ArestasGrafo().getArestasGrafoPorMatrizAdjacencia(matriz);
	}

	public String grausDoVertice(int[][] matriz) throws MatrizAdjacenciaException {
		return new GrausVerices().getGrausVerticesPorMatrizAdjacencia(matriz);
	}
}
