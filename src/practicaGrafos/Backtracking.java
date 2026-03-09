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


    //    Caballo de Atila. Por donde pisa el caballo de Atila jamás vuelve a crecer el pasto. El caballo fue
//    directamente hacia el jardín de n x n casillas. Empezó su paseo por una casilla cualquiera y volvió
//    a ella, es decir hizo un recorrido cerrado. No visitó dos veces una misma casilla, se movió de una
//    casilla a otra vecina en forma horizontal o vertical, pero nunca en diagonal. Por donde pisó el
//    caballo, el pasto jamás volvió a crecer. Luego de terminado el recorrido en algunas casillas
//    todavía había pasto (señal de que en ellas no había estado el caballo). Escriba un algoritmo que
//    deduzca el recorrido completo que hizo el caballo.


    private CeldaSimple[][] jardin = new CeldaSimple[3][3];
    private LinkedList<CeldaSimple> caminoResultado= new LinkedList<>();
    private boolean[][] visitadosCamino= new boolean[3][3];

    public int cantidadDeJardinSinPasto(){
        int result=0;
        for (int fila=0; fila < jardin.length; fila++){
            for(int col=0; col < jardin[fila].length; col++){
                CeldaSimple celda= jardin[fila][col];

                //si no tiene pasto quier decir que el caballo paso por ahi
                if(!celda.isTienePasto()){
                   result++;

                }
            }
        }
        return result;
    }

    public void marcarComoNoVisitadosCamino(){
        for(int fila=0; fila< jardin.length; fila++){
            for(int col=0; col< jardin[fila].length; col++){
                visitadosCamino[fila][col]= false;
            }
        }
    }


    public LinkedList<CeldaSimple> caminoDelCaballo(){

        jardin[0][1].setTienePasto(false);
        jardin[1][1].setTienePasto(false);
        jardin[1][2].setTienePasto(false);
        jardin[0][2].setTienePasto(false);

        marcarComoNoVisitadosCamino();

        int cantSinPasto= cantidadDeJardinSinPasto();

        LinkedList<CeldaSimple> caminoActual= new LinkedList<>();


        for (int fila=0; fila < jardin.length; fila++){
            for(int col=0; col < jardin[fila].length; col++){
                CeldaSimple celda= jardin[fila][col];

                //si no tiene pasto quier decir que el caballo paso por ahi
                if(!celda.isTienePasto()){
                    caminoDelCaballo(caminoActual, fila, col, fila, col, cantSinPasto);

                }
            }
        }
        return caminoResultado;
    }


    public void caminoDelCaballo(LinkedList<CeldaSimple> caminoActual, int filaOrigen, int colOrigen, int filaActual, int colActual, int cantSinPasto){
        CeldaSimple actual= jardin[filaActual][colActual];
        caminoActual.add(actual);
        visitadosCamino[filaActual][colActual]= true;


        if( (caminoActual.size() == cantSinPasto) && (Math.abs(filaActual - filaOrigen) + Math.abs(colActual - colOrigen) == 1) ){
            caminoResultado.clear();

            caminoResultado.addAll(new LinkedList<CeldaSimple>(caminoActual));
            return;
        }

            //ARRIBA
            if(filaActual - 1 >= 0 && !jardin[filaActual -1][colActual].isTienePasto() && !visitadosCamino[filaActual-1][colActual]){
                caminoDelCaballo(caminoActual, filaOrigen, colOrigen, filaActual-1, colActual, cantSinPasto);
            }
            //ABAJO
            if(filaActual + 1 < jardin.length  && !jardin[filaActual + 1][colActual].isTienePasto()&& !visitadosCamino[filaActual+1][colActual]){
                caminoDelCaballo(caminoActual, filaOrigen, colOrigen, filaActual+1, colActual, cantSinPasto);
            }
            //IZQUIERDA
            if(colActual -1 >= 0 && !jardin[filaActual][colActual - 1].isTienePasto() && !visitadosCamino[filaActual][colActual -1]){
                caminoDelCaballo(caminoActual, filaOrigen, colOrigen, filaActual, colActual - 1, cantSinPasto);
            }
            //DERECHA
            if(colActual + 1 < jardin.length  && !jardin[filaActual][colActual + 1].isTienePasto() && !visitadosCamino[filaActual][colActual + 1]){
                caminoDelCaballo(caminoActual, filaOrigen, colOrigen, filaActual, colActual + 1, cantSinPasto);
            }

        caminoActual.removeLast();
        visitadosCamino[filaActual][colActual]= false;
    }


//    Tablero mágico. Dado un tablero de tamaño n x n, construir un algoritmo que ubique (si es posible)
//    n*n números naturales diferentes, entre 1 y un cierto k (con k>n*n), de manera tal que la suma de
//    las columnas y de las filas sea igual a S.

    //DEVUELVE TRUE SI MODIFCO EL TABLERO MAGICO OSEA QUE ENCONTRO UNA SOLUCION, FALSE SI NO LO PUDO HACER

    private CeldaSimple[][] tableroMagico= new CeldaSimple[3][3];

    public boolean llenarTableroMagico(int s, int k){
        int sumaFila=0;
        int sumaCol=0;

        ArrayList<Integer> numerosPosibles= new ArrayList<>(k);
        for (int i=1; i <= k;i++ ){
            numerosPosibles.add(i);
        }

        return llenarTableroMagico(s,numerosPosibles,sumaFila, sumaCol,0,0);

    }

    public boolean llenarTableroMagico(int s, ArrayList<Integer> numerosPosibles,  int sumaFila, int sumaCol,  int filaActual, int colActual){



        if(filaActual == tableroMagico.length ){
            return true;
        }

        int sigFila= filaActual;
        int sigCol= colActual +1;

        if(sigCol == tableroMagico.length){
            sigCol=0;
            sigFila++;
        }

        for(int numeros=0; numeros < numerosPosibles.size(); numeros++){
            Integer numero= numerosPosibles.get(numeros);
            numerosPosibles.remove(numeros);
            tableroMagico[filaActual][colActual].setValor(numero);
            sumaFila+= numero;
            sumaCol+= numero;

            if(sumaFila <= s && sumaCol <= s){

                if(llenarTableroMagico(s, numerosPosibles, sumaFila, sumaCol,  sigFila, sigCol)){
                    return true;
                }

            }
            numerosPosibles.add(numeros,numero);
            tableroMagico[filaActual][colActual].setValor(0);
            sumaCol-=numero;
            sumaFila-= numero;


        }

        return false;

    }

//    Dada una cuadricula de tamaño N x M y una lista con N * M números enteros (positivos y negativos) se desea encontrar, si existe, una configuración de la
//    cuadrícula dónde, luego de colocar todos los númeres de la bolsa, se cumplan las siguientes restricciones:
//    -Si tomamos el valor de una fila como la suma de los valores que contiene, ninguna fila puede tener un valor superior a un valor F, dado por parámetro.
//    -Si tomamos el valor de una columna como la suma de los valores que contiene, ninguna columna puede tener un valor inferior a un valor C, dado por parámetro.

    private int[][] cuadricula =new int[2][2];
    private ArrayList<Integer> numeros= new ArrayList<>();

    //TRUE SI PUDO UBICARLOS, FALSE SI NO PUDO MODIFICARLOS
    public boolean ubicarNumeros(int n, int m, int F, int C){
        numeros.add(2);
        numeros.add(-1);
        numeros.add(4);
        numeros.add(3);

        int [] sumaFilas=new int[n];
        int [] sumaCol= new int[m];

        return ubicarNumeros(F,C,0,0,sumaFilas,sumaCol);
    }

    public boolean ubicarNumeros( int F, int C, int fila, int columna, int[] sumaF, int[] sumaC){
        if(fila == cuadricula.length){
            return true;
        }

        int sigFila= fila;
        int sigCol= columna + 1;
        if(sigCol == cuadricula[0].length){
            sigCol=0;
            sigFila++;
        }

        for(int i=0; i < numeros.size(); i++){
            int numero= numeros.remove(i);

            cuadricula[fila][columna]= numero;
            sumaF[fila]+= numero;
            sumaC[columna]+= numero;


            if(sumaF[fila] <= F){
                if(!(fila== cuadricula.length -1 && sumaC[columna] < C)){
                    if(ubicarNumeros(F,C,sigFila,sigCol,sumaF,sumaC)){
                        return true;
                    }
                }


            }

            cuadricula[fila][columna]= 0;
            numeros.add(i,numero);
            sumaF[fila]-= numero;
            sumaC[columna]-= numero;

        }
        return false;
    }








}
