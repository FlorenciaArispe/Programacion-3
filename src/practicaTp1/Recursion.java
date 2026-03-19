package practicaTp1;

public class Recursion {

    //Implemente un algoritmo recursivo que verifique si una cadena de texto es palindroma
    public boolean cadenaPalindroma(String cadena){
        int fin= cadena.length()-1;

       return algoritmoRecursivo(cadena, 0, fin);
    }

    public boolean algoritmoRecursivo(String cadena, int inicio, int fin){
        if(inicio>=fin){
            return true;
        }

        if(cadena.charAt(inicio) != cadena.charAt(fin)){
            return false;
        }

        return algoritmoRecursivo(cadena, inicio + 1 , fin -1 );
    }

    //Implemente un algoritmo recursivo que determine si un arreglo de tamaño N está ordenado
    public boolean estaOrdenado(int [] arr){
        return estaOrdenadoRecursion(arr,0);
    }
    public boolean estaOrdenadoRecursion(int [] arr , int inicio){
        if(inicio==arr.length - 1){
            return true;
        }

        if(arr[inicio] > arr[inicio+1]){
            return false;
        }

        return estaOrdenadoRecursion(arr, inicio + 1);
    }

    //Implemente un algoritmo recursivo para buscar un elemento en un arreglo ordenado ascendentemente.
    public int buscarElemento(int [] arr, int elemento){
        return buscarElementoRecursion(arr, 0 , elemento);
    }

    public int buscarElementoRecursion(int [] arr, int inicio , int elemento){
        if(inicio == arr.length || arr[inicio] > elemento ){
            return -1;
        }

        if(arr[inicio] == elemento){
            return inicio;
        }
        return buscarElementoRecursion(arr, inicio + 1 , elemento);
    }

    //EJERCICIO ANTERIOR PERO CON BUSQUEDA BINARIA.
    public int buscarElementoBinaria(int [] arr, int elemento){

        return buscarElementoBinariaRecursion(arr, 0 , arr.length -1 ,  elemento);
    }

    public int buscarElementoBinariaRecursion(int [] arr , int inicio, int fin, int elemento){
        int medio;
        if(inicio>fin){
            return -1;
        }
        else{
            medio= (inicio+fin) / 2;
            if(arr[medio] == elemento){
                return medio;
            }
            else if(elemento < arr[medio]){
                return buscarElementoBinariaRecursion(arr, inicio, medio - 1, elemento);
            }
                else{
                return buscarElementoBinariaRecursion(arr, medio + 1, fin, elemento);
            }
        }
    }

    //Implemente un algoritmo recursivo que convierta un número en notación decimal a su equivalente en notación binaria
    public String notacionBinaria(int elemento){
        return notacionBinariaRecursion(elemento);
    }

    public String notacionBinariaRecursion(int divisor){
        if(divisor < 2){
            return String.valueOf(divisor);
        }
            return  notacionBinariaRecursion( divisor/2) + (divisor%2);
    }

    //Implemente un algoritmo recursivo que presente los primeros N términos de la secuencia de Fibonacci.
    public String fibonacci(int n){
        int numero1= 0;
        int numero2=1;
        return fibonacciRecursivo(n, numero1, numero2);
    }

    public String fibonacciRecursivo(int n, int numero1, int numero2){
        if(n<=1){
            return String.valueOf(numero1);
        }
        else{
            return numero1 + "-" + fibonacciRecursivo(n - 1  , numero2, numero1 + numero2);
        }
    }

    public static void main(String [] args){
        Recursion practica= new Recursion();

        String cadena= "reconocer";
        //System.out.println(practica.cadenaPalindroma(cadena));

        int [] arr= {2,3,4,5,6,7};

       // System.out.println(practica.estaOrdenado(arr));
       // System.out.println(practica.buscarElemento(arr, 10));
       //System.out.println(practica.notacionBinaria(26));
       System.out.println(practica.fibonacci(9));

    }
}
