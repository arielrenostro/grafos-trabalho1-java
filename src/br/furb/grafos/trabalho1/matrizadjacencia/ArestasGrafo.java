package br.furb.grafos.trabalho1.matrizadjacencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.furb.grafos.trabalho1.exception.MatrizAdjacenciaException;
import br.furb.grafos.trabalho1.utils.MatrizAdjacenciaUtils;

/**
 *
 * @author ariel, sidnei
 *
 */
public class ArestasGrafo  {

	public String getArestasGrafoPorMatrizAdjacencia(int[][] matrizAdjacencia) throws MatrizAdjacenciaException {
		String[] nomes = MatrizAdjacenciaUtils.getNomesVerticesMatrizAdjacencia(matrizAdjacencia);

		boolean dirigido = new TipoGrafo().isGrafoDirigidoPorMatrizAdjacencia(matrizAdjacencia);
		List<String> listaNormal = new ArrayList<>();
		//List<String> listaDirigido = new ArrayList<>();
		//TODO caso queira dividir nos n dirigidos, fazer aqui
		int linha = 0;
		int coluna = coluna = 0;
		int qntArestas = 0;
		if (dirigido) {
			for (linha = 0; linha <= matrizAdjacencia.length; linha++) {
				for (coluna = 0; coluna <= matrizAdjacencia.length; coluna++) {
					if (matrizAdjacencia[coluna][linha] != 0) {
						qntArestas += matrizAdjacencia[coluna][linha];
						listaNormal.add(nomes[linha] + "," + nomes[coluna]);
					}
				}
			}
		} else {
			for (; linha <= matrizAdjacencia.length; linha++) {
				for (coluna = 0; coluna <= matrizAdjacencia.length; coluna++) {
					if (linha > coluna) {
						coluna = linha;
					}
					if (matrizAdjacencia[coluna][linha] != 0) {
						qntArestas += matrizAdjacencia[coluna][linha];
						listaNormal.add(nomes[linha] + "," + nomes[coluna]);
						listaNormal.add(nomes[coluna] + "," + nomes[linha]);
						//listaDirigido.add(nomes[coluna] + "," + nomes[linha]);
						//TODO caso queira dividir nos n dirigidos, fazer aqui
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("O número de arestas deste grafo é: ");
		sb.append(qntArestas);
		sb.append("\n");
		sb.append("Os conjuntos encontrados no grafo são: ");
		sb.append("[");
		Iterator<String> iterator = listaNormal.iterator();
		while (iterator.hasNext()) {
			String s = iterator.next();
			sb.append("(");
			sb.append(s);
			sb.append(")");
			if (iterator.hasNext()) {
				sb.append(s);
			}
		}
		sb.append("]");
		return sb.toString();//TODO Alterar
	}
}