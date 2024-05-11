package Tp1Listas;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {
    private Node<T> cursor;

    public MyIterator(Node<T> cursor){
        this.cursor=cursor;
    }
    @Override
    public boolean hasNext() {
        return cursor!=null;
    }

    @Override
    public T next() { //Devuelve la info y avanza
        T temp=cursor.getInfo();
        cursor= cursor.getNext();
        return temp;
    }

    public T get(){ //Devuelve la info sin avanzar
        return cursor.getInfo();
    }
}
