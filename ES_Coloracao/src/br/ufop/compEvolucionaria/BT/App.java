package br.ufop.compEvolucionaria.BT;

import java.util.ArrayList;
import java.util.Scanner;


public class App {

    /**
     * @param args the command line arguments
     */

    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            Leitor reader = new Leitor();
            reader.lerArquivo();
            Grafo grafo = reader.grafo;
            ArrayList<Aresta> arestas = reader.arestas;

                    Coloracao c = new Coloracao(grafo);
                    ArrayList<Integer> solucao = c.buscaTabu(c.coloracaoInicial(), 100, 10);
                    System.out.println("TAMANHO COLORAÇÃO FINAL: " + solucao.size());
                                  
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
}