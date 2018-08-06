package testes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import dois_pontos.AlgoritmoGenetico;
import dois_pontos.Individuo;
import dois_pontos.Populacao;

public class AGTeste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		blend.Problema problemaBlend = new blend.Problema();
		dois_pontos.Problema problemaDois_pontos = new dois_pontos.Problema();
		
		dois_pontos.Populacao populacaoDois_pontos = new dois_pontos.Populacao();
		blend.Populacao populacaoBlend = new blend.Populacao();
		
		Integer tamanho = 50;
        Double pCrossover;
        Double pMutacao;
        Integer geracoes = 300;
        
        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;
        Double mediaD = 0.0;
        Double mediaB = 0.0;
        
        ArrayList<Individuo> melhorDois_pontos = new ArrayList<>();
        ArrayList<Individuo> piorDois_pontos = new ArrayList<>();
        ArrayList<blend.Individuo> melhorBlend = new ArrayList<>();
        ArrayList<blend.Individuo> piorBlend = new ArrayList<>();
        
        ArrayList<Integer> execucoes = new ArrayList<>();
        ArrayList<Double> blend = new ArrayList<>();
        ArrayList<Double> dois_pontos = new ArrayList<>();
        ArrayList<Double> tempoBlend = new ArrayList<>();
        ArrayList<Double> tempoDois_pontos = new ArrayList<>();
        ArrayList<Populacao> popDois = new ArrayList<>();
        ArrayList<blend.Populacao> popBlend = new ArrayList<>();
        
        int repeticoes = 60;
        
        for (int i = 1; i <= repeticoes; i++) {
        	
        	execucoes.add(i);         	
			
		}
        
        Collections.shuffle(execucoes);
        System.out.println(execucoes.toString());
        
        
        for (int i = 0; i < repeticoes; i++) {
        	
        	
        	
        	Double result = 0.0;
        	
        	String metodo = "";
        	
        	blend.AlgoritmoGenetico agBlend;
        	dois_pontos.AlgoritmoGenetico agDois_pontos;
            
        	long startTime = System.currentTimeMillis();
            
        	if(execucoes.get(i)%2 == 0){
        		metodo = "dois_pontos";
        		pCrossover = 0.1;
                pMutacao = 0.01;
                
        		agDois_pontos = new dois_pontos.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problemaDois_pontos, minimo, maximo, nVariaveis);
                populacaoDois_pontos = agDois_pontos.executar();
                popDois.add(populacaoDois_pontos);
                result = populacaoDois_pontos.getIndividuos().get(0).getFuncaoObjetivo();
                mediaD += result;
                melhorDois_pontos.add(populacaoDois_pontos.getIndividuos().get(0));
                piorDois_pontos.add(populacaoDois_pontos.getIndividuos().get(populacaoDois_pontos.getIndividuos().size()-1));
                
                dois_pontos.add(result);
            	
            }else{
            	metodo = "blend";
            	pCrossover = 0.8;
            	pMutacao = 0.05;
            	
            	agBlend = new blend.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problemaBlend, minimo, maximo, nVariaveis);                
                populacaoBlend = agBlend.executar();
                popBlend.add(populacaoBlend);
                result = populacaoBlend.getIndividuos().get(0).getFuncaoObjetivo();
                mediaB += result;
                melhorBlend.add(populacaoBlend.getIndividuos().get(0));
                piorBlend.add(populacaoBlend.getIndividuos().get(populacaoBlend.getIndividuos().size()-1));
                
                blend.add(result);
            }
        	 long endTime = System.currentTimeMillis();
             long totalTime = endTime - startTime;

             System.out.println( "***************\nmetodo: " + metodo + " repetição: " + i + " resultado: " + result + " tempo de execução: " + totalTime + "\n***************\n");
            
             if(metodo.equals("dois_pontos")){
            	 tempoDois_pontos.add((double) totalTime);
            	
            }else{
            	tempoBlend.add((double) totalTime);
            }
               	
			
		}	
         
        System.out.println("************************ BLEND ************************");
        System.out.println("Blend: " + blend.toString());
        System.out.println("Tempo blend: " + tempoBlend.toString());
        double melhorCustoB = blend.get(0);
        int melhorB = 0;
        for (int j = 0; j < blend.size(); j++) {
        	if(blend.get(j)< melhorCustoB){
        		melhorCustoB = blend.get(j);
        		melhorB = j;
        	}
			
		}
        double piorCustoB = blend.get(0);
        int piorB = 0;
        for (int j = 0; j < blend.size(); j++) {
        	if(blend.get(j)> piorCustoB){
        		piorCustoB = blend.get(j);
        		piorB = j;
        	}
			
		}

        System.out.println("Média: " + mediaB/30);
        System.out.println("Melhor individuo: " + melhorBlend.get(melhorB).getVariaveis().toString());
        System.out.println("Pior individuo: " + piorBlend.get(piorB).getVariaveis().toString());  
        Collections.sort(blend);
        System.out.println("Melhor custo: " + blend.get(0));
        System.out.println("Pior custo: " + blend.get(blend.size()-1));
       
        
        
        System.out.println("\n\n************************ DOIS_PONTOS ************************");
        System.out.println("Dois_pontos: " + dois_pontos.toString());
        System.out.println("Tempo Dois_pontos: " + tempoDois_pontos.toString());
        double melhorCustoD = dois_pontos.get(0);
        int melhorD = 0;
        for (int j = 0; j < dois_pontos.size(); j++) {
        	if(dois_pontos.get(j)< melhorCustoD){
        		melhorCustoD = dois_pontos.get(j);
        		melhorD = j;
        	}
			
		}
        double piorCustoD = dois_pontos.get(0);
        int piorD = 0;
        for (int j = 0; j < dois_pontos.size(); j++) {
        	if(dois_pontos.get(j)> piorCustoD){
        		piorCustoD = dois_pontos.get(j);
        		piorD = j;
        	}
			
		}
        System.out.println("Média: " + mediaD/30);
        System.out.println("Melhor individuo: " + melhorDois_pontos.get(melhorD).getVariaveis().toString());
        System.out.println("Pior individuo: " + piorDois_pontos.get(piorD).getVariaveis().toString());  
        Collections.sort(dois_pontos);
        System.out.println("Melhor custo: " + dois_pontos.get(0));
        System.out.println("Pior custo: " + dois_pontos.get(dois_pontos.size()-1));
       
       
    	
        
        
        

	}
	
	
}
