package Greedy;

import java.util.ArrayList;

public class Greedy {


//    Cambio de monedas. Dado un conjunto C de N tipos de monedas con un número ilimitado de
//    ejemplares de cada tipo, se requiere formar, si se puede, una cantidad M empleando el mínimo
//    número de ellas. Por ejemplo, un cajero automático dispone de billetes de distintos valores: 100$,
//            25$, 10$, 5$ y 1$, si se tiene que pagar 289$, la mejor solución consiste en dar 10 billetes: 2 de
//    100$, 3 de 25$, 1 de 10$ y 4 de 1$.

    public ArrayList<Integer> billetesParaPagar(ArrayList<Integer> billetes, int monto){
        billetes= new ArrayList<>();
        billetes.add(200);
        billetes.add(100);
        billetes.add(50);
        billetes.add(30);
        billetes.add(10);
        billetes.add(5);

        ArrayList<Integer> solucion= new ArrayList<>();
        int sumaActual= 0;
        int indice=0;

        while(indice < billetes.size() && sumaActual < monto){
            Integer b= billetes.get(indice); //SUPONIENDO QUE ESTAN ORDENADOS DE MAYOR A MENOR
            if(b + sumaActual <= monto){
                sumaActual+= b;
                solucion.add(b);
            }else{
                indice++;
            }
        }

        if(sumaActual == monto){
            return solucion;
        }

        return null;

    }

//    Problema de la Mochila. Se tienen n objetos y una mochila. Para i = 1,2,..n, el objeto i tiene un
//    peso positivo pi y un valor positivo vi. La mochila puede llevar un peso que no sobrepase P. El
//    objetivo es llenar la mochila de tal manera que se maximice el valor de los objetos transportados,
//    respetando la limitación de capacidad impuesta. Los objetos pueden ser fraccionados, si una
//    fracción xi (0 ≤ xi ≤ 1) del objeto i es ubicada en la mochila contribuye en xipi al peso total de la
//    mochila y en xivi al valor de la carga.

    public ArrayList<Double> cantDeElementos(int pesoMaxMochila, ArrayList<Integer> valores, ArrayList<Integer> pesos){
        //3 ELEMENTOS
        valores= new ArrayList<>(3);
        valores.add(25);
        valores.add(24);
        valores.add(15);
        pesos = new ArrayList<>(3);
        pesos.add(18);
        pesos.add(15);
        pesos.add(10);

        ArrayList<Double> solucion= new ArrayList<>(3);

        //ESTRATEGIA GREEDY: hacer valor/pesos y guardarlos en un array. El que mayor numero
        // es el que en relacion peso y valor mas cantidad podemos poner.
        ArrayList<Double> opcionesElementos= new ArrayList<>(3);
        for(int i=0; i < valores.size(); i++){
            int v=valores.get(i);
            int p= pesos.get(i);
            opcionesElementos.add((double)v / p);
        }

        int pesoActual= 0;

        while(pesoActual < pesoMaxMochila ){
            int i= obtenerElMejorCandidato(opcionesElementos);//DEVUELVE EL INDICE QUE OCUPA EL MEJOR CANDIDATO TANTO EN VALORES COMO EN PESOS
            opcionesElementos.remove(i);

            if(pesoActual + pesos.get(i) <= pesoMaxMochila){
                pesoActual+= pesos.get(i);
                solucion.add(i, 1.0);
            }else{
                double porcion=(double) (pesoMaxMochila - pesoActual) / pesos.get(i);
                solucion.add(i, porcion);
                pesoActual= pesoMaxMochila;
            }
        }

        return solucion;

    }

    public int obtenerElMejorCandidato(ArrayList<Double> opcionesElementos){
        int indice=0;
        double mayorValor=0;
        for (int i=0; i < opcionesElementos.size(); i++){
            Double valor= opcionesElementos.get(i);
            if(valor > mayorValor){
                mayorValor= valor;
                indice= i;
            }
        }
        return indice;
    }





}
