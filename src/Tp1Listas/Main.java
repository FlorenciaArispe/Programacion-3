package Tp1Listas;

public class Main {
    public static void main(String[] args){
        List<Integer> list1= new List<>();
        list1.insertFront(21);
        list1.insertFront(17);
        list1.insertFront(10);
        list1.insertFront(9);
        System.out.println(list1);
        //System.out.println(list1.indexOf(1));
        List<Integer> list2= new List<>();
        list2.insertFront(20);
        list2.insertFront(17);
        list2.insertFront(15);
        list2.insertFront(10);
        System.out.println(list2);
        List<Integer> list3= list1.ejercicio5(list1, list2);
        System.out.println(list3);
        List<Integer> list4= list1.ejercicio6(list1, list2);
        System.out.println(list4);
    }
}
