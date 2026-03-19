package practicaTp1;

import java.util.Iterator;

public class Lista<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> first;
    private int size;

    public Lista() {
        this.first=null;
        this.size=0;
    }

    public Lista(T dato){
        insertFront(dato);
    }

    public void insertFront(T dato){
        Node<T> nuevo= new Node<T>(null,dato);
        nuevo.setNext(first);
        first=nuevo;
        size++;
    }

    public T extractFront(){
        if(first!=null){
            T info= this.first.getInfo();
            this.first= this.first.getNext();
            size--;
            return info;
        }
        return null;
    }

    public boolean isEmpty(){
        return this.first == null;
    }

    public int size(){
        return this.size;
    }

    public T getIndex(int index){
           if(index>=0 && first!=null){
               int contador=0;
               Node<T> node= this.first;
               while(node!=null && contador!= index){
                   node=node.getNext();
                   contador++;
               }
               if(node!=null){
                   return node.getInfo();
               }
           }
           return null;
    }

    public int indexOf(T info){
        if(first!=null){
            int res=-1;
            Node<T> node= first;
            while(node!=null ){
                res++;
                if(node.getInfo() == info){
                    return res;
                }
                node=node.getNext();
            }

        }
        return -1;
    }

    public int indexOf2(T info){
        for(int i=0;i< size-1;i++){
            if(info == getIndex(i)){
                return i;
            }
        }
        return -1;
    }

    //LISTAS ORDENADAS
    public Lista<T> ejercicio6(Lista<T> lista1, Lista<T> lista2){
        Lista<T> listaNueva= new Lista<>();

        MyIterator<T> it1= lista1.iterator();
        MyIterator<T> it2= lista2.iterator();

        while(it1.hasNext() && it2.hasNext()){
            Integer info1= (Integer) it1.get();
            Integer info2= (Integer) it2.get();

            if(info1 == info2){
                listaNueva.insertOrdenado(it1.get());
                it1.next();
                it2.next();
            }
            else if(info1> info2) {

                it2.next();
            }
            else {
                it1.next();
            }
        }
        return listaNueva;
    }

    //LISTAS DESORDENADAS
    public Lista<T> listasDesordenadas(Lista<T> lista1, Lista<T> lista2){
        Lista<T> nueva= new Lista<>();
        MyIterator<T> it1= lista1.iterator();

        while(it1.hasNext()){
            MyIterator<T> it2= lista2.iterator();
            Integer info1= (Integer) it1.get();

            while(it2.hasNext()){
                Integer info2= (Integer) it2.get();
                if(info1==info2){
                    nueva.insertOrdenado(it1.get());
                }
                it2.next();
            }
        it1.next();
        }
       return nueva;
    }

    public Lista<T> ejercicio7(Lista<T> lista1, Lista<T> lista2){
        Lista<T> nueva= new Lista<T>();
        MyIterator<T> it1= lista1.iterator();

        while(it1.hasNext()){
            boolean existe=false;
            MyIterator<T> it2= lista2.iterator();
            T info1= it1.get();

            while(it2.hasNext()){
                if(info1==it2.get()){
                    existe= true;
                }
                it2.next();
            }
            if(!existe){
                nueva.insertOrdenado(info1);
            }
            it1.next();
        }

    return nueva;
    }

    public void insertOrdenado(T info) {
        Node<T> newNode = new Node<>(null, info);

        // caso lista vacía o va al principio
        if (first == null || first.getInfo().compareTo(info) >= 0) {
            insertFront(info);
            return;
        }

        // buscar posición: avanzar mientras el siguiente sea menor que info
        Node<T> current = first;
        while (current.getNext() != null && current.getNext().getInfo().compareTo(info) < 0) {
            current = current.getNext();
        }

        // insertar después de current
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }

    @Override
    public String toString(){

        if(first!=null){
            String res= String.valueOf(first.getInfo());
            Node<T> next= first.getNext();
            while(next!=null){
                String info= String.valueOf(next.getInfo());
                 res +=  " , " + info;
                 next= next.getNext();
            }
            return res;
        }
    return "";
    }

    @Override
    public MyIterator<T> iterator(){
        return new MyIterator<T>(this.first);
    }

}