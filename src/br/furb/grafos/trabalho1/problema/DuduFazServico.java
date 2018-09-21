package br.furb.grafos.trabalho1.problema;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ariel e sidnei
 */
public class DuduFazServico extends ProblemaBase {

	public String resolverProblema(String problema) {
		ContextoDuduFazServico contexto = new ContextoDuduFazServico();

		List<String> linhasProblema = getLinhasProblema(problema);
		definirDadosCabecalho(contexto, linhasProblema);
		definirCasos(contexto, linhasProblema);
		efetuarBuscaEmProfundidade(contexto);

		return getResultado(contexto);
	}

	private String getResultado(ContextoDuduFazServico contexto) {
		StringBuilder sb = new StringBuilder();

		Iterator<CasoDuduFazServico> iterator = contexto.getCasos().iterator();
		while (iterator.hasNext()) {
			boolean temLoop = iterator.next().isTemLoop();
			sb.append(temLoop);

			if (iterator.hasNext()) {
				sb.append("\n\r");
			}
		}

		return sb.toString();
	}

	private void efetuarBuscaEmProfundidade(ContextoDuduFazServico contexto) {
		List<CasoDuduFazServico> casos = contexto.getCasos();

		for (CasoDuduFazServico caso : casos) {
			Map<Integer, Set<Integer>> casos2 = caso.getCasos();
		}
	}

	private void definirCasos(ContextoDuduFazServico contexto, List<String> linhasProblema) {
		List<CasoDuduFazServico> casos = contexto.getCasos();

		while (!linhasProblema.isEmpty()) {
			CasoDuduFazServico caso = new CasoDuduFazServico();

			String linha = linhasProblema.remove(0);
			String[] linhaSplit = linha.split(" ");

			int quantidadeDependencias = Integer.valueOf(linhaSplit[1]);

			for (int idx = 0; idx < quantidadeDependencias; idx++) {
				linha = linhasProblema.remove(0);
				linhaSplit = linha.split(" ");

				int documento = Integer.valueOf(linhaSplit[0]);
				int dependencia = Integer.valueOf(linhaSplit[1]);

				caso.adicionarCaso(documento, dependencia);
			}

			casos.add(caso);
		}
	}

	private void definirDadosCabecalho(ContextoDuduFazServico contexto, List<String> linhasProblema) {
		String cabecalho = linhasProblema.remove(0);
		int quantidadeCasos = Integer.valueOf(cabecalho);
		contexto.setQuantidadeCasos(quantidadeCasos);
	}
}
