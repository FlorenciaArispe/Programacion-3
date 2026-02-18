package practicaTp1;

public class Ordenamiento {

    //BURBUJEO
    public static void burbujeo(int [] arr){
        int auxiliar;
        for(int i=0; i< arr.length -1; i++){
            for(int j=0; j < arr.length - i -1; j++){
                if(arr[j]> arr[j+1]){
                        auxiliar= arr[j+1];
                        arr[j+1]=arr[j];
                        arr[j]=auxiliar;
                }
            }
        }
    }

    public void sort(int [] arr){
        mergeSort(0, arr.length - 1);
    }

    public void mergeSort(int inicio, int fin){

    }

    public void merge(int inicio, int fin, int medio){

    }

    public static void main (String [] args) {
        Ordenamiento practica= new Ordenamiento();

        int [] arr= {8,1,3,9,2};
        for(int i : arr){
            System.out.println(i);
        }
        practica.burbujeo(arr);
        for(int i : arr){
            System.out.println(i);
        }



    }
}
