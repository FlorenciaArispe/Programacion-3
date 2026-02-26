package practicaGrafos;

public class Arco<T> {
    private int origen;
    private int destino;
    private T etiqueta;

    public Arco(int origen, int destino, T etiqueta){
        this.origen= origen;
        this.destino= destino;
        this.etiqueta= etiqueta;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    public T getEtiqueta() {
        return etiqueta;
    }

    @Override
    public boolean equals(Object o){
        Arco<T> otro= (Arco<T>) o;
        return this.getOrigen() == otro.getOrigen() && this.getDestino() == otro.getDestino();
    }
}
