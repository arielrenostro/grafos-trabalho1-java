package br.furb.grafos.trabalho1.matrizadjacencia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import br.furb.grafos.trabalho1.exception.MatrizAdjacenciaException;
import br.furb.grafos.trabalho1.utils.MatrizAdjacenciaUtils;

/**
 *
 * @author ariel, sidnei
 *
 */
public class GrausVerices {

	public String getGrausVerticesPorMatrizAdjacencia(int[][] matrizAdjacencia) throws MatrizAdjacenciaException {
		boolean isDirigido = new TipoGrafo().isGrafoDirigidoPorMatrizAdjacencia(matrizAdjacencia);

		if (isDirigido) {
			return getGrausVerticesPorMatrizAdjacenciaGrafoDirigido(matrizAdjacencia);
		} else {
			return getGrausVerticesPorMatrizAdjacenciaGrafoNaoDirigido(matrizAdjacencia);
		}
	}

	private String getGrausVerticesPorMatrizAdjacenciaGrafoNaoDirigido(int[][] matrizAdjacencia) throws MatrizAdjacenciaException {
		Map<Integer, Integer> graus = new HashMap<>();

		int[] colunas = null;
		int grau = 0;
		for (int idxLinha = 0; idxLinha < matrizAdjacencia.length; idxLinha++) {
			colunas = matrizAdjacencia[idxLinha];
			grau = 0;
			for (int idxColuna = 0; idxColuna < colunas.length; idxColuna++) {
				grau += (idxColuna == idxLinha) ? colunas[idxColuna] * 2 : colunas[idxColuna];
			}

			graus.put(idxLinha, grau);
		}

		StringBuilder sb = new StringBuilder();
		String[] nomesVertices = MatrizAdjacenciaUtils.getNomesVerticesMatrizAdjacencia(matrizAdjacencia);
		Iterator<Entry<Integer, Integer>> iterator = graus.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> entry = iterator.next();

			sb.append(nomesVertices[entry.getKey()]);
			sb.append(" = ");
			sb.append(entry.getValue());

			if (iterator.hasNext()) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	private String getGrausVerticesPorMatrizAdjacenciaGrafoDirigido(int[][] matrizAdjacencia) throws MatrizAdjacenciaException {
		Map<Integer, Integer> grausEntrada = new HashMap<>();
		Map<Integer, Integer> grausSaida = new HashMap<>();

		int grauSaida = 0;
		int grauEntrada = 0;
		for (int idx1 = 0; idx1 < matrizAdjacencia.length; idx1++) {
			grauSaida = 0;
			grauEntrada = 0;
			for (int idx2 = 0; idx2 < matrizAdjacencia.length; idx2++) {
				grauSaida += matrizAdjacencia[idx1][idx2];
				grauEntrada += matrizAdjacencia[idx2][idx1];
			}
			grausSaida.put(idx1, grauSaida);
			grausEntrada.put(idx1, grauEntrada);
		}

		StringBuilder sb = new StringBuilder();
		String[] nomesVertices = MatrizAdjacenciaUtils.getNomesVerticesMatrizAdjacencia(matrizAdjacencia);
		for (int idx = 0; idx < matrizAdjacencia.length; idx++) {
			sb.append(nomesVertices[idx]);
			sb.append(" = E(");
			sb.append(grausEntrada.containsKey(idx) ? grausEntrada.get(idx) : 0);
			sb.append("), S(");
			sb.append(grausSaida.containsKey(idx) ? grausSaida.get(idx) : 0);
			sb.append(")\n");
		}
		return sb.delete(sb.length() - 1, sb.length()).toString();
	}

}
