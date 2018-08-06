package br.ufop.compEvolucionaria.ES;

import java.util.ArrayList;
import java.util.Random;

public class IndividuoCores implements Individuo<Integer> {

    // Genótipo+Fenotipo
    private ArrayList<Integer> cromossomos; // cores
    // Custo da função objetivo
    Integer funcaoObjetivo;
    Grafo grafo;

    // Número de vértices
    Integer nVertices;

    public IndividuoCores(Grafo grafo) {
        this.grafo = grafo;
        this.cromossomos = new ArrayList<>();
    }

    @Override
    public ArrayList<Integer> getCromossomos() {
        return cromossomos;
    }

    @Override
    public void setCromossomos(ArrayList<Integer> cromossomos) {
        this.cromossomos = cromossomos;
    }

    @Override
    public Integer getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    @Override
    public void setFuncaoObjetivo(Integer funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

   public Integer nVertices() {
        return nVertices;
    }

    public void nVertices(Integer nVertices) {
        this.nVertices = nVertices;
    }

    // Gerar o genótipo
    @Override
    public void criar() {
       
        for(int i = 0; i < grafo.vertices.size(); i++){
                this.cromossomos.add(i);
        }
    }

    @Override
    public int compareTo(Individuo o) {
        return this.getFuncaoObjetivo().compareTo(o.getFuncaoObjetivo());
    }

    @Override
    public Individuo<Integer> clone() {
        Individuo individuo = null;
        individuo = new IndividuoCores(this.grafo);
        individuo.setCromossomos(new ArrayList<>(this.getCromossomos()));
        individuo.setFuncaoObjetivo(this.getFuncaoObjetivo());
        return individuo;
    }

    @Override
    public String toString() {
        return "Individuo:" + cromossomos + "\nCusto:" + funcaoObjetivo + "\nQuantidade de cores:" + funcaoObjetivo/100;
    }
    
}