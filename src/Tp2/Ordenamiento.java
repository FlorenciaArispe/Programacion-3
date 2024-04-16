package Tp2;

public class Ordenamiento {

    //Ordenamiento por seleccion: busca el elemento mas chico del array y lo coloca adelante, luego hace lo mismo con el array sin ese primer elemento
    public void selectionSort(int [] arr){

        for (int i = 0; i < arr.length - 1; i++) {
            // Encontrar el elemento mas chico en el subarray sin ordenar
            int menor = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[menor]) {
                    menor = j;
                }
            }

            // Intercambiar el menor encontrado con el primer elemento del subarray sin ordenar
            int temp = arr[menor];
            arr[menor] = arr[i];
            arr[i] = temp;
        }
    }

    //Ordenamiento por burbujeo: compara pares adyacentes y si estan desordenados los invierte.
    public static void burbujeo(int [] arr){
        int i, j, aux;
        for(i=0; i<arr.length-1;i++){
            for( j=0; j<arr.length-i-1;j++){
                if(arr[j]> arr[j+1]){
                    aux= arr[j+1];
                    arr[j+1]= arr[j];
                    arr[j]=aux;
                }
            }
        }
    }

    //Ordenamiento por bubbleSort (burbujeo adaptado)
    public void bubbleSortAdapt(int [] arr){
        boolean intercambia=true;
        int j=0;
        int temp;
        while(intercambia){
            intercambia=false;
            j++;
            for(int i=0;i<arr.length-j; i++){
                if(arr[i]>arr[i+1]){
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    intercambia=true;
                }
            }
        }
    }

    //Ordenamiento MergeSort: divide en 2, ordena la parte izquierda, ordena la derecha, y junta ambas ordenadas.
    public void mergeSort(int [] arr, int inicio,int fin){
        if(inicio<fin){ //CASO BASE: el array tiene un solo elemento (inicio mayor que fin)
            int medio= (inicio+fin)/2;
            mergeSort(arr,inicio, medio); // ordena la mitad izquierda del array
            mergeSort(arr,medio+1, fin); // ordena la mitad derecha del array
            merge(arr, inicio, medio, fin); // combina ambas mitades ordenadas
        }
    }

    public void merge( int [] arr, int inicio, int medio, int fin){
        int [] aux= new int[fin+1];
        for (int i=inicio; i<=fin; i++){
            aux[i]= arr[i];
        }

        int i=inicio;
        int j= medio+1;
        int k=inicio;

        while( i<=medio && j<= fin){
            if( aux[i] <= aux[j]) {
                arr[k] = aux[i];
                i++;
            }
            else{
                arr[k]= aux[j];
                j++;
            }
            k++;
        }
        //si quedan elementos copiarlos en el array original
        while(i<=medio){
            arr[k]=aux[i];
            k++;
            i++;
        }
        while (j<=fin){
            arr[k]= aux[j];
            k++;
            j++;
        }

    }

    //Ordenamiento quickSort: agarra un pivote y ordena en base a ese. Todo lo mas chico lo pone en la izquierda, y todo lo mayor lo pone en la derecha. El pivote asi queda en el lugar correcto donde va
    public void quickSort(int [] arr, int inicio, int fin){
        if(inicio<fin){
            //encontrar el pivote y dividir el array
            int pivote= dividir(arr, inicio, fin);
            quickSort(arr, inicio, pivote-1); //hacer lo mismo con la parte izquierda
            quickSort(arr, pivote+1, fin);//hacer lo mismo con la parte derecha
        }
    }

    public int dividir( int [] arr, int inicio, int fin){
       //tomar el primer elemento como pivote
       int pivote= arr[fin];
       int i= inicio-1;
       //colocar los elementos menores que el pivote a la izquierda y los mayores a la derecha
        for(int j=inicio; j<fin; j++){
            if(arr[j]< pivote){
                i++;
                int aux= arr[i];
                arr[i]=arr[j];
                arr[j]= aux;
            }
        }
        int temp= arr[i+1]; //poner en su lugar al pivote
        arr[i+1]= arr[fin];
        arr[fin]= temp;
        return i+1;
    }

    public static void main(String[] args){
        int [] arr= new int [] {10,2,6,7,3,60,56,89,1,4};
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + "-");
        }

        Ordenamiento practica= new Ordenamiento();
         /*
        practica.selectionSort(arr);
        System.out.print("   Ordenado por seleccion: ");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]+ "-");
        }
        */
        /*
        practica.burbujeo(arr);
        System.out.print("   Ordenado por burbujeo: ");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]+ "-");
        }
        */
        /*
        practica.bubbleSortAdapt(arr);
        System.out.print("   Ordenado por burbujeo sort adaptado: ");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]+ "-");
        }
        */
        /*
        practica.mergeSort(arr,0,9);
        System.out.print("   Ordenado por mergeSort: ");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]+ "-");
        }
        */
        practica.quickSort(arr,0,9);
        System.out.print("   Ordenado por quickSort: ");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]+ "-");
        }
    }
}
