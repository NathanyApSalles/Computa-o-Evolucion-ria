package br.ufop.computacaoEvo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;


public class ESRealMain {

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {

        Double minimo = -500.0;
        Double maximo = 500.0;
        Integer nVariaveis = 50;
        Problema problema = new ProblemaSchwefel(nVariaveis);

        // Parametros - ES
        Integer mu = 100; // Tamanho da populacao
        Integer lambda = 80; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.8; // mutacao - aplicacao ao descendente - variacao/perturbacao

        ESReal esReal = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
        
        ArrayList<Individuo> arrayMelhor = new ArrayList<>();
        ArrayList<Double> resultados = new ArrayList<>();
        ArrayList<Double> tempo = new ArrayList<>();
        Double result = 0.0;
        Double media = 0.0;
        
        for (int i = 0; i < 30; i++) {

        	long startTime = System.currentTimeMillis();
            Individuo melhor = esReal.executar();
            arrayMelhor.add(melhor);
            result += melhor.getFuncaoObjetivo();
            resultados.add(result);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

//            System.out.println(i + 1 + "° execução:" + melhor.getFuncaoObjetivo() + " tempo de execução: " + totalTime);
            System.out.println(i+1+";"+melhor.getFuncaoObjetivo()+";"+totalTime);
            tempo.add((double) totalTime);
		}
       
        
        System.out.println("Tempo: " + tempo.toString());
        Collections.sort(arrayMelhor);
        System.out.println("Melhor custo: " + arrayMelhor.get(0).getFuncaoObjetivo());
        System.out.println("Pior custo: " + arrayMelhor.get(resultados.size()-1).getFuncaoObjetivo());
        System.out.println("Média: " + result/30);
        
        
    }

}
