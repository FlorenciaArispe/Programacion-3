package Tp4Grafos;


/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco<T> {

    private T verticeOrigen;
    private T verticeDestino;
    private T etiqueta;

    public Arco(T verticeOrigen, T verticeDestino, T etiqueta) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.etiqueta = etiqueta;
    }

    public T getVerticeOrigen() {
        return verticeOrigen;
    }

    public T getVerticeDestino() {
        return verticeDestino;
    }

    public T getEtiqueta() {
        return etiqueta;
    }

    @Override
    public boolean equals(Object obj) {
        Arco<T> otroArco = (Arco<T>) obj;
        return verticeDestino == otroArco.verticeDestino && verticeOrigen == otroArco.verticeOrigen;
    }

}