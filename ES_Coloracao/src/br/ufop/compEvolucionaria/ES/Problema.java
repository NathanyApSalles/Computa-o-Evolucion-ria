package br.ufop.compEvolucionaria.ES;

import java.util.ArrayList;


public interface Problema {
	 
    void calcularFuncaoObjetivo(Individuo individuo);
    int getDimensao();
	ArrayList<Integer> listaCores(ArrayList<Integer> s);
    
}
