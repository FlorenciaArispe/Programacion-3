package Tp1Listas;

public class List<T extends Comparable<T>> implements Iterable<T>  {
    private Node<T> first;
    private int size;

    public List() {
        this.first = null;
        this.size=0;
    }

    public void insertFront(T info) {
        Node<T> tmp = new Node<T>(info,null);
        tmp.setNext(this.first);
        this.first = tmp;
        this.size++;
    }

    public T extractFront() {
        if(first!=null){
            T respuesta=this.first.getInfo();
            Node<T> siguiente= this.first.getNext();
            first=siguiente;
            this.size--;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.first==null;
    }

    //retorna la informacion de una posicion en la lista
    public T get(int index) {
        if(first!=null && this.size - 1>= index){
            int counter=0;
            Node<T> response=this.first;
            while (index!=counter){
                counter++;
                response= response.getNext();
            }
            return response.getInfo();
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    //recibe un elemento y retorna el índice donde está almacenado ese elemento, o -1 si el elemento no existe.
    public int indexOf(T info){
        for(int i=0; i<size; i++){
            if(this.get(i)==info)
                return i;
        }
        return -1;
    }

    //dadas dos listas construir otra con los elementos comunes suponiendo que las listas están ordenadas y la lista resultante debe mantenerse ordenada.
    public List<T> ejercicio5(List<T> list1, List<T> list2){
        List<T> result= new List<>();
        MyIterator<T> it1= list1.iterator();
        MyIterator<T> it2= list2.iterator();
        while(it1.hasNext() && it2.hasNext()){
            Integer info1= (Integer) it1.get();
            Integer info2= (Integer) it2.get();
            if( info1<info2){
                it1.next();
            }
            else if (info1 > info2){
                it2.next();
            }
            else{
                result.insertOrdered(it1.get());
                it1.next();
                it2.next();
            }
        }
        return result;
    }

    public void insertOrdered(T info){
        Node<T> newNode= new Node<>(info, null);
        if(this.first==null){ //1º caso: es el primer nodo de la lista
            insertFront(info);
        }
        else {
            int compare=-1;
            Node<T> cursor= this.first;
            while (cursor!=null && compare <0){
                compare= cursor.getInfo().compareTo(info);
                if(compare<0){ // 2º caso: el compare dio -1 entonces cursor es mas chico que info nueva
                    if(cursor.getNext()==null){ // si cursor no tiene un siguiente va directamente despues de el
                        cursor.setNext(newNode);
                    }
                    cursor= cursor.getNext(); //si tiene un siguiente, hay que seguir buscando donde va el nuevo
                }
            }
            if(cursor !=null){ // cursor queda parado en el lugar donde va el nuevo
                newNode.setInfo((cursor.getInfo())); //en el nuevo ponemos la info de ese cursor (para no perder el valor, lo vamos a pisar)
                newNode.setNext(cursor.getNext()); //el nuevo apunta al siguiente de cursor
                cursor.setInfo(info); // ahora cursor va a tener la nueva info
                cursor.setNext(newNode); // y el siguiente de cursor es el nuevo nodo, quedan todos enlazados
            }
        }
    }

    //Escriba una función que dadas dos listas ordenadas construya otra con los elementos que están en la primera pero no en la segunda.
    public List<T> ejercicio6(List<T> list1, List<T> list2 ){
        List<T> result= new List<>();
        MyIterator<T> it1= list1.iterator();
        MyIterator<T> it2= list2.iterator();
        while(it1.hasNext() && it2.hasNext()){
            Integer info1= (Integer) it1.get();
            Integer info2= (Integer) it2.get();
            if( info1<info2){
                result.insertOrdered(it1.get());
                it1.next();
            }
            else if (info1 > info2){
                it2.next();
            }
            else{
                it1.next();
                it2.next();
            }
        }
        while(it1.hasNext()){
            result.insertOrdered(it1.get());
        }
        return result;
    }
    @Override
    public String toString() {
        if(first!=null){
            String response = String.valueOf(this.first.getInfo());
            Node<T> node= this.first.getNext();
            while(node!=null){
                String nuevo= String.valueOf(node.getInfo());
                response+= " , " + nuevo;
                node=node.getNext();
            }
            return response;
        }
        return "";
    }

    @Override
    public MyIterator<T> iterator() {
        return new MyIterator<>(this.first);
    }
}