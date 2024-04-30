package Tp2Arbol;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tree {

    private TreeNode root;

    public Tree() {
        this.root = null;
    }

    public void add(Integer value) {
        if (this.root == null)
            this.root = new TreeNode(value);
        else
            this.add(this.root,value);
    }

    private void add(TreeNode actual, Integer value) {
        if (actual.getValue() > value) {
            if (actual.getLeft() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setLeft(temp);
            } else {
                add(actual.getLeft(),value);
            }
        } else if (actual.getValue() < value) {
            if (actual.getRight() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setRight(temp);
            } else {
                add(actual.getRight(),value);
            }
        }
    }

    //devuelve el valor de la raiz
    public Integer getRoot(){
        if(this.root !=null)
            return this.root.getValue();
        return null;
    }

    //busca un elemento y devuelve si esta o no
    public boolean hasElem(Integer e) {
        return hasElem(root, e);
    }

    private boolean hasElem(TreeNode cursor, Integer e){
        //1º es null
        //2.a es la raiz
        //2.b mas chico. recursion izq
        //2.c mas grande. recursion derecha
        if(cursor==null){
            return false;
        }
        else{
            if(cursor.getValue().equals(e))
                return true;
            else if(cursor.getValue()>e){
                return hasElem(cursor.getLeft(), e);
            }
            else if (cursor.getValue()<e)
                return hasElem(cursor.getRight(), e);
        }
        return false;
    }

    //dice si esta vacio el arbol
    public boolean isEmpty(){
        return this.root==null;
    }

    //imprime en PosOrder (ultima vez que pasa por el nodo)
    public void printPosOrder(){
        posOrder(root);
        System.out.println();
    }

    public void posOrder(TreeNode elem ){
        if(elem!=null){
            posOrder(elem.getLeft());
            posOrder(elem.getRight());
            System.out.print(elem.getValue() + " ");
        }
    }

    //imprime en PreOrder (primera vez que pasa por el nodo)
    public void printPreOrder(){
        preOrder(root);
        System.out.println();
    }

    public void preOrder(TreeNode elem ){
        if(elem!=null){
            System.out.print(elem.getValue() + " ");
            preOrder(elem.getLeft());
            preOrder(elem.getRight());
        }
    }

    //imprime InOrder (por el medio del nodo, osea la segunda vez)
    public void printInOrder(){
        inOrder(root);
        System.out.println();
    }

    private void inOrder(TreeNode elem){
        if(elem!=null){
            inOrder(elem.getLeft());
            System.out.print(elem.getValue() + " ");
            inOrder(elem.getRight());
        }
    }

    // Método para encontrar el padre de un nodo
    public TreeNode getElemPadre(TreeNode cursor) {
        if (this.root == null || this.root == cursor) {
            return null; // Si el nodo raíz es nulo o coincide con el nodo hijo, devuelve null
        }

        return getElemPadre(this.root, cursor);
    }

    private TreeNode getElemPadre(TreeNode root, TreeNode hijo) {
        if (root == null || root == hijo) {
            return null; // Si el nodo actual es nulo o coincide con el nodo hijo, devuelve null
        }
        // Verifica si el nodo hijo está en el subárbol izquierdo del nodo actual
        if (root.getLeft() == hijo || root.getRight() == hijo) {
            return root; // Si el nodo actual es el padre del nodo hijo, devuelve el nodo actual
        }

        // Busca en el subárbol izquierdo
        TreeNode leftParent = getElemPadre(root.getLeft(), hijo);
        if (leftParent != null) {
            return leftParent; // Si se encuentra en el subárbol izquierdo, devuelve el resultado
        }

        // Si no se encuentra en el subárbol izquierdo, busca en el subárbol derecho
        return getElemPadre(root.getRight(), hijo);
    }


    public boolean delete(Integer elem){

        return delete(this.root,elem);
    }

    private boolean delete(TreeNode cursor, Integer elem){
        if(cursor==null)
            return false;
        else{
            if(cursor.getValue()>elem){
                return delete(cursor.getLeft(),elem);
            }
            else if(cursor.getValue()<elem){
                return delete(cursor.getRight(), elem);
            }
            else {
                TreeNode padre= this.getElemPadre(cursor);
                TreeNode hijo= null;
                //ES HOJA
                if(cursor.getLeft()==null && cursor.getRight()==null){
                    if(padre == null){
                        // Nodo es la raíz
                        this.root = null;
                    }
                    else if(padre.getLeft()==cursor){
                        padre.setLeft(null);
                    }
                    else{
                        padre.setRight(null);
                    }
                    return true;
                }

                //TIENE 1 HIJO DERECHO
                else if(cursor.getLeft()==null ){
                   hijo= cursor.getRight();
                    if(padre == null){
                        // Nodo es la raíz
                        this.root = hijo;
                    }
                    else if(padre.getLeft()==cursor){
                       padre.setLeft(hijo);
                   }
                   else{
                       padre.setRight(hijo);
                   }
                   return true;
                }
                //TIENE 1 HIJO IZQUIERDO
                else if (cursor.getRight() == null) {
                    hijo= cursor.getLeft();
                    if(padre == null){
                        // Nodo es la raíz
                        this.root = hijo;
                    }
                    else if(padre.getLeft()==cursor){
                        padre.setLeft(hijo);
                    }
                    else{
                        padre.setRight(hijo);
                    }
                    return true;
                }

                //TIENE 2 HIJOS
                else{
                    //) Reemplazar N con el NMI del subárbol derecho a N.
                    TreeNode temp= NMI(cursor.getRight());
                    cursor.setValue(temp.getValue());
                    delete(temp, temp.getValue());
                    return true;
                }

            }
        }
    }

    //NODO MAS IZQUIERDO
    private TreeNode NMI(TreeNode cursor){
        if(cursor.getLeft()==null){
            return cursor;
        }
        else {
            return NMI(cursor.getLeft());
        }
    }

    //NODO MAS DERECHO
    private TreeNode NMD(TreeNode cursor){
        if(cursor.getRight()==null){
            return cursor;
        }
        else {
            return NMD(cursor.getRight());
        }
    }

    //ALTURA DEL ARBOL
    public int getHeight(){
      return getHeight(root) - 1;
    }
    private int getHeight( TreeNode cursor){
        if(cursor==null){
            return 0;
        }
        else{
            int leftHeight= getHeight(cursor.getLeft());
            int rightHeight= getHeight(cursor.getRight());
            return 1 + Math.max(leftHeight,rightHeight);
        }
    }

    public List<Integer> getLongestBranch() {
        List<Integer> longestBranch = new ArrayList<>();
        getLongestBranch(this.root, longestBranch);
        return longestBranch;
    }

    private void getLongestBranch(TreeNode node, List<Integer> longestBranch) {
        if (node != null) {
            longestBranch.add(node.getValue());

            int leftHeight = getHeight(node.getLeft());
            int rightHeight = getHeight(node.getRight());

            if (leftHeight >= rightHeight) {
                getLongestBranch(node.getLeft(), longestBranch);
            } else {
                getLongestBranch(node.getRight(), longestBranch);
            }
        }
    }

    public List<Integer> getFrontera(){
        List<Integer> frontera = new ArrayList<>();
        getFrontera(this.root, frontera);
        return frontera;
    }

    private void getFrontera( TreeNode node, List<Integer> frontera){
        if(node.getRight() ==null && node.getLeft()== null){
            frontera.add(node.getValue());
        }
        else if (node.getRight() ==null || node.getLeft()== null){
            if(node.getRight() !=null){
                getFrontera(node.getRight(), frontera);
            }
            else {
                getFrontera(node.getLeft(),frontera);
            }
        }
        else{
            getFrontera(node.getLeft(),frontera);
            getFrontera(node.getRight(),frontera);
        }
    }

    public  Integer getMaxElem(){
        return getMaxElem(root);
    }

    private Integer getMaxElem(TreeNode node){
        if(node.getRight()==null){
            return node.getValue();
        }
        else{
            return getMaxElem(node.getRight());
        }
    }

    public List<Integer> getElemAtLevel(int nivel){
        List<Integer> elemPorNivel = new ArrayList<>();
        getElemAtLevel(this.root, elemPorNivel, nivel, 1);
        return elemPorNivel;
    }

    private void getElemAtLevel(TreeNode node, List<Integer> elemPorNivel, int nivel, int actual){
        if (node != null) {
            if (actual == nivel) {
                elemPorNivel.add(node.getValue());
            } else {
                getElemAtLevel(node.getLeft(),elemPorNivel, nivel,actual + 1);
                getElemAtLevel(node.getRight(),elemPorNivel, nivel,actual + 1 );
            }
        }
    }









}