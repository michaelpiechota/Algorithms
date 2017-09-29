//Michael Piechota 09/26/2017
//COMP 482 Project 1: Merge Sort and Quick Sort Comparison
//Sorts.java - contains sort functions

public class Sorts{

    //returns true only if a is sorted from smallest to largest values
    public static boolean isSorted(int[] a)
    {
        for(int i=0; i<a.length-1; i++) {
            if(a[i] > a[i+1]) {
                return false;
            }
        }
        System.out.println("\nisSorted status: True");
        return true;
    }


    /* --------------------Merge Sort --------------------*/
    //merges sorted slices a[i.. j] and a[j + 1 …  k] for 0<=  i <=j < k < a.length
    public static void merge(int[] array, int leftArray, int middleOfArray , int rightArray)
    {
        //First, find the sizes of the 2 arrays that need to be merged
        //then create temp arrays for left and right side of array
        int sizeOf_leftArray = middleOfArray - leftArray + 1;
        int sizeOf_rightArray = rightArray - middleOfArray;
        int leftTempArray[] = new int [sizeOf_leftArray];
        int rightTempArray[] = new int [sizeOf_rightArray];

        //fill left array
        for (int i=0; i<sizeOf_leftArray; ++i)
            leftTempArray[i] = array[leftArray + i];

        //fill right array
        for (int j=0; j<sizeOf_rightArray; ++j)
            rightTempArray[j] = array[middleOfArray + 1 + j];


        //initializing indexes of sub arrays
        int i = 0;
        int j = 0;
        int initIndex = leftArray; //initial index

        while (i < sizeOf_leftArray && j < sizeOf_rightArray) {
            if (leftTempArray[i] <= rightTempArray[j]) {
                array[initIndex] = leftTempArray[i];
                i++;
            }
            else {
                array[initIndex] = rightTempArray[j];
                j++;
            }
            initIndex++;
        }

        //copying elements
        while (i < sizeOf_leftArray) {
            array[initIndex] = leftTempArray[i];
            i++;
            initIndex++;
        }
        //copying elements
        while (j < sizeOf_rightArray) {
            array[initIndex] = rightTempArray[j];
            j++;
            initIndex++;
        }
    }


    //sorts  a[ i .. k]  for 0<=i <= k < a.length
    public static void mergeSort(int[] a,  int i ,  int k)
    {
        if (i < k) {
            int m = (i+k)/2; //this is the middle
            mergeSort(a, i, m);
            mergeSort(a, m+1, k);
            merge(a, i, m, k);
        }
    }

    
    //Sorts the array using mergesort
    public static void mergeSort(int[] a )
    {
        mergeSort(a,0,a.length-1);
    }


    /*------------------ QuickSort ----------------------------------------------------------*/
    //implements in-place partition from text. Partitions subarray s[a..b] into s[a..1-1] and s[1+1..b]
    //so that all elements of s[a..1-1] are less than each element in s[1+1..b]
    public static int partition(int s[], int a, int b)
    {
        int pivot = s[b];
        int left = a;
        int right = b - 1;

        while(left <= right){

            while(left <= right && s[left] <= pivot){
                left = left + 1;
            }

            while(right >= left && s[right] >= pivot){
                right = right - 1;
            }

            if(left < right){
                int leftValue = s[left];
                int rightValue = s[right];
                s[left] = rightValue;
                s[right] = leftValue;
            }
            else{
                int leftValue = s[left];
                int midValue = s[b];
                s[left] = midValue;
                s[b] = leftValue;
            }
        }
        return left;
    }

    
    //quick sorts subarray a[i..j]
    public static void quickSort(int a[], int i, int j)
    {
        if(i < j){
            int part = partition(a, i, j);
            quickSort(a, i, (part-1));
            quickSort(a, (part+1), j);
        }
    }


    //quick sorts array a
    public static void quickSort(int a[])
    {
        int zero = 0;
        int length = (a.length - 1);
        if(zero  < length){
            int part = partition(a, zero, length);
            quickSort(a, zero, (part-1));
            quickSort(a, (part+1), length);
        }
    }
    
    
}//end Sorts class
