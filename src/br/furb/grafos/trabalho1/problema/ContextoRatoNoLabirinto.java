package br.furb.grafos.trabalho1.problema;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ariel e sidnei
 */
public class ContextoRatoNoLabirinto {

	private Map<String, Set<String>> listaAdjacencia = new HashMap<>();
	private Set<String> vertices = new HashSet<>();

	private int quantidadeVertices;
	private int quantidadeArestas;

	private int distancia;

	public int getQuantidadeVertices() {
		return quantidadeVertices;
	}

	public void setQuantidadeVertices(int quantidadeVertices) {
		this.quantidadeVertices = quantidadeVertices;
	}

	public int getQuantidadeArestas() {
		return quantidadeArestas;
	}

	public void setQuantidadeArestas(int quantidadeArestas) {
		this.quantidadeArestas = quantidadeArestas;
	}

	public Set<String> getVertices() {
		return vertices;
	}

	public Map<String, Set<String>> getListaAdjacencia() {
		return listaAdjacencia;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

}
