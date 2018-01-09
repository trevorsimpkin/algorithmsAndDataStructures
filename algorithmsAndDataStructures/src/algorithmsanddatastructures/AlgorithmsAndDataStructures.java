/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsanddatastructures;

import java.util.Scanner;




/**
 *
 * @author trevor
 */
public class AlgorithmsAndDataStructures {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Trevor Simpkin's Algorithm and Data "
                + "Structure tutorial.");
        System.out.println("Here I will walk you through whatever you "
                + "want to learn.");
        System.out.println("Here are your options: ");
        System.out.println("1: Sorting Algorithms");
        System.out.println("2: Searching Algorithms");
        System.out.println("3: Hash Tables");
        System.out.println("4: ");
        System.out.println("Enter your desired number: ");
        
        int choice= 0;
        Scanner in = new Scanner(System.in);
        while (choice==0) {
            choice = in.nextInt();
            switch (choice){
                case 1: System.out.println("Please enter the desired size of array to sort: ");
                        int n = in.nextInt();
                        int [] arr = new int[n];
                        for (int i = 0; i < n; i++){
                            arr[i]=(int)Math.ceil(Math.random()*n);
                        }
                        sort(arr);
                        break;
                default: System.out.println("You must make a choice.");
                         choice = 0;
                         break;
            }
        }
            
        HashTable test = new HashTable(100);
        //test.put(0, "First");
        //test.put(1000, "second");
        //System.out.println(test.get(0));
       // System.out.println(test.get(3));
        //System.out.println(test.get(1000));
        //System.out.println(test);
    }
    public static void sort(int [] arr) {
        System.out.println("Here is your initial array: ");
        printArray(arr);
        System.out.println("How would you like to sort your array?");
        System.out.println("Here are your options: ");
        System.out.println("1: Heap Sort");
        System.out.println("2: Quick Sort");
        System.out.println("3: Insertion Sort");
        System.out.println("4: Speed Test");
        System.out.println("Enter your desired number: ");
        int choice= 0;
        Scanner in = new Scanner(System.in);
        while (choice==0) {
            choice = in.nextInt();
            switch (choice){
                case 1: System.out.println("You have chosen Heap Sort: ");
                        heapSort(arr);
                        break;
                case 2: System.out.println("You have chosen Quick Sort: ");
                        quickSort(arr);
                        break; 
                case 3: System.out.println("You have chosen Insertion Sort: ");
                        insertionSort(arr);
                        break;
                case 4: System.out.println("You have chosen a speed test: ");
                        speedTest(arr);
                        break;
                default: System.out.println("You must make a valid choice.");
                         choice = 0;
                         break;
            }
        }
    }
    public static void heapSort(int [] arr) {
        buildMaxHeap(arr);
        System.out.println("Initial Heap:");
        printArray(arr);
        int N = arr.length-1;
        for(int i = N; i >0; i--) {
            swap(arr,0,i);
            N=N-1;
            maxHeapify(arr,0,N);
        }
        printArray(arr);
        
        //printTree(arr);
    }
    public static void maxHeapify (int [] arr, int i, int N) {
        int l = left(i);
        int r = right(i);
        int largest;
        if (l <= N && arr[l] > arr[i]) {
            largest = l;
        }
        else {
            largest = i;
        }
        if (r <= N && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest!= i) {
            swap(arr, i, largest);
            maxHeapify(arr,largest, N);
        }
    }
    public static void buildMaxHeap (int [] arr) {
        int N = arr.length-1;
        for (int i = arr.length/2;i>=0;i--) {
            maxHeapify(arr,i, N);
        }
    }
    public static int parent(int i) {
        return (2/(i+1));
    }
    public static int left(int i) {
        return (2*i) + 1;
    }
    public static int right(int i){
        return (2*i) + 2;
    }
    public static void swap (int [] arr, int a, int b) {
        int temp;
        temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
    public static void quickSort(int [] arr) {
        
    }
    public static void insertionSort(int [] arr) {
        
    }
    public static void speedTest(int [] arr) {
        
    }
    public static void printArray(int[]arr) {
       System.out.print("[");
        for (int i: arr){
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
    /*
    //FOR HEAP TEST
    public static void printTree(int[]arr) {
        System.out.println("        " + arr[0]);
        System.out.println("      "+arr[1]+"    "+arr[2]);
        System.out.println("   "+arr[3]+"  "+arr[4]+"  "+arr[5]+"  "+arr[6]);
        System.out.println(arr[7]+" "+arr[8]+" "+arr[9]);
                
    }*/
   
    
}