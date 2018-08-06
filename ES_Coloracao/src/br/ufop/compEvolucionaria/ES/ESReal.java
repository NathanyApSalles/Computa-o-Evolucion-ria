package br.ufop.compEvolucionaria.ES;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class ESReal implements Metodo {

    private Problema problema;
    private Grafo grafo;
    // Parametros - ES
    private Integer mu; // Tamanho da populacao
    private Integer lambda; // numero de descendentes
    private Integer geracoes; // criterio de parada
    private Double pMutacao; // mutacao - aplicacao ao descendente - variacao/perturbacao

    public ESReal(Problema problema, Integer mu, Integer lambda, Integer geracoes, Double pMutacao, Grafo grafo) {
    
        this.problema = problema;
        this.mu = mu;
        this.lambda = lambda;
        this.geracoes = geracoes;
        this.pMutacao = pMutacao;
        this.grafo = grafo;
    }
    
    @Override
    public Individuo executar() {
        
        // Geracao da populacao inicial - aleatoria - tamanho mu
    	
        PopulacaoCores populacao = new PopulacaoCores(problema, this.grafo, mu);     
        populacao.criar();
        
        // Avaliar
        populacao.avaliar();
        
        // Populacao - descendentes
        PopulacaoCores descendentes = new PopulacaoCores();
        
        // Criterio de parada - numero de geracoes
        for(int g = 1; g <= this.geracoes; g++) {
            
            // Para cada individuo, gerar lambda/mu descendentes
            for(int i = 0; i < populacao.getIndividuos().size(); i++) {
                
                // Gerar lambda/mu descendentes
                for(int d = 0; d < lambda / mu; d++) {
                    
                    // Clonar individuo
                    IndividuoCores filho = (IndividuoCores) populacao.getIndividuos().get(i).clone();
                    
                    // Aplicar mutacao
                    mutacaoPorVariavel(filho);
                    
                    // Avaliar
                    problema.calcularFuncaoObjetivo(filho);
                    
                    // Inserir na populacao de descendentes
                    descendentes.getIndividuos().add(filho);
                    
                }
                
            }
            
            // ES(mu, lambda)?
            // Eliminar a populacao corrente
            //populacao.getIndividuos().clear();
            
            // ES(mu + lambda)?
            // Mu + Lambda
            populacao.getIndividuos().addAll(descendentes.getIndividuos());
            // Ordenar Mu+Lambda
            Collections.sort(populacao.getIndividuos());
            // Definir sobreviventes
            populacao.getIndividuos().subList(this.mu, populacao.getIndividuos().size()).clear();
            // Limpar descendentes
            descendentes.getIndividuos().clear();
       
            
            
        }
        
      // Retornar o melhor individuo
      return populacao.getMelhorIndividuo();
        
    }
    
    private void mutacaoPorVariavel(Individuo individuo) {

    	Random rnd = new Random();
           
        if (rnd.nextDouble() <= this.pMutacao) {
        	
        	individuo.setCromossomos(encontraVizinho(individuo.getCromossomos()));
        
        }
    }
    
    public ArrayList<Integer> encontraVizinho(ArrayList<Integer> s){
        ArrayList<Integer> s1; 
    	Random rand = new Random();
        ArrayList<Integer> cores = (ArrayList<Integer>) problema.listaCores(s);
        //sotear um vértice
        int vertice = rand.nextInt(grafo.vertices.size());
        //sortear uma cor
        int cor = rand.nextInt(cores.size());
        int c = cores.get(cor);        
        s1 = (ArrayList<Integer>) s.clone();
        int cont = 0;
        // confiro a lista de adjacência para saber se tem conflito
        for (int j = 0; j < grafo.listaAdj.get(vertice).size(); j++) {
        	int adjacente = grafo.listaAdj.get(vertice).get(j).getDestino();
            if(s.get(adjacente) == c){
            	cont++;
                break;
            }
        }
        //se não tiver adj com a mesma cor, então a cor pode ser add ao vértice
        if (cont == 0) {
            s1.set(vertice, c);
        }
        return s1;
    }

		
    
}