package br.ufop.computacaoEvo;

import java.util.Collections;
import java.util.Random;



public class ESReal implements Metodo {

    // Parametros - problema - DeJong
    private Double minimo;
    private Double maximo;
    private Integer nVariaveis;
    private Problema problema;
    
    // Parametros - ES
    private Integer mu; // Tamanho da populacao
    private Integer lambda; // numero de descendentes
    private Integer geracoes; // criterio de parada
    private Double pMutacao; // mutacao - aplicacao ao descendente - variacao/perturbacao

    public ESReal(Double minimo, Double maximo, Integer nVariaveis, Problema problema, Integer mu, Integer lambda, Integer geracoes, Double pMutacao) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
        this.problema = problema;
        this.mu = mu;
        this.lambda = lambda;
        this.geracoes = geracoes;
        this.pMutacao = pMutacao;
    }
    
    @Override
    public Individuo executar() {
        
        // Geracao da populacao inicial - aleatoria - tamanho mu
        PopulacaoDouble populacao = new PopulacaoDouble(problema, minimo, maximo, nVariaveis, mu);     
        populacao.criar();
        
        // Avaliar
        populacao.avaliar();
        
        // Populacao - descendentes
        PopulacaoDouble descendentes = new PopulacaoDouble();
        
        // Criterio de parada - numero de geracoes
        for(int g = 1; g <= this.geracoes; g++) {
            
            // Para cada individuo, gerar lambda/mu descendentes
            for(int i = 0; i < populacao.getIndividuos().size(); i++) {
                
                	// Selecionar r0, r1, r2
                    Random rnd = new Random();
                    int r0, r1, r2;

                    do {
                        r0 = rnd.nextInt(this.mu);
                    } while (r0 == i);
                    

                    do {
                        r1 = rnd.nextInt(this.mu);
                    } while (r1 == r0);

                    do {
                        r2 = rnd.nextInt(this.mu);
                    } while (r2 == r1 || r2 == r0);

                    IndividuoDouble trial = new IndividuoDouble(minimo, maximo, this.nVariaveis);

                    IndividuoDouble xr0 = (IndividuoDouble) populacao.getIndividuos().get(r0);
                    IndividuoDouble xr1 = (IndividuoDouble) populacao.getIndividuos().get(r1);
                    IndividuoDouble xr2 = (IndividuoDouble) populacao.getIndividuos().get(r2);

                    // Gerar perturbacao - diferenca
                    gerarPerturbacao(trial, xr1, xr2);
                    // Mutacao - r0 + F * perturbacao
                    mutacao(trial, xr0);

                    
                    // Avaliar
                    problema.calcularFuncaoObjetivo(trial);
                    
                    // Inserir na populacao de descendentes
                    descendentes.getIndividuos().add(trial);
                    
                
            }
            
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
          
    private void gerarPerturbacao(IndividuoDouble trial, IndividuoDouble xr1, IndividuoDouble xr2) {

        // trial <- Diferenca entre r1 e r2
        for (int i = 0; i < this.nVariaveis; i++) {
            Double diferenca = xr1.getCromossomos().get(i) - xr2.getCromossomos().get(i);

            trial.getCromossomos().add(reparaValor(diferenca));
        }

    }
    private void mutacao(IndividuoDouble trial, IndividuoDouble xr0) {

        // trial <- r0 + F * perturbacao (trial)
        for (int i = 0; i < this.nVariaveis; i++) {

            Double valor = this.pMutacao * xr0.getCromossomos().get(i)
                    + this.pMutacao * (trial.getCromossomos().get(i));

            trial.getCromossomos().set(i, reparaValor(valor));
        }

    }
    private Double reparaValor(Double valor) {
        if (valor < this.minimo) {
            valor = this.minimo;
        } else if (valor > this.maximo) {
            valor = this.maximo;
        }

        return valor;
    }

}
