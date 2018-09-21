package br.furb.grafos.trabalho1.problema;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ariel e sidnei
 */
public class ContextoDuduFazServico {

	private List<CasoDuduFazServico> casos = new ArrayList<>();
	private int quantidadeCasos;

	public int getQuantidadeCasos() {
		return quantidadeCasos;
	}

	public void setQuantidadeCasos(int quantidadeCasos) {
		this.quantidadeCasos = quantidadeCasos;
	}

	public List<CasoDuduFazServico> getCasos() {
		return casos;
	}

}
