package br.ufop.compEvolucionaria.BT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Leitor {

    private File arquivo;
    public Grafo grafo;
    public ArrayList<Aresta> arestas;
    public ArrayList<Integer> vertices;

    public Leitor() {
    	
    	this.vertices = new ArrayList<>(); 

    }

    public Grafo lerArquivo() throws IOException {

           
            FileReader reader = new FileReader("/home/nathany/workspace/ES_Coloracao/src/mulsol.i.1.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            // Inicializar grafo
            arestas = new ArrayList<>();
            String[] line;

            line = bufferedReader.readLine().split(" ");
            int numVertices = (Integer.parseInt(line[0]));
            int numArestas = (Integer.parseInt(line[1]));
            
            ArrayList<ArrayList<Aresta>>listaAdjacencia = new ArrayList<>();
            

            for(int i = 0; i < numVertices; i++) {          
            		this.vertices.add(i);
            }
            
            for(int i = 0; i < numVertices; i++) {          
                listaAdjacencia.add(new ArrayList<Aresta>());
        }

            //Preencher arestas
            for(int i = 0; i < numArestas; ++i){
                    String[] infoAresta = bufferedReader.readLine().split(" ");
                    int origem = Integer.parseInt(infoAresta[0]);
                    int destino = Integer.parseInt(infoAresta[1]);

                    Aresta a = new Aresta(origem, destino);
                   
                    arestas.add(a);
                    
                    listaAdjacencia.get(origem).add(a);
            }
            
            grafo = new Grafo(vertices, arestas,listaAdjacencia);
            bufferedReader.close();
            reader.close();
            return grafo;

    }
}