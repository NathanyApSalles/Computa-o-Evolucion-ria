package teste;

import java.util.ArrayList;

public class DesvioPadrao {
	public static double variancia(ArrayList<Double> custos){
		double p1 = 1/Double.valueOf(custos.size()-1);
		double p2 = somaAoQuadrado(custos) - (Math.pow(somaAoQuadrado(custos), 2)/Double.valueOf(custos.size()));
		
		return p1*p2;
	}
	
	public static double somaAoQuadrado(ArrayList<Double> custos){
		
		double total = 0.0;
		
		for (int i = 0; i < custos.size(); i++) {
			total += Math.pow(custos.get(i), 2);
			
		}
		
		return total;
		
	}
	public static double desvioPadrão(ArrayList<Double> custos){
		return Math.sqrt(variancia(custos));
		
	}
	
	public static long soma(ArrayList<Double> tempo ){
		
		long total = (long) 0.0;
		
		for (int i = 0; i < tempo.size(); i++) {
			total+= (tempo.get(i)/1000);
			
		}
		
		return total;
	}


}
