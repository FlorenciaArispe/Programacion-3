package practicaTp1;

public class Node<T> {
    private Node<T> next;
    private T info;

    public Node(Node<T> next, T info){
        this.next=next;
        this.info=info;
    }

    public Node(){
        this.next=null;
        this.info=null;
    }

    public Node<T> getNext(){
        return this.next;
    }

    public void setNext(Node<T> next){
        this.next=next;
    }

    public T getInfo(){
        return this.info;
    }

    public void setInfo(T info){
        setInfo(info);
    }
}
