package br.ufop.compEvolucionaria.ES;

public class Aresta {

    private int origem;
    private int destino;

    public Aresta(int origem, int destino) {
            super();
            this.origem = origem;
            this.destino = destino;
    }

    public int getOrigem() {
            return origem;
    }

    public void setOrigem(int origem) {
            this.origem = origem;
    }

    public int getDestino() {
            return destino;
    }

    public void setDestino(int destino) {
            this.destino = destino;
    }

    public String toString() {
            return origem + "-" + destino;
    }
	
}
