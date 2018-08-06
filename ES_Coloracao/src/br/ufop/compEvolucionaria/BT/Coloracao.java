package br.ufop.compEvolucionaria.BT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class Coloracao {
    public Grafo grafo;
    public ArrayList<Integer> coloracao;
    

    public Coloracao(Grafo grafo) {
        this.grafo = grafo;
        this.coloracao = new ArrayList<>(Collections.nCopies(grafo.vertices.size(), 0));  
    }

    public ArrayList<Integer> coloracaoInicial(){
        for(int i = 0; i < grafo.vertices.size(); i++){
            coloracao.set(i, i);
        }
        return coloracao;
    }
    
    public ArrayList<Integer> buscaTabu(ArrayList<Integer> s0, int numIter, int tamTabu){
        
        //inicializando a lista tabu
        ArrayList<Integer> tabu = new ArrayList<>(Collections.nCopies(s0.size(), 0));
        ArrayList<Integer> f_opt_iter = new ArrayList<>(Collections.nCopies(numIter, 0));
        
        //inicializa o ótimo inicial
        int f_opt = funcao(s0);
        ArrayList<Integer> s_opt = (ArrayList<Integer>) s0.clone();
        ArrayList<Integer> s = (ArrayList<Integer>) s0.clone();
        
        for(int i = 0; i < numIter; i++){
            double maxf = Double.POSITIVE_INFINITY;
            int maxk = 0;
            ArrayList<Integer> maxs = (ArrayList<Integer>) s.clone();
                     
            for(int k = 0; k < s0.size(); k++){
            	int numTabu = 0;
                ArrayList<Integer> sk = encontraVizinho(s);
                int fk = funcao(sk);
                for(int j = 0; j<sk.size(); j++) {
                	if(sk.get(j)!= s.get(j)) {
                		numTabu = j;
                	}
                }
                if(tabu.get(numTabu) <= 0){
                    if(fk < maxf){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = numTabu;
                        if(fk < f_opt){
                            f_opt = fk;
                            s_opt = (ArrayList<Integer>) sk.clone();
                        }
                    }
                    else if(fk < f_opt){
                        maxf = fk;
                        maxs = (ArrayList<Integer>) sk.clone();
                        maxk = numTabu;
                        f_opt = fk;
                        s_opt = sk;
                    }
                }
            }
            for(int j = 0; j < tabu.size(); j++){
                if(tabu.get(j) > 0)
                    tabu.set(j, tabu.get(j) - 1);
            }
            tabu.set(maxk, tamTabu);
            s = (ArrayList<Integer>) maxs.clone();
            f_opt_iter.set(i, f_opt);
        }
        System.out.println("SOLUCAO FINAL: " + s_opt);
        System.out.println("CUSTO FINAL: " + f_opt);
        
        return listaCores(s_opt);
    }

    
    public int funcao(ArrayList<Integer> s){
        //minimizar - cada conflito aumenta a funcao e o tamanho também\
    	int f = 0;
        f = listaCores(s).size()*100;
        return f;
    }
           
    public ArrayList<Integer> listaCores(ArrayList<Integer> lista) {
        ArrayList<Integer> listaCores = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            int temp = lista.get(i);
            if (!listaCores.contains(temp)) {
                listaCores.add((Integer) temp);
            }
        }
        return listaCores;
    }  
    
    
    public ArrayList<Integer> encontraVizinho(ArrayList<Integer> s){
        ArrayList<Integer> s1; 
    	Random rand = new Random();
        ArrayList<Integer> cores = listaCores(s);
        int u = rand.nextInt(grafo.vertices.size());
        int index = rand.nextInt(cores.size() - 1);
        int c = cores.get(index);
        s1 = (ArrayList<Integer>) s.clone();
        int cont = 0;
        for (int j = 0; j < grafo.listaAdj.get(u).size(); j++) {
        	int adjacente = grafo.listaAdj.get(u).get(j).getDestino();
            if(s.get(adjacente) == c){
            	cont++;
                break;
            }
        }
        if (cont == 0) {
            s1.set(u, c);
        }
        return s1;
    }
}