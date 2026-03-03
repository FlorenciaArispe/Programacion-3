package practicaGrafos;

import Tp4Grafos.Arco;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {
    private Map<Integer, LinkedList<Arco<T>>> vertices;
    private int cantidadArcos;


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
        if (contieneVertice(verticeId)) {
            vertices.remove(verticeId);
            for(LinkedList<Arco<T>> listaArcos: vertices.values()){
               Iterator<Arco<T>> iterador= listaArcos.iterator();
               while(iterador.hasNext()){
                   Arco<T> arco= iterador.next();
                   if(arco.getVerticeDestino() == verticeId){
                       iterador.remove();
                       cantidadArcos--;
                   }
               }
            }
        }
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(contieneVertice(verticeId1) && contieneVertice(verticeId2)){
            if(!existeArco(verticeId1, verticeId2)){
                Arco<T> nuevoArco= new Arco<>(verticeId1, verticeId2, etiqueta);
                LinkedList<Arco<T>> listaArcos= vertices.get(verticeId1);
                listaArcos.add(nuevoArco);
                cantidadArcos++;
            }
        }
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
        LinkedList<Arco<T>> listaArcos= vertices.get(verticeId1);
        Iterator<Arco<T>> iterador= listaArcos.iterator();
        while(iterador.hasNext()){
            Arco<T> arco= iterador.next();
            if(arco.getVerticeDestino() == verticeId2){
                return true;
            }
        }
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
        ArrayList<Integer> adyacentes= new ArrayList<Integer>();
        LinkedList<Arco<T>> arcos= vertices.get(verticeId);

        for(Arco<T> a : arcos){
            adyacentes.add(a.getVerticeDestino());
        }
        return adyacentes.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> arcos= new ArrayList<Arco<T>>();
        for(LinkedList<Arco<T>> listaArcos: vertices.values()){
            Iterator<Arco<T>> it= listaArcos.iterator();
            while(it.hasNext()){
                Arco<T> a= it.next();
                arcos.add(a);
            }
        }
        return arcos.iterator();
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
