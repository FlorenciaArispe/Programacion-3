package practicaTp1;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {
    private Node<T> cursor;

    public MyIterator(Node<T> cursor){
        this.cursor=cursor;
    }

    @Override
    public T next(){
        T info= cursor.getInfo();
        cursor= cursor.getNext();
        return info;
    }

    @Override
    public boolean hasNext(){
        return cursor != null;
    }

    public T get(){
        return cursor.getInfo();
    }







}
