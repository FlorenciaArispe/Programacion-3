package practicaTp2;

public class TreeNode {
    private Integer info;
    private TreeNode izquierda;
    private TreeNode derecha;

    public TreeNode(Integer info, TreeNode izquierda, TreeNode derecha){
        this.info= info;
        this.izquierda= izquierda;
        this.derecha=derecha;
    }

    public TreeNode(){
        info=null;
        izquierda=null;
        derecha=null;
    }

    public TreeNode getIzquierda(){
        return izquierda;
    }

    public TreeNode getDerecha(){
        return derecha;
    }

    public void setIzquierda(TreeNode izquierda){
        this.izquierda=izquierda;
    }

    public void setDerecha(TreeNode derecha){
        this.derecha=derecha;
    }

    public Integer getInfo(){
        return this.info;
    }

    public void setInfo(Integer info){
        this.info=info;
    }

}
