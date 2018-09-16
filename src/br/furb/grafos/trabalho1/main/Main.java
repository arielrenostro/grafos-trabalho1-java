package br.furb.grafos.trabalho1.main;

import br.furb.grafos.trabalho1.producer.TipoGrafoProducer;
import br.furb.grafos.trabalho1.producer.TipoGrafoProducer.TipoAcaoBipartido;

/**
 *
 * @author ariel, sidnei
 *
 */
public class Main {

	public static void main(String[] args) {
		TipoGrafoProducer tipoGrafoProvider = new TipoGrafoProducer();

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

		System.out.println(tipoGrafoProvider.isGrafoBipartido(matrizAdjacencia, TipoAcaoBipartido.COMPLETO));
		System.out.println(tipoGrafoProvider.isGrafoBipartido(matrizAdjacencia, TipoAcaoBipartido.BIPARTIDO));

		matrizAdjacencia = new int[6][6];
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

		System.out.println(tipoGrafoProvider.isGrafoBipartido(matrizAdjacencia, TipoAcaoBipartido.COMPLETO));
		System.out.println(tipoGrafoProvider.isGrafoBipartido(matrizAdjacencia, TipoAcaoBipartido.BIPARTIDO));
	}

}
