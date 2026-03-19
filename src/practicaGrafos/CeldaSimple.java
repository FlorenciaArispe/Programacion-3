package practicaGrafos;

public class CeldaSimple {
    private int fila;
    private int columna;
    private boolean tienePasto;
    private int valor;

    public CeldaSimple(int fila, int columna, boolean tienePasto, int valor){
        this.fila=fila;
        this.columna=columna;
        this.tienePasto= tienePasto;
        this.valor= valor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean isTienePasto() {
        return tienePasto;
    }

    public void setTienePasto(boolean tienePasto) {
        this.tienePasto = tienePasto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
