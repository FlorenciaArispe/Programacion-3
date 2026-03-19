package practicaTp1;

public class Ordenamiento {

    private int[] helper;
    private int[] numbers;

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
        this.numbers= arr;
        this.helper= new int[arr.length];
        mergeSort(0, arr.length - 1);
    }

    public void mergeSort(int inicio, int fin){
        if(inicio<fin){
            int medio= (inicio+fin)/2;
            mergeSort(inicio, medio);
            mergeSort(medio +1, fin);
            merge(inicio, fin, medio);
        }
    }
    public void merge(int inicio, int fin, int medio){
        //COPIAR EL ARRAY A EL HELPER
        for(int i=inicio; i<=fin;i++){
            helper[i]=numbers[i];
        }
        int i=inicio;
        int j=medio+1;
        int k=inicio;
        while(i<=medio && j <=fin){
            if(helper[i]<= helper[j]){
                numbers[k]=helper[i];
                i++;
            }
            else{
                numbers[k]=helper[j];
            }
            k++;
        }
        while (j<= fin){
            numbers[k]= helper[j];
            k++;
            j++;
        }




    }

    public static void main (String [] args) {
        Ordenamiento practica= new Ordenamiento();

        int [] arr= {8,1,9,4,10,2,11,3};
//        for(int i : arr){
//            System.out.println(i);
//        }
//        practica.burbujeo(arr);
//        for(int i : arr){
//            System.out.println(i);
//        }

        practica.sort(arr);
        for(int i : arr){
            System.out.println(i);
        }


            }


}
