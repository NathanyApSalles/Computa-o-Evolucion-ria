package DE_rand_2;

public class DERealPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Double minimo = -5.12;
        Double maximo = 5.12;
        
        int D = 100;
        Problema problema = new ProblemaRastrigin(D);
        
        int gmax = 300;
        int Np = 80;
        
        double F = 0.001;
        double Cr = 1.0;
        
        DEReal deReal = new DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
        
        Individuo resultado = deReal.executar();
        System.out.println(resultado);
        
        
    }
    
}
