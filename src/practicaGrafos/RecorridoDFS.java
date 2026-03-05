package practicaGrafos;

import java.util.*;

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
            } else if (color.get(adyacente).equals("Amarillo")) {
                System.out.println("Arbol Black---> se encontro un ciclo");
            }
        }
        color.put(vertice, "Negro");
    }

    public void iniciarBlanco(Grafo<Integer> grafo){
        Iterator<Integer> it= grafo.obtenerVertices();

        while(it.hasNext()){
            color.put(it.next(), "Blanco");
        }
    }

    //    Escribir un algoritmo que, dado un grafo dirigido y dos vértices i, j de este grafo, devuelva el
//    camino simple (sin ciclos) de mayor longitud del vértice i al vértice j. Puede suponerse que
//    el grafo de entrada es acíclico.
    public LinkedList<Integer> caminoMayorLongitud(Grafo<Integer> grafo, Integer i, Integer j){
        LinkedList<Integer> caminoMasLargo= new LinkedList<>();
        LinkedList<Integer> caminoActual= new LinkedList<>();

        caminoActual.add(i);
        caminoMayorLongitudRecursion(grafo,i,j, caminoActual,caminoMasLargo);

        return caminoMasLargo;
    }

    public void caminoMayorLongitudRecursion(Grafo<Integer> grafo, Integer i, Integer j,
                                                            LinkedList<Integer> caminoActual, LinkedList<Integer> caminoMasLargo){
        if(i.equals(j)){
            if(caminoActual.size() > caminoMasLargo.size()){
                caminoMasLargo.clear();
                caminoMasLargo.addAll(caminoActual);
            }
        }
        else{
            Iterator<Integer> adyacentes= grafo.obtenerAdyacentes(i);
            while(adyacentes.hasNext()){
                Integer adyacente= adyacentes.next();
                if(!caminoActual.contains(adyacente));
                caminoActual.add(adyacente);

                caminoMayorLongitudRecursion(grafo,adyacente,j, caminoActual,caminoMasLargo);
                caminoActual.removeLast();
            }
        }
    }

//    Escriba un algoritmo que dado un grafo G y un vértice v de dicho grafo, devuelva una lista
//    con todos los vértices a partir de los cuales exista un camino en G que termine en v.
    public LinkedList<Integer> verticesQueTerminanEnV(Grafo<Integer> grafo, Integer v){
        LinkedList<Integer> resultado= new LinkedList();
        iniciarBlanco(grafo);
        Iterator<Integer> it= grafo.obtenerVertices();
        while(it.hasNext()){
            Integer vertice= it.next();
            if(color.get(vertice).equals("Blanco")){
                boolean tieneCamino= verticesQueTerminanEnV(grafo,vertice, v);
                if(tieneCamino){
                    resultado.add(vertice);
                }
            }
            iniciarBlanco(grafo);

        }
        return resultado;
    }

    public boolean verticesQueTerminanEnV(Grafo<Integer> grafo, Integer vertice, Integer destino){
        if(vertice.equals(destino)){
            return true;
        }
        else{
            color.put(vertice,"Amarillo");
            Iterator<Integer> it= grafo.obtenerAdyacentes(vertice);
            while(it.hasNext()){
                Integer adyacente= it.next();
                if(color.get(adyacente).equals("Blanco")){
                    boolean tieneCamino= verticesQueTerminanEnV(grafo, adyacente, destino);
                    if (tieneCamino){
                        return true;
                    }
                }
            }
            return false;
        }
    }


//    Supongamos una conexión entre computadoras (1, ... ,n) que se encuentra modelada
//    mediante un grafo. Se requiere, si existe, dar una conexión entre dos computadoras a y b
//    existentes sabiendo que la computadora i está fuera de servicio.
    public LinkedList<Integer> conexionEntreComputadoras(Grafo<Integer> grafo, Integer a, Integer b, Integer i){
        LinkedList<Integer> resultado= new LinkedList<>();

        iniciarBlanco(grafo);


        boolean hayConexion= conexionEntreComputadorasRecursion(grafo,a,b,i, resultado);
        if(hayConexion){
            return resultado;
        }
        else{
            resultado.clear();
        }
        return resultado;
    }

    public boolean conexionEntreComputadorasRecursion(Grafo<Integer> grafo, Integer origen, Integer destino, Integer sinFuncionar,LinkedList<Integer> resultado){
        if(origen.equals(sinFuncionar)){
            return false;
        }
        else if(origen.equals(destino)){
            resultado.add(origen);
            return true;
        }
        else{
            color.put(origen, "Amarillo");
            Iterator<Integer> adyacentes= grafo.obtenerAdyacentes(origen);
            while(adyacentes.hasNext()){
                Integer adyacente= adyacentes.next();
                if(color.get(adyacente).equals("Blanco")){
                    boolean hayConexion= conexionEntreComputadorasRecursion(grafo,adyacente, destino, sinFuncionar, resultado);
                    if(hayConexion){
                        resultado.add(origen);
                        return true;
                    }
                }


            }
            color.put(origen,"Blanco");
            return false;
        }
    }



}
