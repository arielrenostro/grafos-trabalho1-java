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
		int qntArestas = 0;
		if (dirigido) {
			for (int linha = 0; linha < matrizAdjacencia.length; linha++) {
				for (int coluna = 0; coluna < matrizAdjacencia.length; coluna++) {
					int valor = matrizAdjacencia[coluna][linha];
					if (valor != 0) {
						qntArestas += valor;
						for (int idxValor = 0; idxValor < valor; idxValor++) {
							listaNormal.add(nomes[coluna] + "," + nomes[linha]);
						}
					}
				}
			}
		} else {
			for (int linha = 0; linha < matrizAdjacencia.length; linha++) {
				for (int coluna = 0; coluna < matrizAdjacencia.length; coluna++) {
					if (linha > coluna) {
						coluna = linha;
					}
					int valor = matrizAdjacencia[coluna][linha];
					if (valor != 0) {
						qntArestas += valor;
						for (int idxValor = 0; idxValor < valor; idxValor++) {
							listaNormal.add(nomes[linha] + "," + nomes[coluna]);
							listaNormal.add(nomes[coluna] + "," + nomes[linha]);
						}
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
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
