/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsanddatastructures;

import java.util.Arrays;
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
                            arr[i]=(int)(Math.random()*n);
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
        System.out.println("4: Counting Sort");
        System.out.println("5: Radix Sort");
        System.out.println("6: Bucket Sort");
        System.out.println("7: Speed Test");
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
                        quickSort(arr, 0, arr.length-1);
                        break; 
                case 3: System.out.println("You have chosen Insertion Sort: ");
                        insertionSort(arr);
                        break;
                case 4: System.out.println("You have chosen a Counting Sort: ");
                        countingSort(arr);
                        break;
                case 5: System.out.println("You have chosen a Radix Sort: ");
                        radixSort(arr);
                        break;
                case 6: System.out.println("You have chosen a Bucket Sort: ");
                        bucketSort(arr);
                        break;
                case 7: System.out.println("You have chosen a speed test: ");
                        speedTest(arr);
                        break;
                default: System.out.println("You must make a valid choice.");
                         choice = 0;
                         break;
            }
            System.out.println("Here is your sorted array!");
            printArray(arr);
        }
    }
    /*
    Intro to Algorithms 3rd Ed. implementation of heap sort.
    Quicksort usually beats heapsort in practice, although there
    are many uses for heaps. Maybe implement d-ary heap or min-heap for practice
    later on. Tree visualization works, but needs some tweaks in order to format
    better. Could also make JavaFX visual, but WHY? Maybe cool to make Web
    visuals for web site? Uses buildMaxHeap to build initial heap then calls
    max heapify on remainder of heap after placing largest element in rear of 
    array (heap). 
    */
    public static void heapSort(int [] arr) {
        buildMaxHeap(arr);
        int N = arr.length-1;
        for(int i = N; i > 0; i--) {
            swap(arr,0,i);
            N=N-1;
            maxHeapify(arr,0,N);
        }
        
        
    }
    /*
    Max Heapify creates a heap. By swapping larger child of parent with parent.
    This all parents have smaller or equal children. (i.e. Heap). 
    */
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
    /*
    Creates heap out of initial array. Starts with halfway point in array, which
    is always guaranteed to be largest index with children. Calls maxHeapify on 
    each until reaches root then guaranteed to have Heap.
    */
    public static void buildMaxHeap (int [] arr) {
        int N = arr.length-1;
        for (int i = arr.length/2;i>=0;i--) {
            maxHeapify(arr,i, N);
        }
    }
    /*
    Helper method for maxHeapify, gets left child index in heap. 
    */
    public static int left(int i) {
        return (2*i) + 1;
    }
    /*
    Helper method for maxHeapify, gets right child index in heap. 
    */
    public static int right(int i){
        return (2*i) + 2;
    }
    /*
    Helper method  swaps two elements. 
    */
    public static void swap (int [] arr, int a, int b) {
        int temp;
        temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
    /*
    Intro to Algorithms 3rd Ed. implementation of Quick Sort.
    Quicksort usually beats heapsort in practice, although worst case of Quick
    Sort is O(n^2) Maybe implement partition demonstration for learning
    later on.  Maybe cool to make web visuals for web site? 
    */
    public static void quickSort(int [] arr, int p, int r) {
        if(p < r) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q-1);
            quickSort(arr, q+1, r);
        }
    }
    /*
    Key to quicksort, creates a pivot point (last element in this case) Then
    splits array based on this pivot point if larger than pivot goes to right
    else goes to left. i index keeps track of where partition is. 
    */
    public static int partition(int [] arr, int p, int r) {
        int x = arr[r];
        int i = p-1;
        for (int j = p; j <= r-1; j++) {
            if(arr[j]<=x) {
                i = i+1;
                swap(arr, i, j);
            }
            
        }
        swap(arr,i+1,r);
        return i+1;
    }
    /*
    For comparison to other sorting methods. 
    */
    public static void insertionSort(int [] arr) {
        int key;
        int j;
        for (int i = 1; i<arr.length; i++) {
            key= arr[i];
            j = i-1;
            while(j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j = j-1;
            }
            arr[j+1]=key;
        }
    }
    public static void countingSort(int []arr) {
        int [] copy = Arrays.copyOf(arr, arr.length);
        int [] count =  new int [arr.length];
        for (int i = 0; i < copy.length; i++) {
            count[copy[i]]++;
        }
        for (int k = 1; k <count.length; k++) {
            count[k] = count[k]+count[k-1];
        }
        
        for( int j = arr.length-1; j >=0; j--) {
            
            arr[count[copy[j]]-1]=copy[j];
            count[copy[j]]= count[copy[j]]-1;
        }
        
    }
    public static void radixSort(int []arr) {
        
    }
    public static void bucketSort(int []arr) {
        
    }
    public static void speedTest(int [] arr) {
        int [] copy = Arrays.copyOf(arr, arr.length);
        int [] copy2 = Arrays.copyOf(arr, arr.length);
        long start = System.nanoTime();
        insertionSort(copy);
        long end = System.nanoTime();
        long insertionTime = end - start;
        start = System.nanoTime();
        heapSort(copy2);
        end = System.nanoTime();
        long heapsortTime = end - start;
        start = System.nanoTime();
        quickSort(arr, 0, arr.length-1);
        end = System.nanoTime();
        long quicksortTime = end - start;
        System.out.println("Insertion Sort time elapsed: ");
        System.out.println(insertionTime + " nanoseconds!");
        System.out.println("Heap Sort time elapsed: ");
        System.out.println(heapsortTime + " nanoseconds!");
        System.out.println("Quick Sort time elapsed: ");
        System.out.println(quicksortTime + " nanoseconds!");
    }
    /*
    Prints array. 
    */
    public static void printArray(int[]arr) {
       System.out.print("[");
        for (int i: arr){
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
    /*
    public static void printSpaces(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
    }
    public static void printLine(int size, int level, int [] arr ) {
        int startingIndex = (int)Math.pow(2,level-1)-1;
        for (int i = 0; i<2*(level-1); i++) {
            printSpaces(size/level);
            System.out.print(arr[startingIndex]);
            startingIndex++;
        }
        System.out.println();
        
    }
    */
    /*
    //For kicks and gigs. Needs some formatting. Probably should use printf. 
    public static void printTree(int[]arr) {
        int initialSpaces = ((arr.length+1)/2)*2;
        int spaces = initialSpaces*2;
        int level = 1;
        int height = (int) (Math.log(arr.length)/Math.log(2))+1;
        int i = 0;
        while (level <= height) {
            printLine(spaces, level, arr);
            level++;
        /*    printSpaces(initialSpaces);
            for (int j=i; j < level; j++) {
                System.out.print(arr[j]);
                if (j!=0) {
                    if(j+1<arr.length&&arr[j+1]<10) {
                        printSpaces(spaces);
                    }
                    else {
                        printSpaces(spaces-1);
                    }
                    
                }
            }
           
            i=level;
            level = (level*2)+1;
            if (level>=arr.length) {
                level = arr.length;
            }
            spaces=spaces/2;
            initialSpaces=initialSpaces-(spaces/2);
            System.out.println();
        }
        }
                
    }*/
   
    
}