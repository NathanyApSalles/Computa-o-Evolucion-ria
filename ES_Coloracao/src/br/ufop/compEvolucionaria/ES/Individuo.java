package br.ufop.compEvolucionaria.ES;

import java.util.ArrayList;
import java.util.Random;

public interface Individuo<T> extends Comparable<Individuo> {
    
    Integer getFuncaoObjetivo();
    void setFuncaoObjetivo(Integer funcaoObjetivo);
    
    ArrayList<T> getCromossomos();
    void setCromossomos(ArrayList<T> cromossomos);
    
    void criar();
    Individuo<T> clone();
    
}
