package final14082025;

import java.util.ArrayList;
import java.util.List;

public class ABB<T> {
    private Nodo raiz;

    public ABB(Nodo raiz){
        this.raiz=raiz;
    }

    //AGREGA UN NUEVO ELEMENTO AL ARBOL Y RETORNA TRUE SI LA CLAVE ES NUEVA O FALSE SI YA EXISTIA EN EL ARBOL
    public boolean agregar(int key, T elemento){
        if(raiz == null){
            Nodo<T> nuevo= new Nodo<>(key);
            nuevo.addElementoLista(elemento);
            raiz=nuevo;
            return true;
        }
        else{
            return agregarRecursion(key, elemento, raiz);
        }
    }

    public boolean agregarRecursion(int key,T elemento, Nodo<T> cursor){
        if(key== cursor.getClave()){
            cursor.addElementoLista(elemento);
            return false;
        }
        else if(key < cursor.getClave()){
            if(cursor.getIzquierda() != null){
                return agregarRecursion(key, elemento, cursor.getIzquierda());
            }
            else{
                Nodo<T> nuevo= new Nodo<>(key);
                nuevo.addElementoLista(elemento);
                cursor.setIzquierda(nuevo);
                return true;
            }
        }
        else{
            if(cursor.getDerecha() != null){
                return agregarRecursion(key,elemento, cursor.getDerecha());
            }
            else{
                Nodo<T> nuevo= new Nodo<>(key);
                nuevo.addElementoLista(elemento);
                cursor.setDerecha(nuevo);
                return true;
            }

        }
    }

    //RETORNA UNA LISTA CON TODOS LOS ELEMENTOS DE UN NIVEL DADO DEL ARBOL
    public List<T> elementosPorNivel(int nivel){
        if(raiz!= null){
            return elementosPorNivel(nivel, 1, raiz);
        }
        return new ArrayList<>();

    }

    public List<T> elementosPorNivel(int nivel, int nivelActual, Nodo<T> cursor){
        if(cursor == null){
            return new ArrayList<>();
        }
        else{
            if(nivel== nivelActual){
                List<T> nueva= new ArrayList<>();
                nueva.addAll(cursor.getListaElementos());
                return nueva;
            }
            else{
                List<T> listaDerecha= elementosPorNivel(nivel, nivelActual + 1, cursor.getDerecha());
                List<T> listaIzquierda= elementosPorNivel(nivel,nivelActual + 1, cursor.getIzquierda());

                listaDerecha.addAll(listaIzquierda);
                return listaDerecha;
            }
        }
    }

    //RETORNA SI EL ARBOL ES BALANCEADO O NO (ES BALANCEADO SI LA ALTURA DE LOS 2 SUBARBOLES DIFIERE MAXIMO EN 1)
    //devuelve la altura si esta balanceado o -2 si no (cualquier valor especial)
    public boolean esBalanceado(){
       return alturaBalanceado(raiz) != -2;
    }

    public int alturaBalanceado(Nodo<T> cursor){
        if(cursor== null){
            return -1;
        }
//        if()
        return 1;
    }


}
