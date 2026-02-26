package practicaGrafos;

import Tp4Grafos.Arco;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {
    private Map<Integer, LinkedList<Arco<T>>> vertices;


    @Override
    public void agregarVertice(int verticeId) {
//        if(vertices.get(verticeId) == null){
//            vertices.put(verticeId, new LinkedList<>());
//        }

        if(!contieneVertice(verticeId)){
            vertices.put(verticeId, new LinkedList<>());
        }
    }

    @Override
    public void borrarVertice(int verticeId) {

        vertices.remove(verticeId);

        for(LinkedList<Arco<T>> l : vertices.values()){



        }

    }



    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {

    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {

    }

    @Override
    public boolean contieneVertice(int verticeId) {
//        for(Integer v : vertices.keySet()){
//            return verticeId == v;
//        }
//        return false;
        return this.vertices.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        return null;
    }

    @Override
    public int cantidadVertices() {
        return 0;
    }

    @Override
    public int cantidadArcos() {
        return 0;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {

        return null;
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<T> arcos= new ArrayList<>();

        


        return null;
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        if(vertices.containsKey(verticeId)){
           LinkedList<Arco<T>> arcos= vertices.get(verticeId);
           return arcos.iterator();
        }
        return null;
    }
}
