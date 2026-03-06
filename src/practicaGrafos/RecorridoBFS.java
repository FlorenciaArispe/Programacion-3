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

//    Dados un grafo G con sus vértices rotulados con colores y dos vértices v1 y v2, escriba un
//    algoritmo que encuentre un camino desde el vértice v1 al vértice v2 tal que no pase por
//    vértices rotulados con el color rojo

    Map<Integer, String> coloresVertices= new HashMap<>();
    //en constructor cuando el grafo se crea, tambien se le aplican a cada vertice un color. Igual que cuando
    //se agrega un vertice, se agrega un color al Map... asi que para empezar el ejercicio suponemos que ya colores esta
    //completo con el color de cada vertice.

    public LinkedList<Integer> caminoSinRojo(Grafo<Integer> grafo, Integer v1, Integer v2){
        marcarComoNoVisitados(grafo);
        LinkedList<Integer> camino= new LinkedList<>();
        Map<Integer, Integer> padres= new HashMap<>();

        Queue<Integer> fila= new LinkedList<>();
        visitados.put(v1, true);
        fila.add(v1);
        while(!fila.isEmpty()){
            Integer vertice= fila.poll();
            Iterator<Integer> adyacentes= grafo.obtenerAdyacentes(vertice);
            while(adyacentes.hasNext()){
                Integer adyacente= adyacentes.next();
                if(!visitados.get(adyacente) && coloresVertices.get(adyacente).equals("Rojo")){
                    visitados.put(adyacente, true);
                    padres.put(adyacente,vertice);
                    if(adyacente.equals(v2)){
                        Integer actual= v2;
                        while (actual!=null){
                            camino.addFirst(actual);
                            actual=padres.get(actual);
                        }
                        return camino;
                    }
                    else{
                        fila.add(adyacente);
                    }

                }
            }
        }
        return camino;
    }














}
