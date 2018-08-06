package teste;

import java.util.ArrayList;
import java.util.Collections;

import DE_rand_2.*;
import DE_tradicional.*;


public class testeDE {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double minimo = -5.12;
        Double maximo = 5.12;        
        int D = 100;
        DE_rand_2.Problema deproblema = new DE_rand_2.ProblemaRastrigin(D);
        DE_tradicional.Problema problema = new DE_tradicional.ProblemaRastrigin(D);
        int gmax = 300;
        int Np = 80;
        
        double F = 0.001;
        double Cr = 1.0;
        
        int repeticoes = 60;
        ArrayList<Double> tempoTradicional = new ArrayList<>();
        ArrayList<Double> tempoDERand2 = new ArrayList<>();
        ArrayList<DE_tradicional.Individuo> melhIndividuoTrad = new ArrayList<>();
        ArrayList<DE_rand_2.Individuo> melhIndividuoDe = new ArrayList<>();
        ArrayList<Double> tradicional = new ArrayList<>();
        ArrayList<Double> de_rand_2 = new ArrayList<>();
        Double mediaTrad = 0.0;
        Double mediaDe = 0.0;
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
        		DE_tradicional.DEReal deReal = new DE_tradicional.DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
                DE_tradicional.Individuo melhor = deReal.executar();
                melhIndividuoTrad.add(melhor);
	            result = melhor.getFuncaoObjetivo();
	            mediaTrad += result;
	            tradicional.add(result);
        	}else{
        		metodo = "DE/rand/2";
        		System.out.println(metodo);
        		DE_rand_2.DEReal deReal = new DE_rand_2.DEReal(minimo, maximo, deproblema, gmax, D, Np, F, Cr);                
        		DE_rand_2.Individuo melhor = deReal.executar();
        		melhIndividuoDe.add(melhor);
            	result = melhor.getFuncaoObjetivo();
            	mediaDe += result;
            	de_rand_2.add(result);
                
        	}
        	
        	long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            if(metodo.equals("tradicional")){
            	tempoTradicional.add((double)totalTime);
            	
            }else{
            	tempoDERand2.add((double)totalTime);
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
        Collections.sort(tempoTradicional);
        System.out.println("Melhor tempo: " + tempoTradicional.get(0)+"ms");
        System.out.println("Pior tempo: " + tempoTradicional.get(tempoTradicional.size()-1)+"ms");
        DesvioPadrao dP = new DesvioPadrao();
        System.out.println("Média do tempo: " + dP.soma(tempoTradicional)/30 +"s");
        System.out.println("Desvio Padrão (custo): " + dP.desvioPadrão(tradicional));

        System.out.println("\n\n**************************** DE/rand/2 **********************");
        System.out.println("DE/rand/2: " + de_rand_2.toString());
        System.out.println("Tempo DE/rand/2: " + tempoDERand2);
        double melhorCustoD = de_rand_2.get(0);
        int melhorD = 0;
        for (int j = 0; j < de_rand_2.size(); j++) {
        	if(de_rand_2.get(j)< melhorCustoD){
        		melhorCustoD = de_rand_2.get(j);
        		melhorD = j;
        	}
			
		}
        double piorCustoD = de_rand_2.get(0);
        int piorD = 0;
        for (int j = 0; j < de_rand_2.size(); j++) {
        	if(de_rand_2.get(j)> piorCustoD){
        		piorCustoD = de_rand_2.get(j);
        		piorD = j;
        	}
			
        }
        System.out.println("Melhor individuo: " + melhIndividuoDe.get(melhorD));
        System.out.println("Pior individuo: " + melhIndividuoDe.get(piorD));
        Collections.sort(de_rand_2);
        System.out.println("Melhor custo: " + de_rand_2.get(0));        
        System.out.println("Pior custo: "+de_rand_2.get(de_rand_2.size()-1));
        System.out.println("Média: " + mediaDe/30);
        Collections.sort(tempoDERand2);
        System.out.println("Melhor tempo: " + tempoDERand2.get(0) +"ms");
        System.out.println("Pior tempo: " + tempoDERand2.get(tempoDERand2.size()-1)+"ms");        
        DesvioPadrao dPDE = new DesvioPadrao();
        System.out.println("Média do tempo: " + dPDE.soma(tempoDERand2)/30 + "s");
        System.out.println("Desvio Padrão (custo):" + dPDE.desvioPadrão(de_rand_2));


        
	}

}
