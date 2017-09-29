//Michael Piechota 09/26/2017
//COMP 482 Project 1: Merge Sort and Quick Sort Comparison
//Driver file

import java.util.Random;
import java.util.Scanner;

public class SortTester extends Sorts{

    //function to compare quick sort and merge sort
    public static void compareSorts()
    {
        Sorts sorts = new Sorts();
        
        int trials = 20;
        //n will be received via scanner. n is the array size.
        int n;

        //scanner to get array size
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the size of the array: ");
        while (!scanner.hasNextInt()) scanner.next();
        n = scanner.nextInt();

        int numOf_quickSortWins = 0;
        int numOf_mergeSortWins = 0;
        long quickSort_average = 0;
        long mergeSort_average = 0;
        long quickSort_nlognCalc = 0;
        long mergeSort_nlognCalc = 0;

        //run 20 trials
        for(int j=0; j<20; j++){
            int[] arrayForQS = new int[n];
            int[] arrayForMS = new int[n];
            
            for (int i=0; i<n; i++){
                int arrElement = (int)(Math.random()*1000000 + 1);
                arrayForQS[i] = arrElement;
                arrayForMS[i] = arrElement;
            }

            //timed quickSort
            long QS_timeStart = System.nanoTime();
            sorts.quickSort(arrayForQS);
            long QS_timeEnd = System.nanoTime();

            //timed megeSort
            long MS_timeStart = System.nanoTime();
            sorts.mergeSort(arrayForMS);
            long MS_timeEnd = System.nanoTime();

            //calculations of mergeSort and quickSort times
            long quickTime = QS_timeEnd - QS_timeStart;
            quickSort_average += quickTime;
            long mergeTime = MS_timeEnd - MS_timeStart;
            mergeSort_average += mergeTime;

            //keeping track of which sort wins
            if (quickTime < mergeTime){
                numOf_quickSortWins++;
            } else {
                numOf_mergeSortWins++;
            }

        }

        //calculating averages
        quickSort_average = quickSort_average/trials;
        mergeSort_average = mergeSort_average/trials;

        //calculating avg/nlogn
        quickSort_nlognCalc = quickSort_average/(n*((long)(Math.log(n) / Math.log(2))));
        mergeSort_nlognCalc = mergeSort_average/(n*((long)(Math.log(n) / Math.log(2))));

        //CASE: Merge Sort Wins
        if (numOf_quickSortWins < numOf_mergeSortWins){
            System.out.println("\nMerge Sort WINS!!!\n");
            //display # of wins
            System.out.println("# of Quick Sort wins: "+numOf_quickSortWins);
            System.out.println("# of Merge Sort wins: "+numOf_mergeSortWins);
            //mergeSort times:
            System.out.println("The average runtime for Merge Sort is: \n"+mergeSort_average+" nanoseconds");
            System.out.println("Merge Sort avgRunTime/nlogn is: \n"+mergeSort_nlognCalc+" nanoseconds/n(log(n))");
            //quickSort times:
            System.out.println("The average runtime for Quick Sort is: \n"+quickSort_average+" nanoseconds");
            System.out.println("Quick Sort avgRunTime/nlogn is: \n"+quickSort_nlognCalc+" nanoseconds/n(log(n))");

        }
        //CASE: Quick Sort Wins
        else {
            System.out.println("\nQuick Sort WINS!!!\n");
            //display # of wins
            System.out.println("# of Quick Sort wins: "+numOf_quickSortWins);
            System.out.println("# of Merge Sort wins: "+numOf_mergeSortWins);
            //mergeSort times:
            System.out.println("The average runtime for Merge Sort is: \n"+mergeSort_average+" nanoseconds");
            System.out.println("Merge Sort avgRunTime/nlogn is: \n"+mergeSort_nlognCalc+" nanoseconds/n(log(n))");
            //quickSort times:
            System.out.println("The average runtime for Quick Sort is: \n"+quickSort_average+" nanoseconds");
            System.out.println("Quick Sort avgRunTime/nlogn is: \n"+quickSort_nlognCalc+" nanoseconds/n(log(n))");
        }
    }


    //utility function fulfilling spec on instructions
    //tests both sorts with hardcoded arrays.
    public static void testArrays()
    {

        Sorts sorts = new Sorts();

        //hard coded arrays 
        //used 2 arrays respectively for my own testing.
        int[] qS_array = {34, 67, 23, 19, 122, 300, 2, 5, 17, 18, 5, 4,  3,  19, -40, 23};
        int[] mS_array = {34, 67, 23, 19, 122, 300, 2, 5, 17, 18, 5, 4,  3,  19, -40, 23};

        //show arrays in console 
        System.out.println("The following array will be sorted: ");
        printArray(mS_array);

        //sort array using BOTH sorts
        sorts.quickSort(qS_array);
        sorts.mergeSort(mS_array);

        if(Sorts.isSorted(qS_array) == true) {
            //display sorted arrays in console
            System.out.println("Quick Sorted Array: ");
            printArray(qS_array);
        }
        else{
            System.out.println("Quick Sort array is NOT sorted!");
        }

        if(Sorts.isSorted(mS_array) == true){
            System.out.println("Merge Sorted Array: ");
            printArray(mS_array);
        }
        else{
            System.out.println("Merge Sort array is NOT sorted!");
        }


    }


    //function needed to print arrays
    public static void printArray(int array[]){
        int n = array.length;
        for (int i=0; i<n; ++i)
            System.out.print(array[i]+" ");
        System.out.println();
    }


    //Driver
    public static void main(String args[]){
        //using scanner to input a choice so I can do both tests.
        int input;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 1 to compare or 2 to test: ");

        while (!scanner.hasNextInt()) scanner.next();
        input = scanner.nextInt();

        //compareSorts runs the qS vs mS comparison code
        //testArrays tests the hard coded arrays
        if(input == 1){
            compareSorts();
        }
        else if(input == 2){
            testArrays();
        }
    }


}//END SortTester class
