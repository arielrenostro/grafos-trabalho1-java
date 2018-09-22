package br.furb.grafos.trabalho1.problema;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CasoDuduFazServico {

	private final Map<Integer, Set<Integer>> dependenciasPorDocumento = new HashMap<>();

	private boolean temLoop = false;

	public void adicionarCaso(Integer documento, Integer dependencia) {
		Set<Integer> dependencias = dependenciasPorDocumento.get(documento);
		if (null == dependencias) {
			dependencias = new HashSet<>();
			dependenciasPorDocumento.put(documento, dependencias);
		}
		dependencias.add(dependencia);
	}

	public boolean isTemLoop() {
		return temLoop;
	}

	public void setTemLoop(boolean temLoop) {
		this.temLoop = temLoop;
	}

	public Map<Integer, Set<Integer>> getDependenciasPorDocumento() {
		return dependenciasPorDocumento;
	}

}
