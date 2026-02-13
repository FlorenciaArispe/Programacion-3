package Tp4Grafos;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        // Creo un grafo dirigido donde las etiquetas de los arcos son valores Float
        GrafoDirigido<Float> grafito = new GrafoDirigido<>();

        // Agrego los vertices 1 y 2
        grafito.agregarVertice(1);
        grafito.agregarVertice(2);
        grafito.agregarVertice(5);

        // Genero un arco desde 1 hasta 2 con el valor de etiqueta 3
        grafito.agregarArco(1, 2, 3F);
        grafito.agregarArco(5,1,5F);

        grafito.borrarVertice(1);

        //System.out.println(grafito.cantidadVertices());
        //System.out.println(grafito.obtenerArco(5,1));


        GrafoDirigido<Integer> grafo = new GrafoDirigido<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);
        grafo.agregarVertice(7);
        grafo.agregarVertice(8);

        grafo.agregarArco(1, 2, 8);
        grafo.agregarArco(1, 4, 18);
        grafo.agregarArco(1, 5, 232);
        grafo.agregarArco(2, 3, 123);
        grafo.agregarArco(2, 5, 2);
        grafo.agregarArco(4, 5, 65);
        grafo.agregarArco(4, 6, 3567);
        grafo.agregarArco(5, 3, 123);
        grafo.agregarArco(6, 5, 43);
        grafo.agregarArco(7, 4, 65);
        grafo.agregarArco(7, 6, 3567);
        grafo.agregarArco(7, 8, 3567);
        grafo.agregarArco(8, 5, 3567);

        ServicioDFS<Integer> dfs= new ServicioDFS<>(grafo);
        dfs.DFS_Visit();

        ServicioBFS<Integer> bfs= new ServicioBFS<>(grafo);
        bfs.BFS_Visit();

        LinkedList<Integer> caminoSimple = dfs.caminoSimple(1, 3);
        System.out.println(caminoSimple);

    }

}


