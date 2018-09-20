package br.furb.grafos.trabalho1.problema;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import br.furb.grafos.trabalho1.listaadjacencia.BuscaLarguraListaAdjacencia;
import br.furb.grafos.trabalho1.listaadjacencia.ResultadoBuscaLargura;

public class RatoNoLabirinto {

	private static final String ENTRADA = "ENTRADA";
	private static final String SAIDA = "SAIDA";
	private static final String QUEIJO = "*";

	public int resolverProblema(String problema) {
		ContextoRatoNoLabirinto contexto = new ContextoRatoNoLabirinto();

		List<String> linhasProblema = getLinhasProblema(problema);
		definirDadosCabecalho(contexto, linhasProblema);
		definirListaAdjacenciaEVertices(contexto, linhasProblema);
		efetuarBuscaEmLargura(contexto);

		return contexto.getDistancia();
	}

	private void efetuarBuscaEmLargura(ContextoRatoNoLabirinto contexto) {
		Map<String, Set<String>> listaAdjacencia = contexto.getListaAdjacencia();
		Set<String> vertices = contexto.getVertices();

		String entrada = getVerticeEntrada(vertices);
		String saida = getVerticeSaida(vertices);

		BuscaLarguraListaAdjacencia buscaLarguraListaAdjacencia = new BuscaLarguraListaAdjacencia();
		ResultadoBuscaLargura buscaQueijo = buscaLarguraListaAdjacencia.efetuarBuscaLargura(listaAdjacencia, entrada, QUEIJO);
		ResultadoBuscaLargura buscaSaida = buscaLarguraListaAdjacencia.efetuarBuscaLargura(listaAdjacencia, QUEIJO, saida);

		int distanciaQueijo = buscaQueijo.getVerticesBuscaLargura().get(QUEIJO).getDistancia();
		int distanciaSaida = buscaSaida.getVerticesBuscaLargura().get(saida).getDistancia();

		contexto.setDistancia(distanciaQueijo + distanciaSaida);
	}

	private String getVerticeSaida(Set<String> vertices) {
		return vertices.stream().filter(vertice -> SAIDA.equalsIgnoreCase(vertice)).findFirst().orElse(null);
	}

	private String getVerticeEntrada(Set<String> vertices) {
		return vertices.stream().filter(vertice -> ENTRADA.equalsIgnoreCase(vertice)).findFirst().orElse(null);
	}

	private void definirListaAdjacenciaEVertices(ContextoRatoNoLabirinto contexto, List<String> linhasProblema) {
		Set<String> vertices = contexto.getVertices();
		Map<String, Set<String>> listaAdjacencia = contexto.getListaAdjacencia();

		linhasProblema.forEach(linha -> {
			String[] verticesLinha = linha.split(" ");
			String verticeA = verticesLinha[0];
			String verticeB = verticesLinha[1];

			vertices.add(verticeA);
			vertices.add(verticeB);

			adicionarVerticeListaAdjacencia(listaAdjacencia, verticeA, verticeB);
			adicionarVerticeListaAdjacencia(listaAdjacencia, verticeB, verticeA);
		});
	}

	private void adicionarVerticeListaAdjacencia(Map<String, Set<String>> lista, String verticeA, String verticeB) {
		Set<String> set = lista.get(verticeA);
		if (null == set) {
			set = new HashSet<>();
			lista.put(verticeA, set);
		}
		set.add(verticeB);
	}

	private void definirDadosCabecalho(ContextoRatoNoLabirinto contexto, List<String> linhasProblema) {
		String cabecalho = linhasProblema.remove(0);

		String[] parametrosCabecalho = cabecalho.split(" ");
		int vertices = Integer.valueOf(parametrosCabecalho[0]);
		int arestas = Integer.valueOf(parametrosCabecalho[1]);

		contexto.setQuantidadeVertices(vertices);
		contexto.setQuantidadeArestas(arestas);
	}

	private List<String> getLinhasProblema(String problema) {
		return Arrays.stream(problema.split("\n")) //
				.map(String::trim) //
				.collect(Collectors.toList());
	}

}
