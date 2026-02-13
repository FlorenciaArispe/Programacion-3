package Tp4Grafos;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T>{
    private Map<Integer, LinkedList<Arco<T>>> vertices;
    private int cantidadArcos;

    public GrafoDirigido() {
        this.vertices = new HashMap<>();
        cantidadArcos=0;
    }
    @Override
    public void agregarVertice(int verticeId) {
        if(!contieneVertice(verticeId)) {
            vertices.put(verticeId, new LinkedList<Arco<T>>());
        }
    }

    @Override
    public void borrarVertice(int verticeId) {
        //entrySet while
        if(vertices.containsKey(verticeId)) {
            LinkedList<Arco<T>> listArcos= vertices.get(verticeId);
            cantidadArcos= cantidadArcos-listArcos.size();
            vertices.remove(verticeId);

            for(LinkedList<Arco<T>> arcosDestino: vertices.values()){
                Iterator<Arco<T>> iterador= arcosDestino.iterator();
                while (iterador.hasNext()){
                    Arco<T> arco = iterador.next();
                    // Si el destino del arco es el vértice que estamos eliminando, lo eliminamos
                    if (arco.getVerticeDestino() == verticeId) {
                        iterador.remove();// Elimina el arco de la lista
                        cantidadArcos--;
                    }
                }
            }
        }
    }


    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
            Arco<T> arcoNuevo = new Arco<T>(verticeId1, verticeId2, etiqueta);
            vertices.get(verticeId1).add(arcoNuevo);
            cantidadArcos++;
        }
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if(existeArco(verticeId1,verticeId2)) {
            Arco<T> arcoParaBorrar = obtenerArco(verticeId1,verticeId2);
            vertices.get(verticeId1).remove(arcoParaBorrar);
            cantidadArcos--;
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        Arco<T> arcoNuevo = new Arco<T>(verticeId1, verticeId2, null);
        return vertices.get(verticeId1).contains(arcoNuevo);
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        LinkedList<Arco<T>> arcos= vertices.get(verticeId1);
        if(arcos!=null){
            for(Arco<T> a:arcos){
                if(a.getVerticeDestino()==verticeId2){
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return vertices.size();
    }

    @Override
    public int cantidadArcos() {
        return this.cantidadArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        LinkedList<Arco<T>> listArcos = vertices.get(verticeId);
        ArrayList<Integer> verticesAdyacentes = new ArrayList<>();
        for(Arco<T> arco : listArcos){
            verticesAdyacentes.add(arco.getVerticeDestino());
        }
        return verticesAdyacentes.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> arcos = new ArrayList<>();
        for(LinkedList<Arco<T>> listArcos : vertices.values()) {
            Iterator<Arco<T>> itArcos = listArcos.iterator();
            while (itArcos.hasNext()) {
                Arco<T> arcoActual = itArcos.next();
                arcos.add(arcoActual);
            }
        }
        return arcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        LinkedList<Arco<T>> listArcos = vertices.get(verticeId);
        return listArcos.iterator();
    }



/*
    public void DFS(){
        Map<Integer, String> color = new HashMap<>();
        for(int v : vertices.keySet()){
            color.put(v, "Blanco");
        }
        for(int v : vertices.keySet()){
            if(color.get(v).equals("Blanco"))
                DFS_Visit(v, color);
        }
    }
    private void DFS_Visit(int v, Map<Integer, String> color){
        color.put(v, "Amarillo");
        LinkedList<Arco<T>> adyacentes = vertices.get(v);
        for(Arco<T> arco : adyacentes){
            int verticeAdyacente = arco.getVerticeDestino();
            if(color.get(verticeAdyacente).equals("Blanco"))
                DFS_Visit(verticeAdyacente, color);
            else if(color.get(verticeAdyacente).equals("Amarillo"))
                System.out.println("Hay ciclo");
        }
        color.put(v, "Negro");
    }
    public void BFS(){
        Queue<Integer> filaQueue = new LinkedList<>();
        Map<Integer, Boolean> visitado = new HashMap<>();
        for(int v : vertices.keySet()){
            visitado.put(v, false);
        }
        for(int v : vertices.keySet()){
            if (!visitado.get(v))
                BFS_Visit(v, filaQueue, visitado);
        }
    }
    private void BFS_Visit(int v, Queue<Integer> filaQueue, Map<Integer, Boolean> visitado){
        visitado.put(v, true);
        filaQueue.add(v);
        while(!filaQueue.isEmpty()){
            int vertice = filaQueue.poll();
            LinkedList<Arco<T>> adyacentes = vertices.get(vertice);
            for(Arco<T> arco : adyacentes){
                int verticeAdyacente = arco.getVerticeDestino();
                if(!visitado.get(verticeAdyacente)) {
                    visitado.put(verticeAdyacente, true);
                    filaQueue.add(verticeAdyacente);
                }
            }
        }
    }

    public LinkedList<Integer> caminoSimple(int i, int j){
        LinkedList<Integer> listaActual = new LinkedList<>();
        LinkedList<Integer> listaCamino = new LinkedList<>();
        Map<Integer, String> color = new HashMap<>();
        for (int vertice : vertices.keySet()) {
            color.put(vertice, "Blanco");
        }
        return caminoSimple(i, j, listaActual, listaCamino, color);

    }
    private LinkedList<Integer> caminoSimple(int i, int j, LinkedList<Integer> listaActual,
                                             LinkedList<Integer> listaCamino, Map<Integer, String> color){
        color.put(i, "Amarillo");
        listaCamino.add(i);
        if(i==j && listaCamino.size()>listaActual.size()){
            listaActual.clear();
            listaActual.addAll(listaCamino);
        }
        LinkedList<Arco<T>> adyacentes = vertices.get(i);
        for(Arco<T> arco : adyacentes) {
            int verticeAdyacente = arco.getVerticeDestino();
            if(color.get(verticeAdyacente).equals("Blanco")){
                caminoSimple(verticeAdyacente,j,listaActual,listaCamino, color);
            }
        }
        color.put(i,"Blanco");
        listaCamino.remove(listaCamino.size()-1);
        return listaActual;
    }


  LinkedList<Integer> listaActual = new LinkedList<>();
	        public LinkedList<Integer> caminoSimple(int i, int j){

	            LinkedList<Integer> listaCamino = new LinkedList<>();

	            caminoSimple(i, j, listaActual, listaCamino);
	            return listaActual;
	        }
	        private void caminoSimple(int i, int j, LinkedList<Integer> listActual,
	                                                 LinkedList<Integer> listaCamino){

	            LinkedList<Arco<T>> adyacentes = vertices.get(i);
	            if(!listaCamino.contains(i)) {
	                listaCamino.add(i);

	            for(Arco<T> arco : adyacentes) {
	                int verticeAdyacente = arco.getVerticeDestino();

	                if (verticeAdyacente == j) {
	                    listaCamino.add(verticeAdyacente);
	                    System.out.println("listaCamino:" + listaCamino.size());
	                    System.out.println("listaActual:" + listaActual.size());
	                	if (listaCamino.size() > listaActual.size()) {
	                        listaActual =  new LinkedList<Integer>(listaCamino);
	                    }
	                	listaCamino.remove(listaCamino.size()-1);
	                }

	                else {
	                    caminoSimple(verticeAdyacente, j, listaActual, listaCamino);

	                }
	            }
	            listaCamino.remove(listaCamino.size()-1);
	            }
	        }
    }*/


}
