package br.ufop.compEvolucionaria.ES;

import java.io.IOException;

import br.ufop.compEvolucionaria.ES.Grafo;
import br.ufop.compEvolucionaria.ES.Leitor;


public class ESRealMain {

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {

        
        Problema problema = new ProblemaColoracao();

        // Parametros - ES
        Integer mu = 50; // Tamanho da populacao
        Integer lambda = 80; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.5; // mutacao - aplicacao ao descendente - variacao/perturbacao
        
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
        
        
    }

}
