package br.furb.grafos.trabalho1.listaadjacencia;

public class VerticeBuscaLargura {

	private EstadoBusca estadoBusca = EstadoBusca.NAO_ENCONTRADO;
	private VerticeBuscaLargura pai;
	private int distancia;
	private String vertice;

	public EstadoBusca getEstadoBusca() {
		return estadoBusca;
	}

	public void setEstadoBusca(EstadoBusca estadoBusca) {
		this.estadoBusca = estadoBusca;
	}

	public VerticeBuscaLargura getPai() {
		return pai;
	}

	public void setPai(VerticeBuscaLargura pai) {
		this.pai = pai;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public String getVertice() {
		return vertice;
	}

	public void setVertice(String vertice) {
		this.vertice = vertice;
	}

	@Override
	public String toString() {
		return vertice + " [Estado [" + estadoBusca.name() + "], Dist [" + distancia + "], Pai [" + (pai != null ? pai.getVertice() : "") + "]]";
	}
}
