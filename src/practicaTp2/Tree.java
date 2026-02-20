package practicaTp2;

public class Tree {

    private TreeNode raiz;

    public Tree(TreeNode raiz){
        this.raiz= raiz;
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

        //System.out.println("raiz: " +  arbol.getRaizObjeto().getIzquierda().getIzquierda().getDerecha().getInfo());
       // System.out.println("esta vacia: " + arbol.isEmpty());
System.out.println(arbol.delete(6));


    }

}
