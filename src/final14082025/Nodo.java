package final14082025;


import java.util.ArrayList;
import java.util.List;

public class Nodo<T> {
    private int clave;
    private List<T> informacion;
    private Nodo<T> derecha;
    private Nodo<T> izquierda;

    public Nodo(){
        this.clave=0;
        this.informacion= new ArrayList<T>();
        this.derecha= null;
        this.izquierda= null;
    }

    public Nodo(int clave){
        this.clave=clave;
        this.informacion= new ArrayList<T>();
        this.derecha= null;
        this.izquierda= null;
    }

    public void addElementoLista(T elemento){
        this.informacion.add(elemento);
    }

    public int getClave(){
        return this.clave;
    }

    public Nodo<T> getDerecha(){
        return this.derecha;
    }


    public Nodo<T> getIzquierda(){
        return this.izquierda;
    }

    public void setIzquierda(Nodo<T> izquierda){
        this.izquierda=izquierda;
    }

    public void setDerecha(Nodo<T> derecha){
        this.derecha=derecha;
    }

    public List<T> getListaElementos(){
        return this.informacion;
    }

}
