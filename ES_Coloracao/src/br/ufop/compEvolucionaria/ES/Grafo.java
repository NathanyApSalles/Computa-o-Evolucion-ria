package br.ufop.compEvolucionaria.ES;

import java.util.ArrayList;


public class Grafo {
	
	public ArrayList<Integer> vertices;
	public ArrayList<Aresta> arestas;
	public ArrayList<ArrayList<Aresta>> listaAdj;
	
	public Grafo(ArrayList<Integer> vertices, ArrayList<Aresta> arestas,
			ArrayList<ArrayList<Aresta>> listaAdj) {
		super();
		this.vertices = vertices;
		this.arestas = arestas;
		this.listaAdj = listaAdj;
	}
	
	public Grafo(){}
	public ArrayList<Integer> getVertices() {
		return vertices;
	}
	public void setVertices(ArrayList<Integer> vertices) {
		this.vertices = vertices;
	}
	public ArrayList<Aresta> getArestas() {
		return arestas;
	}
	public void setArestas(ArrayList<Aresta> arestas) {
		this.arestas = arestas;
	}
	public ArrayList<ArrayList<Aresta>> getListaAdj() {
		return listaAdj;
	}
	public void setListaAdj(ArrayList<ArrayList<Aresta>> listaAdj) {
		this.listaAdj = listaAdj;
	}
	
}

