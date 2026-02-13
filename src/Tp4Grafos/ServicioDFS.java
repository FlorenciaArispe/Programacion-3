package Tp4Grafos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class ServicioDFS<T> {
    private Grafo<T> grafo;
    Map<Integer, String> color;

    public ServicioDFS(Grafo<T> g){
        this.grafo=g;
        color=new HashMap<>();
    }

    public void iniciarEnBlancoVertices(){
        Iterator<Integer> verticesIterator = grafo.obtenerVertices();
        while (verticesIterator.hasNext()) {
            color.put(verticesIterator.next(), "Blanco");
        }
    }

    public void DFS_Visit(){
        iniciarEnBlancoVertices();

        Iterator<Integer> verticesIterator = grafo.obtenerVertices();

        while(verticesIterator.hasNext()){
            Integer v=verticesIterator.next();
            if(color.get(v).equals("Blanco"))
                DFS_Visit(v);
        }
    }

    private void DFS_Visit(int v){
        color.put(v, "Amarillo");
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(v);
        while(adyacentes.hasNext()){
            Integer a = adyacentes.next();
            if(color.get(a).equals("Blanco")){
                DFS_Visit(a);
            }
              /*
                Ejercicio 3
                Implemente un algoritmo que determine si un grafo dirigido tiene algún ciclo.
                */
            else if(color.get(a).equals("Amarillo"))
                System.out.println("Hay ciclo");
        }
        color.put(v, "Negro");
    }

    /*
    Ejercicio 4
    Escribir un algoritmo que, dado un grafo dirigido y dos vértices i, j de este grafo, devuelva el
    camino simple (sin ciclos) de mayor longitud del vértice i al vértice j. Puede suponerse que el
    grafo de entrada es acíclico.
    */
    public LinkedList<Integer> caminoSimple(int i, int j){
        LinkedList<Integer> listaActual = new LinkedList<>();
        LinkedList<Integer> listaCamino = new LinkedList<>();

        iniciarEnBlancoVertices();

        caminoSimple(i, j, listaActual, listaCamino);
        return listaActual;

    }
    private void caminoSimple(int i, int j, LinkedList<Integer> listaActual, LinkedList<Integer> listaCamino){
        color.put(i, "Amarillo");
        listaCamino.add(i);
        if(i==j && listaCamino.size()>listaActual.size()){
            listaActual.clear();
            listaActual.addAll(listaCamino);
        }
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(i);
        while(adyacentes.hasNext()){
            Integer a = adyacentes.next();
            if(color.get(a).equals("Blanco")){
                caminoSimple(a,j,listaActual,listaCamino);
            }
        }
        color.put(i,"Blanco");
        listaCamino.remove(listaCamino.size()-1);
    }

      /*
    Ejercicio 5
    Escriba un algoritmo que dado un grafo G y un vértice v de dicho grafo, devuelva una lista
    con todos los vértices a partir de los cuales exista un camino en G que termine en v.
    */


}
