package br.furb.grafos.trabalho1.matrizadjacencia;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.furb.grafos.trabalho1.exception.MatrizAdjacenciaException;
import br.furb.grafos.trabalho1.utils.MatrizAdjacenciaUtils;

/**
 *
 * @author ariel, sidnei
 *
 */
public class TipoGrafo {

	private static final String DIRIGIDO = "Dirigido";
	private static final String NAO_DIRIGIDO = "Não dirigido";
	private static final String SIMPLES = "Simples";
	private static final String MULTIGRAFO = "Multigrafo";
	private static final String REGULAR = "Regular";
	private static final String COMPLETO = "Completo";
	private static final String NULO = "Nulo";
	private static final String BIPARTIDO = "Bipartido";

	public String getTipoGrafoPorMatrizAdjacencia(int[][] matrizAdjacencia) throws MatrizAdjacenciaException {
		MatrizAdjacenciaUtils.validarMatrizAdjacencia(matrizAdjacencia);

		boolean isSimples = true;

		boolean isDirigido = isGrafoDirigidoPorMatrizAdjacencia(matrizAdjacencia, false);

		boolean isRegular = true;
		boolean isNulo = true;

		ResultadoBipartido resultadoBipartido = isGrafoBipartidoPorMatrizAdjacencia(matrizAdjacencia);
		boolean isBipartido = resultadoBipartido.isBipartido();
		boolean isCompleto = isBipartido ? resultadoBipartido.isCompleto() : true;

		int valor = 0;
		int somatorioVerticeSaida = 0;
		int somatorioVerticeEntrada = 0;
		int somatorioVerticeSaidaAnterior = 0;
		int somatorioVerticeEntradaAnterior = 0;

		for (int idxLinha = 0; idxLinha < matrizAdjacencia.length; idxLinha++) {
			somatorioVerticeSaida = 0;
			somatorioVerticeEntrada = 0;

			for (int idxColuna = 0; idxColuna < matrizAdjacencia.length; idxColuna++) {
				valor = matrizAdjacencia[idxLinha][idxColuna];
				somatorioVerticeSaida += valor;
				somatorioVerticeEntrada += matrizAdjacencia[idxColuna][idxLinha];

				if (idxColuna == idxLinha) { // Diagonal principal
					if (valor > 0) { // tem valor = loop
						isSimples = false;
					}
				} else if (valor == 0) { // N é diagonal principal e não tem aresta
					if (!isBipartido) {
						isCompleto = false;
					}
				}

				if (valor > 0) { // tem alguma aresta, n�o � nulo
					isNulo = false;
				}

				if (valor > 1) { // Tem aresta paralela, multigrafo
					isSimples = false;
				}
			}

			if (idxLinha > 0  //
					&& ((somatorioVerticeSaidaAnterior != somatorioVerticeSaida) //
							|| (isDirigido && somatorioVerticeEntradaAnterior != somatorioVerticeEntrada))) {
				isRegular = false;
			}
			somatorioVerticeSaidaAnterior = somatorioVerticeSaida;
			somatorioVerticeEntradaAnterior = somatorioVerticeEntrada;
		}

		return getTipoGrafo(isSimples, isDirigido, isRegular, isCompleto, isNulo, isBipartido);
	}

	private ResultadoBipartido isGrafoBipartidoPorMatrizAdjacencia(int[][] matrizAdjacencia) {
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
						return new ResultadoBipartido(false, false);
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

		boolean adjacenteNoGrupo = isAdjacenteNoGrupo(listaAdjacencia, azul) || isAdjacenteNoGrupo(listaAdjacencia, vermelho);
		if (adjacenteNoGrupo) {
			return new ResultadoBipartido(false, false);
		}

		boolean resultado = true;
		for (int idx = 0; idx < azul.length; idx++) {
			lista = listaAdjacencia.get(idx);
			if (lista.isEmpty()) {
				return new ResultadoBipartido(true, false);
			}
			resultado = true;
			if (azul[idx]) {
				resultado = isAdjacentePresenteLista(vermelho, lista);
			} else if (vermelho[idx]) {
				resultado = isAdjacentePresenteLista(azul, lista);
			}

			if (!resultado) {
				return new ResultadoBipartido(true, false);
			}
		}

		return new ResultadoBipartido(true, true);
	}

	private boolean isAdjacenteNoGrupo(Map<Integer, Set<Integer>> listaAdjacencia,	boolean[] grupo) {
		Set<Integer> lista;
		for (int idx = 0; idx < grupo.length; idx++) {
			if (grupo[idx]) {
				lista = listaAdjacencia.get(idx);
				for (int adjacente : lista) {
					if (grupo[adjacente]) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean isAdjacentePresenteLista(boolean[] grupo, Set<Integer> lista) {
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
		StringBuilder sb = new StringBuilder();
		sb.append(isSimples ? SIMPLES : MULTIGRAFO);
		sb.append(", ");
		sb.append(isDirigido ? DIRIGIDO : NAO_DIRIGIDO);

		if (isNulo) {
			sb.append(", ");
			sb.append(NULO);
		} else if (isBipartido) {
			sb.append(", ");
			sb.append(BIPARTIDO);
			if (isCompleto) {
				sb.append(" ");
				sb.append(COMPLETO);
			}
		} else if (isCompleto) {
			sb.append(", ");
			sb.append(COMPLETO);
		} else if (isRegular) {
			sb.append(", ");
			sb.append(REGULAR);
		}

		return sb.toString();
	}

	private boolean isGrafoDirigidoPorMatrizAdjacencia(int[][] matrizAdjacencia, boolean validar) throws MatrizAdjacenciaException {
		if (validar) {
			MatrizAdjacenciaUtils.validarMatrizAdjacencia(matrizAdjacencia);
		}

		for (int idxLinha = 0; idxLinha < matrizAdjacencia.length; idxLinha++) {
			for (int idxColuna = 0; idxColuna < matrizAdjacencia.length; idxColuna++) {
				if (matrizAdjacencia[idxLinha][idxColuna] != matrizAdjacencia[idxColuna][idxLinha]) { // se o inverso for diferente, é dirigido
					return true;
				}
			}
		}

		return false;
	}

	public boolean isGrafoDirigidoPorMatrizAdjacencia(int[][] matrizAdjacencia) throws MatrizAdjacenciaException {
		return isGrafoDirigidoPorMatrizAdjacencia(matrizAdjacencia, true);
	}

	private class ResultadoBipartido {

		boolean bipartido;
		boolean completo;

		public ResultadoBipartido(boolean bipartido, boolean completo) {
			super();
			this.bipartido = bipartido;
			this.completo = completo;
		}

		public boolean isBipartido() {
			return bipartido;
		}

		public boolean isCompleto() {
			return completo;
		}
	}
}
