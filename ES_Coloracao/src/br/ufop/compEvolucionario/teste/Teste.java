package br.ufop.compEvolucionario.teste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


import br.ufop.compEvolucionaria.BT.Aresta;
import br.ufop.compEvolucionaria.BT.Coloracao;
import br.ufop.compEvolucionaria.ES.ESReal;
import br.ufop.compEvolucionaria.ES.Grafo;
import br.ufop.compEvolucionaria.ES.Individuo;
import br.ufop.compEvolucionaria.ES.Leitor;
import br.ufop.compEvolucionaria.ES.Problema;
import br.ufop.compEvolucionaria.ES.ProblemaColoracao;


public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int repeticoes = 60;
		String metodo = "";
		
		ArrayList<Integer> execucoes = new ArrayList<>();
        for (int i = 1; i <= repeticoes; i++) {
        	
        	execucoes.add(i);         	
			
		}
        

        Collections.shuffle(execucoes);
        
        System.out.println(metodo);
		Problema problema = new ProblemaColoracao();

        // Parametros - ES
        Integer mu = 50; // Tamanho da populacao
        Integer lambda = 80; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.5; // mutacao - aplicacao ao descendente - variacao/perturbacao
        
        ArrayList<Integer> solucoesES = new ArrayList<>();
        Double mediaES = 0.0;
        ArrayList<Integer> solucoesBT = new ArrayList<>();
        Double mediaBT = 0.0;
        ArrayList<Long> tempoES = new ArrayList<>();
        ArrayList<Long> tempoBT = new ArrayList<>();
        for (int i = 0; i < repeticoes; i++) {
        	long startTime = System.currentTimeMillis();
        	
        	if(execucoes.get(i)%2 == 0){
        		metodo = "ES";
        		
                Leitor reader = new Leitor();
                try {
        			reader.lerArquivo();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
                Grafo grafo = reader.grafo;
                
                ESReal esReal = new ESReal(problema, mu, lambda, geracoes, pMutacao, grafo);
                Individuo melhor = esReal.executar();
               
                System.out.println(melhor);
                int result = melhor.getFuncaoObjetivo()/100;
                solucoesES.add(result);
                mediaES += result;
                
                

        	}else{
        		metodo = "BT";
        		System.out.println(metodo);
        		 try {
        	            br.ufop.compEvolucionaria.BT.Leitor reader = new br.ufop.compEvolucionaria.BT.Leitor();
        	            reader.lerArquivo();
        	            br.ufop.compEvolucionaria.BT.Grafo grafo = reader.grafo;
        	            ArrayList<Aresta> arestas = reader.arestas;

	                    Coloracao c = new Coloracao(grafo);
	                    ArrayList<Integer> solucao = c.buscaTabu(c.coloracaoInicial(), 100, 10);
	                    System.out.println("QUANTIDADE DE CORES: " + solucao.size());  
	                    int result = solucao.size();
	           		 	solucoesBT.add(result);
	           		 	mediaBT += result;
        	        } 
        	        catch (Exception e){
        	            e.printStackTrace();
        	        }
        	} 
        	long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            if(metodo.equals("ES")){
            	tempoES.add(totalTime);
            	
            }else{
            	tempoBT.add(totalTime);
            }
        
        
        }
        
        System.out.println("************************ ES ************************");
        Collections.sort(solucoesES);
        System.out.println("Melhor resultado: " + solucoesES.get(0) + " cores");
        System.out.println("Pior resultado: " + solucoesES.get(solucoesES.size()-1) + " cores");
        Collections.sort(tempoES);
        System.out.println("Melhor tempo: " + tempoES.get(0));
        System.out.println("Pior tempo: " + tempoES.get(tempoES.size()-1));
        System.out.println("Media: " + mediaES/solucoesES.size());
        System.out.println(solucoesES.toString());
        
        
        System.out.println("************************ BT ************************");
        Collections.sort(solucoesBT);
        System.out.println("Melhor resultado: " + solucoesBT.get(0) + " cores");
        System.out.println("Pior resultado: " + solucoesBT.get(solucoesBT.size()-1) + " cores");
        Collections.sort(tempoBT);
        System.out.println("Melhor tempo: " + tempoBT.get(0));
        System.out.println("Pior tempo: " + tempoBT.get(tempoBT.size()-1));
        System.out.println("Media: "+ mediaBT/solucoesBT.size());
        System.out.println(solucoesBT.toString());
        
        
        
        

	}

}
