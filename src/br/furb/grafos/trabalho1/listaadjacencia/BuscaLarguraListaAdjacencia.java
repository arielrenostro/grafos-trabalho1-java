package br.furb.grafos.trabalho1.listaadjacencia;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class BuscaLarguraListaAdjacencia {

	public ResultadoBuscaLargura efetuarBuscaLargura(Map<String, Set<String>> listaAdjacencia, String primeiroVertice, String verticeParada) {
		ResultadoBuscaLargura resultadoBuscaLargura = new ResultadoBuscaLargura();

		Map<String, VerticeBuscaLargura> verticesBuscaLargura = resultadoBuscaLargura.getVerticesBuscaLargura();

		LinkedList<String> lista = new LinkedList<>();
		lista.add(primeiroVertice);

		while (!lista.isEmpty()) {
			String vertice = lista.remove(0);
			VerticeBuscaLargura verticeBuscaLargura = getVerticeBuscaLargura(verticesBuscaLargura, vertice);

			Set<String> adjacentes = listaAdjacencia.get(vertice);
			if (null == adjacentes) {
				continue;
			}
			for (String adjacente : adjacentes) {
				VerticeBuscaLargura adjacenteVerticeBuscaLargura = getVerticeBuscaLargura(verticesBuscaLargura, adjacente);
				if (EstadoBusca.NAO_ENCONTRADO.equals(adjacenteVerticeBuscaLargura.getEstadoBusca())) {
					lista.add(adjacenteVerticeBuscaLargura.getVertice());

					adjacenteVerticeBuscaLargura.setEstadoBusca(EstadoBusca.ENCONTRADO);
					adjacenteVerticeBuscaLargura.setPai(verticeBuscaLargura);
					adjacenteVerticeBuscaLargura.setDistancia(verticeBuscaLargura.getDistancia() + 1);

					if (null != verticeParada && verticeParada.equals(adjacente)) {
						return resultadoBuscaLargura;
					}
				}
			}

			verticeBuscaLargura.setEstadoBusca(EstadoBusca.FECHADO);
		}

		return resultadoBuscaLargura;
	}

	private VerticeBuscaLargura getVerticeBuscaLargura(Map<String, VerticeBuscaLargura> verticesBuscaLargura, String vertice) {
		VerticeBuscaLargura verticeBuscaLargura = verticesBuscaLargura.get(vertice);
		if (null == verticeBuscaLargura) {
			verticeBuscaLargura = new VerticeBuscaLargura();
			verticeBuscaLargura.setVertice(vertice);

			verticesBuscaLargura.put(vertice, verticeBuscaLargura);
		}
		return verticeBuscaLargura;
	}

}
