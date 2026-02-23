package practicaTp2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Tree {

    private TreeNode raiz;

    public Tree(TreeNode raiz){
        this.raiz= raiz;
    }

    public Tree(){
        this.raiz=null;
    }

    public void add(Integer nuevo){
        if(raiz == null ){
            raiz= new TreeNode(nuevo,null,null);
        }
        else{
            addBuscar(raiz, nuevo);
        }
    }
    public void addBuscar(TreeNode raiz, Integer nuevo){

        if(nuevo > raiz.getInfo()){
            if(raiz.getDerecha() == null){
                raiz.setDerecha(new TreeNode(nuevo,null,null));
            }
            else{
                addBuscar(raiz.getDerecha(),nuevo);
            }
            addBuscar(raiz.getDerecha(), nuevo);
        }
        else if(nuevo< raiz.getInfo()){
            if(raiz.getIzquierda() == null){
                raiz.setIzquierda(new TreeNode(nuevo, null, null));
            }
            else{
                addBuscar(raiz.getIzquierda(), nuevo);
            }
        }
    }

    //DEVUELVE EL VALOR DE LA RAIZ
    public Integer getRaiz(){
        if(raiz != null){
            return raiz.getInfo();
        }
        return null;
    }

    //DEVUELVE LA RAIZ
    public TreeNode getRaizObjeto(){
        return raiz;
    }

    //existe un elemento
        public boolean hasElem(Integer elemento){
            return hasElemRecursion(raiz, elemento);
        }

        public boolean hasElemRecursion(TreeNode raiz, Integer elemento){
            if(raiz==null){
                return false;
            }
            else{
                if(raiz.getInfo()==elemento){
                    return true;
                }
                else if(elemento > raiz.getInfo()){
                    if(raiz.getDerecha()!=null){
                      return hasElemRecursion(raiz.getDerecha(), elemento);
                    }

                }
                else{
                    if(raiz.getIzquierda() != null){
                        return hasElemRecursion(raiz.getIzquierda(), elemento);
                    }
                }
            }
            return false;

        }

        //ESTA VACIO EL ARBOL
    public boolean isEmpty(){
        return this.raiz==null;
    }

    //DEVOLVER PADRE DE UN NODO
    public TreeNode getPadre(TreeNode cursor){
        //es la raiz (no tiene padre)
        if(raiz == null || cursor == raiz){
            return null;
        }
        else {
            return getPadreRecursion(cursor, raiz);
        }

    }

    public TreeNode getPadreRecursion(TreeNode cursor, TreeNode raiz){
        if(raiz == null){
            return null;
        }
        else if(raiz.getIzquierda() == cursor || raiz.getDerecha() == cursor){
            return raiz;
        }
        else{
            if(cursor.getInfo() < raiz.getInfo()){
                return getPadreRecursion(cursor, raiz.getIzquierda());
            }
            else{
                return getPadreRecursion(cursor, raiz.getDerecha());
            }
        }
    }

    //NMI- NODO MAS IZQUIERDO
    public TreeNode NMI(TreeNode cursor){
        if(cursor.getIzquierda() == null ){
            return cursor;
        }
        else{
           return NMI(cursor.getIzquierda());
        }
    }

    //ELIMINAR UN NODO
    public boolean delete(Integer elemento){
        if(raiz != null){
            return deleteRecursion(elemento, raiz);
        }
        return false;

    }

    public boolean deleteRecursion(Integer elemento, TreeNode cursor){
        if(elemento  > cursor.getInfo()){
            return deleteRecursion(elemento, cursor.getDerecha());
        }
        else if(elemento < cursor.getInfo()){
            return deleteRecursion(elemento, cursor.getIzquierda());
        }
        else{

            TreeNode padre= getPadre(cursor);
            //caso 1- HOJA
            if(cursor.getDerecha() == null && cursor.getIzquierda() == null){
                System.out.println("es hoja " + cursor.getInfo());
                if (padre == null) {
                    //NODO ES RAIZ
                    this.raiz= null;
                }
                //ES EL NODO DE LA IZQUIERDA
                else if(padre.getIzquierda() == cursor ){
                    padre.setIzquierda(null);
                }
                //ES EL NODO DE LA DERECHA
                else{
                    padre.setDerecha(null);
                }
                return true;
            }
            //caso 2 a)- TIENE UN SOLO HIJO IZQUIERDO
            else if(cursor.getDerecha() == null ){
                System.out.println("tiene un hijo izquierdo " + cursor.getInfo());
                if(padre == null){
                    raiz= cursor;
                }
                else if(padre.getIzquierda() == cursor){
                    padre.setIzquierda(cursor.getIzquierda());
                }
                else{
                    padre.setDerecha(cursor.getIzquierda());
                }
                return true;


            }
            //caso 2 b)- TIENE UN SOLOR HIJO DERECHO
            else if (cursor.getIzquierda() == null){
                System.out.println("tiene un hijo derecho " + cursor.getInfo());
                if(padre== null){
                    raiz=cursor;
                }
                else if(padre.getIzquierda() == cursor){
                    padre.setIzquierda(cursor.getDerecha());
                }
                else{
                    padre.setDerecha(cursor.getDerecha());
                }
                return true;
            }
            //caso 3- TIENE 2 HIJOS
            else{
                System.out.println("tiene 2 hijos " + cursor.getInfo());
                TreeNode nmi= NMI(cursor.getDerecha());
                cursor.setInfo(nmi.getInfo());
               return deleteRecursion(nmi.getInfo(), nmi);
            }
        }
    }

    //DEVUELVE LA ALTURA DE UN ARBOL
    public int getHeight(){
        return getHeight(raiz);
    }

    public int getHeight( TreeNode cursor ){

        if(cursor == null){
            return -1;
        }
        else{
            int alturader= getHeight(cursor.getDerecha());
            int alturaizq=getHeight(cursor.getIzquierda());

            if(alturader> alturaizq){
                return alturader + 1;
            }else{
                return alturaizq + 1;
            }
        }

    }

    //POSORDER
    public void posOrder(){
        posOrder(raiz);
    }

    public void posOrder(TreeNode cursor){
        if(cursor!=null){
            posOrder(cursor.getIzquierda());
            posOrder(cursor.getDerecha());
            System.out.println(cursor.getInfo());
        }
    }

    //PREORDER
    public void preOrder(){
        preOrder(raiz);
    }

    public void preOrder(TreeNode cursor){
        if(cursor!= null ){
            System.out.println(cursor.getInfo());
            preOrder(cursor.getIzquierda());
            preOrder(cursor.getDerecha());
        }
    }

    //EN ORDEN
    public void inOrder(){
        inOrder(raiz);
    }

    public void inOrder(TreeNode cursor){
        if(cursor!=null){
            inOrder(cursor.getIzquierda());
            System.out.println(cursor.getInfo());
            inOrder(cursor.getDerecha());
        }
    }

    //RECORRIDO MAS LARGO
    public List<Integer> getLongesBranch(){


        return getLongesBranch(raiz);
    }

    public List<Integer> getLongesBranch(TreeNode cursor){
        if(cursor == null){
            return new ArrayList<>();
        }
        else{
            List<Integer> caminoIzq= new ArrayList<>();
            List<Integer> caminoDer= new ArrayList<>();

            caminoIzq = getLongesBranch(cursor.getIzquierda());
            caminoDer = getLongesBranch(cursor.getDerecha());

            if(caminoIzq.size() > caminoDer.size()){
                caminoIzq.add(0,cursor.getInfo());
                return caminoIzq;
            }
            else{
                caminoDer.add(0,cursor.getInfo());
                return caminoDer;
            }
        }
    }

    //FRONTERA: TODOS LOS NODOS HOJAS
    public List<Integer> getFrontera(){
        return getFrontera(raiz);
    }

    public List<Integer> getFrontera(TreeNode cursor){
        if(cursor== null){
            return new ArrayList<>();
        }
        else{
            if(cursor.getIzquierda() == null && cursor.getDerecha() == null){
                List<Integer> nueva= new ArrayList<>(cursor.getInfo());
                nueva.add(cursor.getInfo());
                return nueva;
            }
            else{
                List<Integer> izquierda= getFrontera(cursor.getIzquierda());
                List<Integer> derecha= getFrontera(cursor.getDerecha());
                izquierda.addAll(derecha);

                return izquierda;
            }
        }
    }

    //DEVUELVE EL MAYOR ELEMENTO DEL ARBOL
    public Integer getMaxElem(){
        return getMaxElem(raiz);
    }

    public Integer getMaxElem(TreeNode cursor){
        if(cursor==null){
            return -1;
        }
        else{

            Integer resultado= getMaxElem(cursor.getDerecha());

            return Math.max(resultado, cursor.getInfo());
        }
    }

    //DEVUELVE UNA LISTA DE TODOS LOS NODOS DE UN DETERMINADO NIVEL.
    public List<Integer> getElemAtLevel(int nivel){
        return getElemAtLevel(nivel, 1,  raiz);
    }

    public List<Integer> getElemAtLevel(int nivel, int actual , TreeNode cursor){
    if(cursor == null){
        return new ArrayList<>();
    }
    else{
        if(nivel == actual){

            List<Integer> result= new ArrayList<>();
            result.add(cursor.getInfo());
            return result;
        }
        else{
            List<Integer> listaDerecha= getElemAtLevel(nivel, actual + 1, cursor.getDerecha());
            List<Integer> listaIzquierda= getElemAtLevel(nivel, actual + 1, cursor.getIzquierda());
            listaDerecha.addAll(listaIzquierda);

            return listaDerecha;
        }
    }
    }

//    Dado un árbol binario de búsquedas que almacena números enteros, implementar un algoritmo
//    que retorne la suma de todos los nodos internos del árbol.
        public int sumaNodosInternos(){
            return sumaNodosInternos(raiz);
        }

        public int sumaNodosInternos(TreeNode cursor){
            if (cursor == null) {
                return 0;
            }
            else{
                if(cursor.getIzquierda() == null && cursor.getDerecha() == null){
                    //ES HOJA
                    return 0;
                }
                else{
                    int sumaDerecha=  sumaNodosInternos(cursor.getDerecha());
                    int sumaIzquierda=  sumaNodosInternos(cursor.getIzquierda());

                    return cursor.getInfo() + sumaDerecha + sumaIzquierda;
                }
            }
        }

        //Devolver una lista solo con los nodos que sean superiores a K
    public List<Integer> valoresMayores(int k){
        return valoresMayores(k, raiz);
    }

    public List<Integer> valoresMayores(int k, TreeNode cursor){
        if(cursor== null){
            return new ArrayList<>();
        }
        else{
            List<Integer> mayoresDerecha= valoresMayores(k, cursor.getDerecha());
            List<Integer> mayoresIzquierda= valoresMayores(k, cursor.getIzquierda());
            mayoresDerecha.addAll(mayoresIzquierda);

            if(cursor.getInfo() > k){
                mayoresDerecha.add(cursor.getInfo());


            }
            return mayoresDerecha;

        }
    }

    //COMPLETAR LOS VALORES INTERNOS DE UN ARBOL (EL VALOR ES IGUAL A EL HIJO DERECHO MENOS EL HIJO IZQUIERDO
    public void completarInternos(){
            Integer infoRaiz= completarInternos(raiz);
            raiz.setInfo(infoRaiz);
    }

    public Integer completarInternos(TreeNode cursor){
        if(cursor==null){
            return 0;
        }
        else{
            if(cursor.getDerecha() == null && cursor.getIzquierda() == null){
                return cursor.getInfo();
            }
            else{
                Integer derecha=  completarInternos(cursor.getDerecha());
                Integer izquierda= completarInternos(cursor.getIzquierda());

                cursor.setInfo(derecha-izquierda);
                return derecha - izquierda;

            }
        }
    }

    //FINAL 24/4/2024
    //devolver una lista con todos los valores ordenados que esten entre M y N
    public List valoresEntre(int m, int n){
        List resultado= new ArrayList();
        valoresEntre(m, n, raiz, resultado);
        return resultado;
    }

    public void valoresEntre(int m, int n, TreeNode cursor, List resultado){
     if(cursor!= null){
         if(cursor.getInfo() > m){
             valoresEntre(m,n,cursor.getIzquierda(), resultado);
         }
         if(cursor.getInfo() >= m && cursor.getInfo()<= n){
             resultado.add(cursor.getInfo());
         }
         if(cursor.getInfo() < n){
             valoresEntre(m,n,cursor.getDerecha(), resultado);
         }
     }
    }


//FINAL 17/07/2025
    //dado 2 arboles binarios de busqueda, determinar si ambos tienen la misma estructura (sin importar el valor los nodos)
    public boolean mismaEstructura(Tree arbol1, Tree arbol2){
       return mismaEstructuraRecursion(arbol1.getRaizObjeto(), arbol2.getRaizObjeto());
    }

    public boolean mismaEstructuraRecursion(TreeNode cursor1, TreeNode cursor2){
        if((cursor1 != null && cursor2 == null) || (cursor1 == null && cursor2 != null)){
            return false;
        }
        else{
            if(cursor1 == null && cursor2 == null){
                return true;
            }
            boolean izquierda= mismaEstructuraRecursion(cursor1.getIzquierda(), cursor2.getIzquierda());
            boolean derecha=  mismaEstructuraRecursion(cursor1.getDerecha(), cursor2.getDerecha());

           return izquierda && derecha;

        }
    }

    public static void main (String [] args){
        TreeNode raiz= new TreeNode(6,null,null);

        Tree arbol= new Tree(raiz);

        arbol.add(7);
        arbol.add(3);
        arbol.add(1);
        arbol.add(5);
        arbol.add(4);
//        arbol.add(2);
        arbol.add(9);
      arbol.add(10);
//        arbol.add(11);

//            List<Integer> lista= new ArrayList<>(arbol.valoresEntre(4,7));
//        System.out.println("tamaño " + lista.size());
//
//    for(Integer i : lista){
//            System.out.println(i);
//    }

        //System.out.println("raiz: " +  arbol.getRaizObjeto().getIzquierda().getIzquierda().getDerecha().getInfo());
       // System.out.println("esta vacia: " + arbol.isEmpty());
    //System.out.println(arbol.delete(6));
        //System.out.println("altura del arbol " + arbol.getHeight());
       // arbol.posOrder();
        //arbol.preOrder();
        //arbol.inOrder();
//    List<Integer> lista= new ArrayList<>(arbol.getLongesBranch());
//        System.out.println("tamaño " + lista.size());
//
//    for(Integer i : lista){
//            System.out.println(i);
//    }
//
//            List<Integer> lista= new ArrayList<>(arbol.getFrontera());
//        System.out.println("tamaño " + lista.size());
//
//    for(Integer i : lista){
//            System.out.println(i);
//    }

      //  System.out.println(arbol.getMaxElem());

//            List<Integer> lista= new ArrayList<>(arbol.getElemAtLevel(2));
//        System.out.println("tamaño " + lista.size());
//
//    for(Integer i : lista){
//            System.out.println(i);
//    }

      //  System.out.println(arbol.sumaNodosInternos());

//                    List<Integer> lista= new ArrayList<>(arbol.valoresMayores(6));
//        System.out.println("tamaño " + lista.size());
//
//    for(Integer i : lista){
//            System.out.println(i);
//    }

        Tree arbol1= new Tree();

        arbol1.add(6);
        arbol1.add(3);
        arbol1.add(7);
        arbol1.add(5);

        Tree arbol2= new Tree();

        arbol2.add(6);
        arbol2.add(3);
        arbol2.add(7);
        arbol2.add(5);

        System.out.println(arbol1.mismaEstructura(arbol1, arbol2));



    }

}
