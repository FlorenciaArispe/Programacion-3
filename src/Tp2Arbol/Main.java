package Tp2Arbol;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [] args){
        Tree arbol= new Tree();
        arbol.add(12);
        arbol.add(3);
        arbol.add(22);
        arbol.add(16);
        arbol.add(1);
        arbol.add(7);
        arbol.add(11);
        arbol.add(8);
        arbol.add(25);
        arbol.add(41);
        arbol.add(13);
        arbol.printInOrder();
        //System.out.println(arbol.delete(3));
        //arbol.printInOrder();
        //System.out.println(arbol.getRoot());
        //System.out.println(arbol.hasElem(11));
        //System.out.println("NMI: " + arbol.NMI());
        //System.out.println("NMD: " + arbol.NMD());
        //System.out.println(arbol.getHeight());
        /*
        List<Integer> cadenaMasLarga= arbol.getLongestBranch();
        for(int i=0; i<cadenaMasLarga.size(); i++){
            System.out.println(cadenaMasLarga.get(i));
        }
        List<Integer> frontera= arbol.getFrontera();
        for(int i=0; i<frontera.size(); i++){
            System.out.println(frontera.get(i));
        }*/
        //System.out.println(arbol.getMaxElem());
        /*List<Integer> elemPorNivel= arbol.getElemAtLevel(3);
        for(int i=0; i<elemPorNivel.size(); i++){
            System.out.println(elemPorNivel.get(i));
        }*/
        //System.out.println(arbol.sumaNodosInternos());
        /*List<Integer> valoresMayoresaK= arbol.valoresMayoresaK(13);
        for(int i=0; i<valoresMayoresaK.size(); i++){
            System.out.println(valoresMayoresaK.get(i));
        }*/

    }
}
