package br.ufop.compEvolucionaria.ES;

import java.util.ArrayList;


public class PopulacaoCores extends Populacao<Integer> {

       
    Grafo grafo;
    

    public PopulacaoCores() {
        this.individuos = new ArrayList<>();
    }
    
    public PopulacaoCores(Problema problema, Grafo grafo, Integer tamanho) {
    	
        this.problema = problema;
        this.grafo = grafo;
        this.tamanho = tamanho;
    }
    
    @Override
    public void criar() {
        individuos = new ArrayList<>();
        
        for (int i = 0; i < this.getTamanho(); i++) {
            Individuo individuo = new IndividuoCores(grafo);
            individuo.criar();
            individuos.add(individuo);
            
            
        }        
    }
    
    
}
