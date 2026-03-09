package practicaGrafos;

import java.lang.reflect.Array;
import java.util.*;

public class Backtracking {
    Celda[][] laberinto;
    boolean[][] visitados;



    public Backtracking(int n){
        this.laberinto= new Celda[n][n];
        this.visitados= new boolean[n][n];
    }

//    Dado un laberinto consistente en una matriz cuadrada que tiene en cada posición un valor natural
//    y cuatro valores booleanos, indicando estos últimos si desde esa casilla se puede ir al norte, este,
//    sur y oeste, encontrar un camino de longitud mínima entre dos casillas dadas, siendo la longitud
//    de un camino la suma de los valores naturales de las casillas por las que pasa. Idea: podría
//    representarse el laberinto como una matriz, de objetos, donde cada objeto tiene el valor natural, y
//    cuatro booleanos, uno para cada dirección a la que se permite ir desde allí.

    public void marcarComoNoVisitados(){
        for(int fila=0; fila < laberinto.length; fila++){
            for(int col=0; col< laberinto[fila].length; col++){
               visitados[fila][col]= false;
            }
        }
    }

    LinkedList<Celda> resultado= new LinkedList<>();
    int mejorCosto=1000000;
    public LinkedList<Celda> caminoMinimo( Integer filaOrigen, Integer colOrigen,Integer filaFin, Integer colFin){
        //(0,1) (2,2)
        marcarComoNoVisitados();
        resultado.clear();
        LinkedList<Celda> caminoActual= new LinkedList<>();
        int costoActual=0;
        caminoMinimo( filaOrigen ,colOrigen ,filaFin ,colFin, caminoActual, costoActual);

        return resultado;
    }

    public boolean sePuedeAvanzar( Integer fila, Integer col){
        boolean isVisitado= visitados[fila][col];
        if(!isVisitado && fila>=0 && col>=0 && fila< laberinto.length && col< laberinto.length){
            return true;
        }

        return false;
    }

    public void caminoMinimo( Integer filaOrigen, Integer colOrigen,Integer filaFin, Integer colFin,
                              LinkedList<Celda> caminoActual, int costoActual){
        visitados[filaOrigen][colOrigen]= true;
        Celda actual= laberinto[filaOrigen][colOrigen];
        caminoActual.add(actual);
        costoActual+= actual.getValor();

        if(filaOrigen.equals(filaFin) && colOrigen.equals(colFin)){
            if(costoActual<mejorCosto){
                resultado.clear();
                resultado.addAll(caminoActual);
                mejorCosto= costoActual;
            }
        }
        else{

            if(actual.isNorte() && sePuedeAvanzar(filaOrigen-1, colOrigen)){
                caminoMinimo(filaOrigen-1, colOrigen, filaFin, colFin, caminoActual, costoActual);
            }
            if(actual.isSur() && sePuedeAvanzar(filaOrigen+1, colOrigen)){
                caminoMinimo(filaOrigen+1, colOrigen, filaFin, colFin, caminoActual, costoActual);
            }
            if( actual.isEste() && sePuedeAvanzar(filaOrigen,colOrigen-1)){
                caminoMinimo(filaOrigen, colOrigen+1, filaFin, colFin, caminoActual, costoActual);
            }
            if(actual.isOeste() && sePuedeAvanzar(filaOrigen, colOrigen+1)){
                caminoMinimo(filaOrigen, colOrigen -1, filaFin, colFin, caminoActual, costoActual);
            }

        }

        visitados[filaOrigen][colOrigen]= false;
        caminoActual.removeLast();
        costoActual-= actual.getValor();
    }


//    Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las
//    combinaciones de esos números tal que la suma sea igual a M
    public ArrayList<ArrayList<Integer>> combinacionesPosibles(ArrayList<Integer> conjunto, Integer M){
        ArrayList<ArrayList<Integer>> resultado= new ArrayList<>();
        ArrayList<Integer> caminoActual= new ArrayList<>();
        Integer sumaActual= 0;
        Integer posicion=0;
        combinacionesPosibles(conjunto,M, resultado, caminoActual, sumaActual, posicion);

        return resultado;
    }

    public void combinacionesPosibles(ArrayList<Integer> conjunto, Integer M, ArrayList<ArrayList<Integer>> resultado,
                                      ArrayList<Integer> caminoActual, Integer sumaActual, Integer posicion){
        if(sumaActual.equals(M)){
           resultado.add(new ArrayList<>(caminoActual));
           return;
        }
        if(sumaActual> M){
            return;
        }
        for(int inicio= posicion; inicio < conjunto.size(); inicio++){
            Integer actual= conjunto.get(inicio);
            caminoActual.add(actual);
            sumaActual+=actual;
            combinacionesPosibles(conjunto, M, resultado,caminoActual, sumaActual, inicio + 1);
            caminoActual.removeLast();
            sumaActual-= actual;
        }

    }

//    Partición de conjunto. Dado un conjunto de n enteros se desea encontrar, si existe, una partición
//    en dos subconjuntos disjuntos, tal que la suma de sus elementos sea la misma.

    public boolean subConjuntosIguales(ArrayList<Integer> conjunto){
        ArrayList<Integer> subConjunto= new ArrayList<>();
        Integer sumaActual= 0;
        Integer posicion=0;
        Integer sumaTotal= 0;
        for (Integer i: conjunto){
            sumaTotal+= i;
        }

        if(sumaTotal % 2 == 0){ //PODA NUMERO 1
            Integer objetivo= sumaTotal / 2;
            return subConjuntosIguales(conjunto, subConjunto, sumaActual, objetivo, posicion);
        }
        return false;
    }

    public boolean subConjuntosIguales(ArrayList<Integer> conjunto, ArrayList<Integer> subConjunto, Integer sumaActual, Integer objetivo, Integer posicion){

        if(sumaActual.equals(objetivo)){
            return true;
        }
        if(sumaActual>objetivo){
            return false;
        }
        for(int inicio= posicion; inicio < conjunto.size(); inicio++){
            Integer actual= conjunto.get(inicio);
            subConjunto.add(actual);
            sumaActual+= actual;
           boolean sonIgualees= subConjuntosIguales(conjunto, subConjunto, sumaActual, objetivo, inicio + 1);
           if(sonIgualees) return true;
            subConjunto.removeLast();
            sumaActual-= actual;

        }
        return false;
    }

//    Asignación de tareas a procesadores. Se tienen m procesadores idénticos y n tareas con un
//    tiempo de ejecución dado. Se requiere encontrar una asignación de tareas a procesadores de
//    manera de minimizar el tiempo de ejecución del total de tareas

    ArrayList<ArrayList<Integer>> resultadoo= new ArrayList<>();

    public ArrayList<ArrayList<Integer>> asignacionTareas(Integer cantProcesadores, ArrayList<Integer> tareas){

        ArrayList<Integer> procesadores= new ArrayList<>(cantProcesadores);

        ArrayList<ArrayList<Integer>> asignacionActual= new ArrayList<>();

        for(int i=0; i< cantProcesadores; i++){
            procesadores.add( 0);
            asignacionActual.add(new ArrayList<>());
        }

        Integer tiempoMaximo=100000;
        asignacionTareas( tareas, procesadores, asignacionActual,tiempoMaximo,0);

        return resultadoo;
    }

    public void asignacionTareas( ArrayList<Integer> tareas , ArrayList<Integer> procesadores,ArrayList<ArrayList<Integer>> asignacionActual,
                                  Integer tiempoMaximo, Integer tareaActual){
        if(tareaActual.equals(tareas.size())){
            Integer max= procesadores.get(0);
            for(Integer i: procesadores){
                if(i > max){
                    max= i;
                }
            }
            if(max < tiempoMaximo){
                tiempoMaximo= max;
                resultadoo.clear();
                for (ArrayList<Integer> lista: asignacionActual){
                    resultadoo.add(new ArrayList<>(lista));
                }
                return;
            }
        }

            Integer tarea= tareas.get(tareaActual);
            for(int procesador= 0; procesador < procesadores.size(); procesador++){
                Integer tiempoAnterior= procesadores.get(procesador);

                asignacionActual.get(procesador).add(tarea);

                procesadores.set(procesador, tiempoAnterior + tarea);

                asignacionTareas(tareas, procesadores, asignacionActual, tiempoMaximo, tareaActual + 1);
                procesadores.set(procesador, tiempoAnterior);
                asignacionActual.get(procesador).removeLast();

            }
    }






}
