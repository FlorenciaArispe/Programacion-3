package practicaTp1;

import Tp1Listas.List;

public class Main {

    public static void main(String [] args){
        Lista<Integer> lista1= new Lista<Integer>();
        lista1.insertFront(12);
        lista1.insertFront(8);
        lista1.insertFront(2);
        lista1.insertFront(6);
        lista1.insertFront(3);
        lista1.insertFront(10);
       // lista1.extractFront();

   //    System.out.println(lista1.toString());
//        System.out.println((lista1.getIndex(1)));
//        System.out.println(lista1.isEmpty());
//        System.out.println(lista1.size());
//        System.out.println(lista1.indexOf(7));
        //  System.out.println(lista1.indexOf2(8));

//        MyIterator<Integer> iterador1= lista1.iterator();
//        while(iterador1.hasNext()){
//            System.out.println(iterador1.next());
//        }

        Lista<Integer> lista2= new Lista<Integer>();
        lista2.insertFront(5);
        lista2.insertFront(12);
        lista2.insertFront(6);
        lista2.insertFront(9);
        lista2.insertFront(1);
        lista2.insertFront(2);

//      Lista<Integer> nuevaOrdenada= lista1.ejercicio6(lista1, lista2);
//        System.out.println(nuevaOrdenada.toString());
        System.out.println(lista1.toString());
        System.out.println(lista2.toString());

//        Lista<Integer> ejercicio6= lista1.listasDesordenadas(lista1, lista2);
//       System.out.println(ejercicio6.toString());

        Lista<Integer> ejercicio7= lista1.ejercicio7(lista1, lista2);
   System.out.println(ejercicio7.toString());
    }
}
