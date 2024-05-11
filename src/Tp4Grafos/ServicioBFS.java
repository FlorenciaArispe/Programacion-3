package Tp4Grafos;

import java.util.*;

public class ServicioBFS<T> {
    private Grafo<T> grafo;
    Map<Integer, Boolean> visitado;
    public ServicioBFS(Grafo<T> g){
        this.grafo=g;
        this.visitado=new HashMap<>();
    }

    public void iniciarVerticesNoVisitados(){
        Iterator<Integer> verticesIterator = grafo.obtenerVertices();
        while (verticesIterator.hasNext()) {
            visitado.put(verticesIterator.next(), false);
        }
    }
    public void BFS_Visit(){
        iniciarVerticesNoVisitados();

        Queue<Integer> filaQueue = new LinkedList<>();

        Iterator<Integer> verticesIterator = grafo.obtenerVertices();

        while(verticesIterator.hasNext()){
            Integer v=verticesIterator.next();
            if(!visitado.get(v))
                BFS_Visit(v, filaQueue);
        }
    }
    private void BFS_Visit(int v, Queue<Integer> filaQueue){
        visitado.put(v, true);
        filaQueue.add(v);
        while(!filaQueue.isEmpty()){
            int vertice = filaQueue.poll();
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
            while(adyacentes.hasNext()){
                Integer a = adyacentes.next();
                if(!visitado.get(a)) {
                    visitado.put(a, true);
                    filaQueue.add(a);
                }
            }
        }
    }


}
