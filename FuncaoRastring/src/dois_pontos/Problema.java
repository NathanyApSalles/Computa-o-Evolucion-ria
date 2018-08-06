package dois_pontos;

public class Problema {
     
    public void calcularFuncaoObjetivo(Individuo individuo){
    	
    	Double soma = 10.0 * individuo.getVariaveis().size();
    	
    	for( Double var : individuo.getVariaveis()){
    		soma += Math.pow(var, 2.0) - 10.0 * Math.cos(2 * Math.PI * var); 
    	}
    	
    	individuo.setFuncaoObjetivo(soma);
    }
}
