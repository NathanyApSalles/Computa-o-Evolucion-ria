/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dois_pontos;

/**
 *
 * @author fernando
 */
public class AGReal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Problema problema = new Problema();

        Integer tamanho = 50;
        Double pCrossover = 0.1;
        Double pMutacao = 0.02;
        Integer geracoes = 300;
        
        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
        
        ag.executar();
    }
    
}
