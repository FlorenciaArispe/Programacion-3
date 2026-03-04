package practicaGrafos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class RecorridoDFS {
    private Grafo<Integer> grafo;
    private Map<Integer, String> color;

    public RecorridoDFS(Grafo<Integer> grafo){
        this.grafo= grafo;
        this.color= new HashMap<>();
    }

    public void iniciarEnBlanco(){
        Iterator<Integer> vertices= grafo.obtenerVertices();
        while(vertices.hasNext()){
            color.put(vertices.next(), "Blanco");
        }
    }

    public void DFS(){
        iniciarEnBlanco();

        Iterator<Integer> vertices= grafo.obtenerVertices();
        while(vertices.hasNext()){
            Integer vertice= vertices.next();
            if(color.get(vertice).equals("Blanco")){
                DFS_Visit(vertice);
            }
        }
    }

    public void DFS_Visit(Integer vertice){
        color.put(vertice, "Amarillo");

        Iterator<Integer> adyacentes= grafo.obtenerAdyacentes(vertice);
        while(adyacentes.hasNext()){
            Integer adyacente= adyacentes.next();
            if(color.get(adyacente).equals("Blanco")){
                DFS_Visit(adyacente);
            }
        }
        color.put(vertice, "Negro");
    }
}
