package Tp2RecursionyOrdenamiento;

public class Recursion {
    //1)Implemente un algoritmo recursivo que determine si un arreglo de tamaño N está ordenado
    public boolean itIsOrdered(int [] arr, int inicio){
        if(inicio == arr.length-1){ // LLEGO AL FINAL
            return true;
        }
        else{// 2 opciones:
            if(arr[inicio]<=arr[inicio+1]) //encuentra uno mayor adelante (vuelve a entrar al metodo)
                return itIsOrdered(arr, inicio +1);
            else //encuentra uno menor adelante y retorna false
                return false;
        }
    }

    //2)Implemente un algoritmo recursivo para buscar un elemento en un arreglo ordenado ascendentemente.
    public int searchElement(int [] arr, int elem, int inicio, int fin ){
        int medio;
        if(inicio>fin){
            return -1;
        }
        else{
            medio =(inicio + fin) /2;
            if(elem>arr[medio]){
                return searchElement(arr, elem, medio+1, fin);
            }
            else if(elem<arr[medio]){
                return searchElement(arr, elem, inicio, medio-1);
            }
            else{
                return medio;
            }
        }
    }

    //3)Implemente un algoritmo recursivo que convierta un número en notación decimal a su equivalente en notación binaria.
    public String conversionBinaria(int decimal){
        String resultado;
        int resto;
        if(decimal ==0 || decimal == 1){
            resultado= Integer.toString(decimal);
        }
        else {
            resto= decimal%2;
            resultado= conversionBinaria(decimal/2) + Integer.toString(resto);
        }
        return resultado;
    }
    //4)Implemente un algoritmo recursivo que presente los primeros N términos de la secuencia de Fibonacci.
    public String fibonacci(int n, int anterior, int actual){
        String resultado="";

        if(n<=0){
            return resultado;
        }

        else {
            resultado= Integer.toString(anterior) + fibonacci(n-1, actual, anterior + actual);
        }
        return resultado;
    }

    //5)Dado un arreglo ordenado de números distintos A se desea construir un algoritmo que
    //determine si alguno de los elementos de dicho arreglo contiene un valor igual a la posición en la
    //cuál se encuentra, es decir, A[i] = i.
    public boolean valorIgualAPosicion(int[] arr, int inicio, int fin){
        if(inicio>fin)
            return false;
        else {
            if(arr[inicio]==inicio)
                return true;
            else
                return valorIgualAPosicion(arr, inicio+1, fin);
        }
    }

    public static void main(String[] args) {
        Recursion practica = new Recursion();

        int [] arr=  new int[]{0, 2, 3, 4, 5,6,7};
        int i=practica.searchElement(arr,7, 0, 6);
        //System.out.println(i);
        //System.out.println(practica.itIsOrdered(arr,0));
        System.out.println(practica.valorIgualAPosicion(arr,0,6));

        //System.out.println(practica.conversionBinaria(10));
        //System.out.println(practica.fibonacci(6, 0, 1));


    }

}