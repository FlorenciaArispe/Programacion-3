package practicaGrafos;

import java.util.*;

public class RecorridoBFS {
    private Grafo<Integer> grafo;
    private Map<Integer, Boolean> visitados;

    public RecorridoBFS(Grafo<Integer> grafo){
        this.grafo= grafo;
        this.visitados= new HashMap<>();
    }

    public void marcarComoNoVisitados(){
        Iterator<Integer> it= grafo.obtenerVertices();
        while(it.hasNext()){
            visitados.put(it.next(), false);
        }
    }

    public void recorridoBFS(){
        marcarComoNoVisitados();

        Queue<Integer> fila= new LinkedList<>();
        Iterator<Integer> it= grafo.obtenerVertices();
        while(it.hasNext()){
            Integer vertice= it.next();
            if(!visitados.get(vertice)){
                BFS(vertice, fila);
            }
        }
    }

    public void BFS(Integer vertice, Queue<Integer> fila){
        visitados.put(vertice, true);
        fila.add(vertice);
        while(!fila.isEmpty()){
            Integer verticeFila= fila.poll();
            Iterator<Integer> it= grafo.obtenerAdyacentes(verticeFila);
            while(it.hasNext()){
                Integer v= it.next();
                if(!visitados.get(v)){
                    visitados.put(v, true);
                    fila.add(v);
                }
            }
        }
    }

//    Supongamos que una ciudad se encuentra modelada mediante un grafo, donde cada nodo
//    es una esquina, y las aristas representan las calles. Diseñe un algoritmo tal que dadas dos
//    esquinas, devuelva el camino más corto entre ambas de manera de caminar la menor
//    cantidad de cuadras posible.
    public void marcarComoNoVisitados(Grafo<Integer> grafo){
        Iterator<Integer> it= grafo.obtenerVertices();
        while(it.hasNext()){
            visitados.put(it.next(), false);
        }
    }



    public LinkedList<Integer>  caminoMasCorto(Grafo<Integer> grafo, Integer origen, Integer destino){
        marcarComoNoVisitados(grafo);
        LinkedList<Integer> masCorto= new LinkedList<>();
        Map<Integer, Integer> padres= new HashMap<>();
        Queue<Integer> filaQueue= new LinkedList<>();

        visitados.put(origen,true);


        filaQueue.add(origen);
        while(!filaQueue.isEmpty()){
            Integer vertice= filaQueue.poll();
            Iterator<Integer> adyacentes= grafo.obtenerAdyacentes(vertice);
            while(adyacentes.hasNext()){
                Integer adyacente= adyacentes.next();
                if(!visitados.get(adyacente)){
                    visitados.put(adyacente,true);
                    padres.put(adyacente, vertice);

                    if(adyacente.equals(destino)){
                        Integer actual= destino;
                        while(actual!=null){
                            masCorto.addFirst(actual);
                            actual= padres.get(actual);
                        }
                        return masCorto;
                    }
                    else{
                        filaQueue.add(adyacente);
                    }

                }

            }
        }
        return masCorto;

    }












}
