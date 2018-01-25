/*
 * This program implements many algorithms and data structures from Cormen's 
 * Introduction to Algorithms 3rd ed. Implemented for practice and curiosity.

 * @author Trevor Simpkin
 * @version 1.0
 * @since 2018-01-03
 */
package algorithmsanddatastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class AlgorithmsAndDataStructures {

    public static void main(String[] args) {
        System.out.println("Welcome to Trevor Simpkin's Algorithm and Data "
                + "Structure tutorial.");
        System.out.println("Here I will walk you through whatever you "
                + "want to learn.");
        System.out.println("Here are your options: ");
        System.out.println("1: Sorting Algorithms");
        System.out.println("2: Selection Algorithms");
        System.out.println("3: Data Structures");
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
                        populateArray(arr, n);
                        sort(arr);
                        break;
                case 2: System.out.println("Please enter the desired size of array to select from: ");
                        int x = in.nextInt();
                        int [] arrsel = new int[x];
                        distinctArray(arrsel);
                        System.out.println("Please enter the desired element to select: ");
                        int k = in.nextInt();
                        System.out.println("Your initial array is: ");
                        printArray(arrsel);
                        System.out.println("The " + k +" smallest element is : " + kthSelection(arrsel, 0, arrsel.length-1, k));
                        break;
                default: System.out.println("You must make a choice.");
                         choice = 0;
                         break;
            }
        }
            
        //HashTable test = new HashTable(100);
        //test.put(0, "First");
        //test.put(1000, "second");
        //System.out.println(test.get(0));
       // System.out.println(test.get(3));
        //System.out.println(test.get(1000));
        //System.out.println(test);
    }
    /*
    * Method to sort an array of integers (unless bucket sort is chosen than
    * array of doubles. Can use various sorting methods or perform speed test on
    * sorting methods. 
    * 
    * @param arr integer array to be sorted. 
    */
    public static void sort(int [] arr) {
        if(arr.length<1000) {
            System.out.println("Here is your initial array: ");
            printArray(arr);
        }
        else {
            System.out.println("Your array has been created but is too long to print.");
        }
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
                        int digits = String.valueOf(arr.length).length();
                        radixSort(arr, digits);
                        break;
                case 6: System.out.println("You have chosen a Bucket Sort: ");
                        System.out.println("Bucket sort is best used when the "
                                + "distribution of numbers to be sorted is relatively "
                                + "small.");
                        System.out.println("We are going to reconstruct array to"
                                + " satisfy this condition.");
                        int n = arr.length;
                        double [] bucketarr = new double [n];
                        populateArray(bucketarr, 1.0);
                        System.out.println("Your new array is: ");
                        printArray(bucketarr);
                        bucketSort(bucketarr, 10);
                        System.out.println("Here is your sorted array!");
                        printArray(bucketarr);
                        break;
                case 7: System.out.println("You have chosen a speed test: ");
                        speedTest(arr);
                        break;
                default: System.out.println("You must make a valid choice.");
                         choice = 0;
                         break;
            }
            System.out.println("Here is your sorted array!");
            if (choice!=6) {
               printArray(arr); 
            }
            
        }
    }
    /*
    * Intro to Algorithms 3rd Ed. implementation of heap sort.
    * Quicksort usually beats heapsort in practice, although there
    * are many uses for heaps. Maybe implement d-ary heap or min-heap for practice
    * later on. Tree visualization works, but needs some tweaks in order to format
    * better. Could also make JavaFX visual, but WHY? Maybe cool to make Web
    * visuals for web site? Uses buildMaxHeap to build initial heap then calls
    * max heapify on remainder of heap after placing largest element in rear of 
    * array (heap). Could also make heap object with maxheapify and constructor 
    * could build maxheap. 
    * 
    * @param arr integer array to be used with heapsort
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
    * Max Heapify creates a heap. By swapping larger child of parent with parent.
    * This all parents have smaller or equal children. (i.e. Heap). 
    * @param arr integer array to be reconstructed into heap.
    * @param i index to be used
    * @param N third parameter
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
    * Creates heap out of initial array. Starts with halfway point in array, which
    * is always guaranteed to be largest index with children. Calls maxHeapify on 
    * each until reaches root then guaranteed to have Heap.
    * 
    * @param arr integer array to be turned into heap.
    */
    public static void buildMaxHeap (int [] arr) {
        int N = arr.length-1;
        for (int i = arr.length/2;i>=0;i--) {
            maxHeapify(arr,i, N);
        }
    }
    /*
    * Helper method for maxHeapify, gets left child index in heap. 
    * 
    * @param i ith value in heap.
    * @return integer of left child of i value
    */
    public static int left(int i) {
        return (2*i) + 1;
    }
    /*
    * Helper method for maxHeapify, gets right child index in heap. 
    *
    * @param i ith value in heap.
    * @return integer of left child of i value
    */
    public static int right(int i){
        return (2*i) + 2;
    }
    /*
    * Helper method swaps two elements in array. 
    *
    * @param arr integer array that has values to swap.
    * @param a integer index of 1st element
    * @param b integer index of 2nd element
    */
    public static void swap (int [] arr, int a, int b) {
        int temp;
        temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
    /*
    * Intro to Algorithms 3rd Ed. implementation of Quick Sort.
    * Quicksort usually beats heapsort in practice, although worst case of Quick
    * Sort is O(n^2) Maybe implement partition demonstration for learning
    * later on.  Maybe cool to make web visuals for web site? 
    * 
    * @param arr integer array to be quicksorted
    * @param p integer starting index of recursive quicksort
    * @param r integer ending index of recursive quicksort
    */
    public static void quickSort(int [] arr, int p, int r) {
        if(p < r) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q-1);
            quickSort(arr, q+1, r);
        }
    }
    /*
    * Key to quicksort, creates a pivot point (last element in this case) Then
    * splits array based on this pivot point if larger than pivot goes to right
    * else goes to left. i index keeps track of where partition is. 
    *
    * @param arr integer array to be quicksorted
    * @param p integer starting index of recursive quicksort
    * @param r integer index of value to partition array on
    * @returns integer where partition begins
    */
    public static int partition(int [] arr, int p, int r) {
        int x = arr[r];
        int i = p-1;
        for (int j = p; j <= r-1; j++) {
            if(arr[j]<=x) {
                i = i++;
                swap(arr, i, j);
            }
            
        }
        swap(arr,i+1,r);
        return i+1;
    }
    /*
    * Key to kthselection, creates a pivot point (median of median in this case) Then
    * splits array based on this pivot point if larger than pivot goes to right
    * else goes to left. i index keeps track of where partition is. Needs extra
    * parameter compared to quicksort partition method in order to find element 
    * to partition on. 
    *
    * @param arr integer array to be quicksorted
    * @param p integer starting index of recursive quicksort
    * @param r integer index of value to partition array on
    * @param x integer to search for in order to partition around places x value
    *          in correct spot in array.
    * @returns integer where partition begins
    */
    public static int kthpartition(int [] arr, int p, int r, int x) {
        boolean found = false;
        int k = 0;
        while(!found) {
            if(arr[k]==x) {
                swap(arr, k, r);
                found=true;
            }
            k++;
        }
        
        int i = p;
        for (int j = p; j <= r-1; j++) {
            if(arr[j]<=x) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr,i,r);
        return i;
    }
    /*
    * Very simple O(n^2) sorting method for comparison to other sorting methods. 
    *
    * @param arr integer array to be insertion sorted
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
    /*
    * Intro to Algorithms 3rd Ed. implementation of Counting Sort.
    * Non-comparison based sorting method so is not limited by O(nlogn). 
    * Sort is O(n). Creates count of integers using count array. Then uses copy
    * of original array to place each value in its correct position. 
    * 
    * @param arr integer array to be counting sorted
    */
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
    /*
    * Intro to Algorithms 3rd Ed. implementation of radix Sort.
    * Non-comparison based sorting method so is not limited by O(nlogn). 
    * Sort is O(n). Uses counting sort. Sorts on individual decimal place starting
    * at least significant. 
    * 
    * @param arr integer array to be counting sorted
    * @param d integer value of largest number of digits in single number in arr
    */
    public static void radixSort(int []arr, int d) {
        for (int i = 1; i <= d; i++) {
            int decimal = (int)Math.pow(10,i);
            countingSortRadix(arr, decimal);
        }
    }
    /*
    * Intro to Algorithms 3rd Ed. implementation of counting Sort.
    * Non-comparison based sorting method so is not limited by O(nlogn). 
    * Sort is O(n). Counting sort but only sorts on specified decimal place
    * 
    * @param arr integer array to be counting sorted
    * @param decimal integer specified decimal place
    */
    public static void countingSortRadix(int []arr, int decimal) {
        int divideBy = decimal/10; //sort only on decimal place.
        int [] copy = Arrays.copyOf(arr, arr.length);
        int [] count =  new int [10];
        for (int i = 0; i < copy.length; i++) {
            count[(copy[i]%decimal)/ divideBy]++;
        }
        for (int k = 1; k <count.length; k++) {
            count[k] = count[k]+count[k-1];
        }
        for( int j = arr.length-1; j >=0; j--) {
            
            arr[count[(copy[j]%decimal)/divideBy]-1]=copy[j];
            count[(copy[j]%decimal)/divideBy]= count[(copy[j]%decimal)/divideBy]-1;
        }
        
    }
    /*
    * Intro to Algorithms 3rd Ed. implementation of bucket Sort.
    * Non-comparison based sorting method so is not limited by O(nlogn). 
    * Sort is O(n). Bucket sort creates buckets of values that are close
    * places them in a linked list (bucket) then sorts the buckets. Uses double
    * array though probably equally effective on integer array. 
    * 
    * @param arr double array to be bucket sorted
    * @param numOfBuckets to separate values into. 
    */
    public static void bucketSort(double []arr, int numOfBuckets) {
        LinkedList [] bucket = new LinkedList[numOfBuckets];
        for (int j = 0; j < numOfBuckets; j++) {
            bucket[j] = new LinkedList<>();
        }
        for (int i = 0; i < arr.length; i++) {
            bucket[(int)(numOfBuckets*arr[i])].add(arr[i]);
        }
        for (int j = 0; j < bucket.length; j++) {
            if(!bucket[j].isEmpty()) {
                     Collections.sort(bucket[j]);
            }
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (!bucket[j].isEmpty()) {
                arr[i] = (double) bucket[j].remove();
                i++;
            }
        }
    }
    /*
    * Speed test that compares sorting time of same array using different methods/
    * Prints results in nanoseconds. 
    * 
    * @param arr int array to be sorted by multiple methods 
    */
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
    * Selects the kth smallest value. Finds median of medians to partition array
    * then searches for k value in partition that it belongs to. This is a worst
    * case linear time search. 
    *
    * @param arr integer array to select from
    * @param l integer start of recursive partition
    * @param r integer end of recursive partion
    * @param k kth largest value in array to select.
    * @return kth largest value
    */
    public static int kthSelection (int [] arr, int l, int r, int k) {
        int pos;
        int n = r - l +1; //number of elements in arr[l..r]
        int partitions = n/5;
        int [] medians = new int[(n+4)/5];
        int median;
        for (int i =0; i<partitions; i++) {
            medians[i]= findMedian(Arrays.copyOfRange(arr, l+(5*i),(l+(5*i))+5));
        }
        if(n%5>0) {
            medians[partitions]=findMedian(Arrays.copyOfRange(arr, l+(partitions*5),r+1));
        }
        median=findMedian(medians);
        pos = kthpartition(arr, l, r, median);
        if(pos-l==k-1) {
            return arr[pos];
        }
        if(pos-l>k-1) {
            return kthSelection(arr,l,pos-1,k);
        }
        else {
            return kthSelection(arr,pos+1,r, k-pos+l-1);
        }
    }        
        
        
    /*
    * Finds median of array by sorting using insertion sort then returning
    * halfway point. Only used in kthSelection on size 5 arrays or smaller. 
    *
    * @param arr integer array to find median
    * @return median integer value
    */
    public static int findMedian(int[]arr) {
        insertionSort(arr);
        return arr[arr.length/2];
    }
    /*
    * Prints array. 
    * 
    * @param arr integer array to be printed
    */
    public static void printArray(int[]arr) {
       System.out.print("[");
        for (int i: arr){
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
    /*
    * Prints array. 
    * 
    * @param arr double array to be printed
    */
    public static void printArray(double[]arr) {
       System.out.print("[");
        for (double i: arr){
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
    /*
    * populates array with distinct elements with integers up to two times larger
    * then size of array. 
    * 
    * @param int array to be populated
    */
    public static void distinctArray(int [] arr) {
        ArrayList<Integer> list = new ArrayList<>(2*arr.length);
        for (int i =0; i < 2*arr.length; i++) {
            list.add(i);
        }
        for (int j = 0; j < arr.length; j++) {
            arr[j] = list.remove((int)(Math.random()*list.size()));
        }
    }
    /*
    * populates array with random elements
    * 
    * @param int max of values in array
    * @param int array to be populated
    */
    public static void populateArray(int [] arr, int max) {
        int n = arr.length;
        for (int i = 0; i < n; i++){
            arr[i]=(int)(Math.random()*max);
        }
    }
    /*
    * populates array with random elements
    * 
    * @param max double max size of values in array
    * @param double array to be populated
    */
    public static void populateArray( double [] arr, double max) {
        int n = arr.length;
        for (int i = 0; i < n; i++){
            arr[i]=Math.random()*max;
        }
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