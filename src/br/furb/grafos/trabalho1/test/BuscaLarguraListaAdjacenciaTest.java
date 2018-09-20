package br.furb.grafos.trabalho1.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import br.furb.grafos.trabalho1.listaadjacencia.BuscaLarguraListaAdjacencia;

/**
 * @author ariel e sidnei
 */
public class BuscaLarguraListaAdjacenciaTest {

	@Test
	public void teste01() {
		Map<String, Set<String>> lista = new HashMap<>();
		adicionarValorLista(lista, "v", "r");
		adicionarValorLista(lista, "r", "s");
		adicionarValorLista(lista, "s", "w");
		adicionarValorLista(lista, "w", "t");
		adicionarValorLista(lista, "w", "x");
		adicionarValorLista(lista, "t", "x");
		adicionarValorLista(lista, "t", "u");
		adicionarValorLista(lista, "x", "y");
		adicionarValorLista(lista, "u", "y");

		adicionarValorLista(lista, "r", "v");
		adicionarValorLista(lista, "s", "r");
		adicionarValorLista(lista, "w", "s");
		adicionarValorLista(lista, "t", "w");
		adicionarValorLista(lista, "x", "w");
		adicionarValorLista(lista, "x", "t");
		adicionarValorLista(lista, "u", "t");
		adicionarValorLista(lista, "y", "x");
		adicionarValorLista(lista, "y", "u");

		BuscaLarguraListaAdjacencia buscaLarguraListaAdjacencia = new BuscaLarguraListaAdjacencia();
		buscaLarguraListaAdjacencia.efetuarBuscaLargura(lista, "s", null);

	}

	private void adicionarValorLista(Map<String, Set<String>> lista, String verticeA, String verticeB) {
		Set<String> adjacentes = lista.get(verticeA);
		if (null == adjacentes) {
			adjacentes = new HashSet<>();
			lista.put(verticeA, adjacentes);
		}
		adjacentes.add(verticeB);
	}
}
