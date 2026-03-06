package practicaGrafos;

import Tp4Grafos.Arco;

import java.util.*;

public class RecorridoDFS {
    private Grafo<Integer> grafo;
    private Map<Integer, String> color;

    private Map<String, String> colorString;

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

//    Dado un grafo no orientado que modela las rutas de la provincia de Buenos Aires, devolver
//    todos los caminos alternativos que se pueden tomar para ir desde la ciudad de Buenos
//    Aires a la ciudad de Tandil, considerando que en el tramo Las Flores-Rauch está cortado al
//    tránsito.

    public void iniciarBlancoString(Grafo<String> grafo){
        Iterator<String> it= grafo.obtenerVertices();
        while(it.hasNext()){
            colorString.put(it.next(), "Blanco");
        }
    }
    public LinkedList<LinkedList<String>> obtenerRutas(Grafo<String> grafo, String origen, String destino){
        iniciarBlancoString(grafo);
        LinkedList<String> caminoActual= new LinkedList<>();
        LinkedList<LinkedList<String>> caminos= new LinkedList<>();

        obtenerRutas(grafo, origen, destino, caminoActual, caminos);
        return caminos;


    }

    public void obtenerRutas(Grafo<String> grafo, String actual, String destino,
                                                       LinkedList<String> caminoActual,
                                                       LinkedList<LinkedList<String>> caminos){
        caminoActual.add(actual);
        if(actual.equals(destino)){

            caminos.add(new LinkedList<>(caminoActual));

        }
        else{

            colorString.put(actual, "Amarillo");
            Iterator<String> adyacentes= grafo.obtenerAdyacentes(actual);
            while(adyacentes.hasNext()){
                String ciudad= adyacentes.next();
                if(colorString.get(ciudad).equals("Blanco")){

                    boolean tramoCortado= !(actual.equals("Las flores") && ciudad.equals("Rauch")) &&
                            !(ciudad.equals("Las flores") && actual.equals("Rauch"));
                    if(tramoCortado){

                       obtenerRutas(grafo, ciudad, destino, caminoActual, caminos);

                    }


                }
            }

        }
        caminoActual.removeLast();
        colorString.put(actual, "Blanco");
    }

//    dado un grafo no dirigido escribir un algoritmo en Java que permita encontrar el ciclo hamiltoniano.
//            (es un ciclo que pasa una vez por todos los vértices de V) de mayor peso. O sea que la suma de los arcos que
//            compponen el ciclo sea la mahyor posible
//    devuelva como resultado la lista de vertice que se deben seguir en secuencia par forma el ciclo

    public LinkedList<Integer> cicloHamiltoniano(Grafo<Integer> grafo){
        iniciarBlanco(grafo);
        LinkedList<Integer> caminoActual= new LinkedList<>();
        LinkedList<Integer> cicloMayor= new LinkedList<>();
        Iterator<Integer> vertices= grafo.obtenerVertices();
        Integer sumaArcosActual=0;
        Integer sumaMayor=0;
        while(vertices.hasNext()){
            Integer vertice= vertices.next();
            if(color.get(vertice).equals("Blanco")){
                cicloHamiltoniano(grafo,vertice, vertice, cicloMayor, caminoActual,sumaArcosActual,sumaMayor);
            }
        }
        return cicloMayor;
    }
    public void cicloHamiltoniano(Grafo<Integer> grafo, Integer inicio, Integer origen, LinkedList<Integer> cicloMayor,
                                  LinkedList<Integer> caminoActual, Integer sumaArcosActual, Integer sumaMayor){

        color.put(origen, "Amarillo");
        caminoActual.add(origen);

        Iterator<Integer> adyacentes= grafo.obtenerAdyacentes(origen);
        while (adyacentes.hasNext()){
            Integer adyacente= adyacentes.next();
            if(color.get(adyacente).equals("Blanco")){
                Arco<Integer> arco= grafo.obtenerArco(origen,adyacente);
                cicloHamiltoniano(grafo,inicio, adyacente, cicloMayor, caminoActual, sumaArcosActual + arco.getEtiqueta(), sumaMayor);
            }
            else if(color.get(adyacente).equals("Amarillo") && caminoActual.size() == grafo.cantidadVertices()
            && inicio.equals(adyacente)){
                if(sumaArcosActual> sumaMayor){
                    sumaMayor= sumaArcosActual;
                    cicloMayor.clear();
                    cicloMayor.addAll(caminoActual);
                    cicloMayor.add(inicio);
                }
            }
        }
        color.put(origen, "Blanco");
        caminoActual.removeLast();

    }

//    Escriba un algoritmo en JAVA que dado un grafo G, devuelva en una lista, si existe,
//    un camino de longitud mayor a d que vaya desde v hasta w. Los valores de d, v, y w seran dados por parametro.
    public LinkedList<Integer> caminoMayoraD(Grafo<Integer> grafo, Integer d, Integer origen, Integer destino){
        iniciarBlanco(grafo);
        LinkedList<Integer> camino= new LinkedList<>();
        return caminoMayoraD(grafo, d, origen, destino, camino);


    }

    public LinkedList<Integer> caminoMayoraD(Grafo<Integer> grafo, Integer d, Integer actual, Integer destino, LinkedList<Integer> camino){
        color.put(actual, "Amarillo");
        camino.add(actual);
        if(actual.equals(destino) && camino.size() - 1 > d){
           return camino;
        }


            Iterator<Integer> adyacentes= grafo.obtenerAdyacentes(actual);
            while(adyacentes.hasNext()){
                Integer adyacente= adyacentes.next();
                if(color.get(adyacente).equals("Blanco")){

                   LinkedList<Integer> resultado= caminoMayoraD(grafo, d, adyacente, destino, camino);
                   if(resultado !=null){
                       return resultado;
                   }
                }
            }

        color.put(actual,"Blanco");
        camino.removeLast();
        return null;
    }


}
