package ES_tradicional;


public class ESRealMain {

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {

        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;
        Problema problema = new ProblemaRastrigin(nVariaveis);

        // Parametros - ES
        Integer mu = 50; // Tamanho da populacao
        Integer lambda = 80; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.09; // mutacao - aplicacao ao descendente - variacao/perturbacao

        ESReal esReal = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
        Individuo melhor = esReal.executar();
       
        System.out.println(melhor);
        
        
    }

}
