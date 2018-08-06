package br.ufop.computacaoEvo;


public interface Problema {
	 
    void calcularFuncaoObjetivo(Individuo individuo);
    int getDimensao();
    
}

