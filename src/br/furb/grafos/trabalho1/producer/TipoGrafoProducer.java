package br.furb.grafos.trabalho1.producer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.furb.grafos.trabalho1.exception.MatrizAdjacenciaException;
import br.furb.grafos.trabalho1.provider.TipoGrafoProvider;
import br.furb.grafos.trabalho1.utils.GrafosUtils;

/**
 *
 * @author ariel, sidnei
 *
 */
public class TipoGrafoProducer implements TipoGrafoProvider {

	public static final String DIRIGIDO = "Dirigido";
	public static final String NAO_DIRIGIDO = "Não dirigido";
	public static final String SIMPLES = "Simples";
	public static final String MULTIGRAFO = "Multigrafo";
	public static final String REGULAR = "Regular";
	public static final String COMPLETO = "Completo";
	public static final String NULO = "Nulo";
	public static final String BIPARTIDO = "Bi-partido";

	@Override
	public String tipoDoGrafo(int[][] matrizAdjacencia) throws MatrizAdjacenciaException {
		GrafosUtils.validarMatrizAdjacencia(matrizAdjacencia);

		boolean isSimples = true;

		boolean isDirigido = false;

		boolean isRegular = true;
		boolean isNulo = true;
		boolean isBipartido = isGrafoBipartido(matrizAdjacencia, TipoAcaoBipartido.BIPARTIDO);
		boolean isCompleto = isBipartido ? isGrafoBipartido(matrizAdjacencia, TipoAcaoBipartido.COMPLETO) : true;

		int[] colunas = null;
		int valor = 0;
		int valorAux = 0;
		int somatorioVertice = 0;
		int somatorioVerticeAnterior = 0;

		for (int idxLinha = 0; idxLinha < matrizAdjacencia.length; idxLinha++) {
			colunas = matrizAdjacencia[idxLinha];
			somatorioVertice = 0;

			for (int idxColuna = 0; idxColuna < colunas.length; idxColuna++) {
				valor = colunas[idxColuna];
				somatorioVertice += valor;

				if (idxColuna == idxLinha) { // Diagonal principal
					if (valor > 0) { // tem valor = loop
						isSimples = false;
					}
				} else if (valor == 0) { // Não é principal e não tem aresta a outro vertice
					if (!isBipartido) {
						isCompleto = false;
					}
				}

				if (valor > 0) { // tem alguma aresta, não é nulo
					isNulo = false;
				}

				if (valor > 1) { // Tem aresta paralela, multigrafo
					isSimples = false;
				}

				if (!isDirigido) {
					valorAux = matrizAdjacencia[idxColuna][idxLinha];
					if (valor != valorAux) { // se o inverso for diferente, é dirigido
						isDirigido = true;
					}
				}
			}

			if (idxLinha > 0 && somatorioVerticeAnterior != somatorioVertice) {
				isRegular = false;
			}
			somatorioVerticeAnterior = somatorioVertice;
		}

		return getTipoGrafo(isSimples, isDirigido, isRegular, isCompleto, isNulo, isBipartido);
	}

	public boolean isGrafoBipartido(int[][] matrizAdjacencia, TipoAcaoBipartido acao) {
		Map<Integer, Set<Integer>> listaAdjacencia = getListaIncidenciaPorMatrizAdjacencia(matrizAdjacencia);
		Map<Integer, Set<Integer>> listaAdjacenciaParaVerificacao = new HashMap<>(listaAdjacencia);

		boolean[] azul = new boolean[matrizAdjacencia.length];
		boolean[] vermelho = new boolean[matrizAdjacencia.length];

		int valor = 0;
		Set<Integer> lista = null;
		Set<Entry<Integer, Set<Integer>>> entryListaAdjacencia = null;
		boolean primeiro = true;
		while (true) {
			if (listaAdjacenciaParaVerificacao.isEmpty()) {
				break;
			}

			primeiro = true;
			entryListaAdjacencia = new HashSet<>(listaAdjacenciaParaVerificacao.entrySet());

			for (Entry<Integer, Set<Integer>> entry : entryListaAdjacencia) {
				valor = entry.getKey();
				lista = entry.getValue();

				if (lista.isEmpty()) {
					listaAdjacenciaParaVerificacao.remove(valor);
					continue;
				}

				if (primeiro) {
					azul[valor] = true;
					primeiro = false;
				}

				for (int adjacente : lista) {
					if ((valor == adjacente) || (azul[valor] && azul[adjacente]) || (vermelho[valor] && vermelho[adjacente])) {
						return false;
					} else if (azul[valor]) {
						vermelho[adjacente] = true;
						listaAdjacenciaParaVerificacao.remove(valor);
					} else if (vermelho[valor]) {
						azul[adjacente] = true;
						listaAdjacenciaParaVerificacao.remove(valor);
					}
				}
			}
		}

		if (TipoAcaoBipartido.COMPLETO.equals(acao)) {
			boolean resultado = true;
			for (int idx = 0; idx < azul.length; idx++) {
				lista = listaAdjacencia.get(idx);
				resultado = true;
				if (azul[idx]) {
					resultado = isAdjacentePresente(vermelho, lista);
				} else if (vermelho[idx]) {
					resultado = isAdjacentePresente(azul, lista);
				}

				if (!resultado) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isAdjacentePresente(boolean[] grupo, Set<Integer> lista) {
		for (int idx = 0; idx < grupo.length; idx++) {
			if (grupo[idx] && !lista.contains(idx)) {
				return false;
			}
		}
		return true;
	}

	private Map<Integer, Set<Integer>> getListaIncidenciaPorMatrizAdjacencia(int[][] matrizAdjacencia) {
		Map<Integer, Set<Integer>> listaAjacencia = new HashMap<>();

		int[] colunas = null;
		int valor = 0;
		Set<Integer> lista = null;
		for (int idxLinha = 0; idxLinha < matrizAdjacencia.length; idxLinha++) {
			colunas = matrizAdjacencia[idxLinha];
			lista = new HashSet<>();
			for (int idxColuna = 0; idxColuna < colunas.length; idxColuna++) {
				valor = colunas[idxColuna];
				if (valor > 0) {
					lista.add(idxColuna);
				}
			}
			listaAjacencia.put(idxLinha, lista);
		}
		return listaAjacencia;
	}

	private String getTipoGrafo(boolean isSimples, boolean isDirigido, boolean isRegular, boolean isCompleto, boolean isNulo, boolean isBipartido) {
		return null;
	}

	public enum TipoAcaoBipartido {
		COMPLETO, BIPARTIDO;
	}

}
