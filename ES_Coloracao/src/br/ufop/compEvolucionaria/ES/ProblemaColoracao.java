package br.ufop.compEvolucionaria.ES;

import java.util.ArrayList;


public class ProblemaColoracao implements Problema {

    private Integer nVariaveis;

    public ProblemaColoracao() {
    }
        
    @Override
    public void calcularFuncaoObjetivo(Individuo individuo) {
	    //minimizar - cada conflito aumenta a funcao e o tamanho também
    	int f = 0;
        f = listaCores(individuo.getCromossomos()).size()*100;
       
        individuo.setFuncaoObjetivo(f);
        
    }

	public ArrayList<Integer> listaCores(ArrayList<Integer> lista) {
	    ArrayList<Integer> listaCores = new ArrayList<>();
	    for (int i = 0; i < lista.size(); i++) {
	        int temp = lista.get(i);
	        if (!listaCores.contains(temp)) {
	            listaCores.add((Integer) temp);
	        }
	    }
	    return listaCores;
	} 

    @Override
    public int getDimensao() {
        return this.nVariaveis;
    }
    
}