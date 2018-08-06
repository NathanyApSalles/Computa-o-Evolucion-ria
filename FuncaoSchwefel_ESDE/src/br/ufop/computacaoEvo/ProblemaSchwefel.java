package br.ufop.computacaoEvo;


public class ProblemaSchwefel implements Problema {

    private Integer nVariaveis;

    public ProblemaSchwefel(Integer nVariaveis) {
        this.nVariaveis = nVariaveis;
    }
        
    @Override
    public void calcularFuncaoObjetivo(Individuo individuo) {

        Double value = 0.0;
        int n = individuo.getCromossomos().size();
        
        for(int i = 0; i < n; i++) {
        	Double xi = (Double) individuo.getCromossomos().get(i);
        	value +=  xi * Math.sin(Math.sqrt(Math.abs(xi))); 
                             
        }
        
        value = 418.9829 * n - value;
        individuo.setFuncaoObjetivo(value);
        
    }

    @Override
    public int getDimensao() {
        return this.nVariaveis;
    }
    
}
