package teste;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

import ES_tradicional.ESReal;
import ES_tradicional.Individuo;
import ES_tradicional.IndividuoDouble;
import ES_tradicional.Problema;
import ES_tradicional.ProblemaRastrigin;

import ES_tipo_uniforme.*;

public class testeES {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;
        Problema problema = new ProblemaRastrigin(nVariaveis);
        ES_tipo_uniforme.Problema problemaR = new ES_tipo_uniforme.ProblemaRastrigin(nVariaveis);

        // Parametros - ES
        Integer mu = 50; // Tamanho da populacao
        Integer lambda = 80; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.09; // mutacao - aplicacao ao descendente - variacao/perturbacao
        
        int repeticoes = 60;
        ArrayList<Double> tempoTradicional = new ArrayList<>();
        ArrayList<Double> tempoTipoUniforme = new ArrayList<>();
        ArrayList<ES_tradicional.Individuo> melhIndividuoTrad = new ArrayList<>();
        ArrayList<ES_tipo_uniforme.Individuo> melhIndividuoTipoUni = new ArrayList<>();
        ArrayList<Double> tradicional = new ArrayList<>();
        ArrayList<Double> uniforme = new ArrayList<>();
        Double mediaTrad = 0.0;
        Double mediaUni = 0.0;
        ArrayList<Integer> execucoes = new ArrayList<>();
        for (int i = 1; i <= repeticoes; i++) {
        	
        	execucoes.add(i);         	
			
		}
        
        Collections.shuffle(execucoes);
        
        for (int i = 0; i < repeticoes; i++) {
        	long startTime = System.currentTimeMillis();
        	String metodo = "";
        	Double result = 0.0;
        	
        	if(execucoes.get(i)%2 == 0){
        		metodo = "tradicional";
        		System.out.println(metodo);
	        	ESReal esReal = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
	            Individuo melhor = esReal.executar();
	            melhIndividuoTrad.add(melhor);
	            result = melhor.getFuncaoObjetivo();
	            mediaTrad += result;
	            tradicional.add(result);
            }else{
            	metodo = "tipo_uniforme";
            	System.out.println(metodo);
            	ES_tipo_uniforme.ESReal esReal_uniforme = new ES_tipo_uniforme.ESReal(minimo, maximo, nVariaveis, problemaR, mu, lambda, geracoes, pMutacao);
            	ES_tipo_uniforme.Individuo melhor = esReal_uniforme.executar();
            	melhIndividuoTipoUni.add(melhor);
            	result = melhor.getFuncaoObjetivo();
            	mediaUni += result;
            	uniforme.add(result);
                
            	
            }

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            if(metodo.equals("tradicional")){
            	tempoTradicional.add((double)totalTime);
            	
            }else{
            	tempoTipoUniforme.add((double)totalTime);
            }
        }
        
        
        System.out.println("************************ Tradicional ************************");
        System.out.println("Tradicional: " + tradicional.toString());
        System.out.println("Tempo tradicional: " + tempoTradicional);
        double melhorCustoT = tradicional.get(0);
        int melhorT = 0;
        for (int j = 0; j < tradicional.size(); j++) {
        	if(tradicional.get(j)< melhorCustoT){
        		melhorCustoT = tradicional.get(j);
        		melhorT = j;
        	}
			
		}
        double piorCustoT = tradicional.get(0);
        int piorT = 0;
        for (int j = 0; j < tradicional.size(); j++) {
        	if(tradicional.get(j)> piorCustoT){
        		piorCustoT = tradicional.get(j);
        		piorT = j;
        	}
			
        }
        System.out.println("Melhor individuo: " + melhIndividuoTrad.get(melhorT));
        System.out.println("Pior individuo: " + melhIndividuoTrad.get(piorT));
        Collections.sort(tradicional);
        System.out.println("Melhor custo: " + tradicional.get(0));        
        System.out.println("Pior custo: "+tradicional.get(tradicional.size()-1));
        System.out.println("Média: " + mediaTrad/30);
        System.out.println("Melhor tempo: " + tempoTradicional.get(0)+"ms");
        System.out.println("Pior tempo: " + tempoTradicional.get(tempoTradicional.size()-1)+"ms");
        DesvioPadrao dP = new DesvioPadrao();
        System.out.println("Média do tempo: " + dP.soma(tempoTradicional)/30+"s");
        System.out.println("Desvio Padrão: " + dP.desvioPadrão(tradicional));
        
        System.out.println("\n\n************************ Tipo Uniforme **********************");
        System.out.println("Uniforme: " + uniforme.toString());
        System.out.println("Tempo tipo uniforme: " + tempoTipoUniforme);
        double melhorCustoU = uniforme.get(0);
        int melhorU = 0;
        for (int j = 0; j < uniforme.size(); j++) {
        	if(uniforme.get(j)< melhorCustoU){
        		melhorCustoU = uniforme.get(j);
        		melhorU = j;
        	}
			
		}
        double piorCustoU = uniforme.get(0);
        int piorU = 0;
        for (int j = 0; j < uniforme.size(); j++) {
        	if(uniforme.get(j)> piorCustoU){
        		piorCustoU = uniforme.get(j);
        		piorU = j;
        	}
			
        }
        System.out.println("Melhor individuo: " + melhIndividuoTipoUni.get(melhorU));
        System.out.println("Pior individuo: " + melhIndividuoTipoUni.get(piorU));
        Collections.sort(uniforme);
        System.out.println("Melhor custo: " + uniforme.get(0));        
        System.out.println("Pior custo: "+uniforme.get(uniforme.size()-1));
        System.out.println("Média: " + mediaUni/30);
        System.out.println("Melhor tempo: " + tempoTipoUniforme.get(0)+"ms");
        System.out.println("Pior tempo: " + tempoTipoUniforme.get(tempoTipoUniforme.size()-1)+"ms");
        DesvioPadrao dPUni = new DesvioPadrao();
        System.out.println("Média do tempo: " + dPUni.soma(tempoTipoUniforme)/30+"s");
        System.out.println("Desvio Padrão:" + dPUni.desvioPadrão(uniforme));

        

	}

}
