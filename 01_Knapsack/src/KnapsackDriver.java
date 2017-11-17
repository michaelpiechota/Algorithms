//Michael Piechota 11/09/2017
//COMP 482 Project 2: 0-1 Knapsack Analysis
//KnapsackDriver.java - Knapsack Driver program used for analysis.
import java.util.*;

class KnapsackDriver {
    public static void main(String arg[]) {

        /******************************** TEST CASES ********************************/
        //Instructor's Test Case #1 
        int n = 7;
        int[] weights = {-1, 60, 50, 60, 50, 70, 70, 45};
        int W = 100;
        int[] benefits = {-1, 180, 95, 40, 95, 40, 40, 105};
        Knapsack knapsackCase1 = new Knapsack(W, weights, benefits);

        //Instructor'sTest Case #2 
        int n2 = 18;
        int[] weights2 = {-1,25,4,2,5,6, 2,7,8,2,1, 1,3,5,8,9,  6,3,2};
        int W2 = 39;
        int[] benefits2 = {-1,75,7,4,3,2,  6,8,7,9,6,  5,4,8,10,8,  1,2,2};
        Knapsack knapsackCase2 = new Knapsack(W2, weights2, benefits2);

        //Instructor's Test Case #3 
        int n3 = 20;
        int[] weights3 = {-1, 10,14,35,12,16, 20,13,7,2,4, 3,10,5,6,17,7,9,3,4,3};
        int W3 = 29;
        int[] benefits3 = {-1, 2,13,41,1,12, 5,31,2,41,16,2,12,1,13,4, 51,6,12,1,9};
        Knapsack knapsackCase3 = new Knapsack(W3, weights3, benefits3);

        //Instructor's Test Case #4
        int n4 = 7;
        int[] weights4 = {-1, 2,5,3,2,5,3,7 };
        int W4 = 10;
        int[] benefits4 = {-1, 5,10,5,20,15,5,10};
        Knapsack knapsackCase4= new Knapsack(W4, weights4, benefits4);

        //Online Test Case #5 (FROM GOOGLE)
        int n5 = 24;
        int[] weights5 ={ 382745, 799601, 909247, 729069, 467902, 44328, 34610, 698150, 823460, 903959, 853665, 551830,
                610856, 670702, 488960, 951111, 323046, 446298, 931161, 31385, 496951, 264724, 224916, 169684 };
        int W5 = 6404180;
        int[] benefits5 = {825594, 1677009, 1676628,1523970, 943972, 97426, 69666, 1296457, 1679693, 1902996, 1844992,
                1049289, 1252836, 1319836, 953277, 2067538, 675367, 853655, 1826027, 65731, 901489, 577243, 466257,
                369261};
        Knapsack knapsackCase5 = new Knapsack(W5, weights5, benefits5);


        /******************************** TEST RUNS ********************************/
        //Case #1
        System.out.println("Test Case #1\nKnapsack Problem Instance");
        System.out.println("Number of items = " + n +" Knapsack Capacity = " + W);
        System.out.println("Input weights: " + Arrays.toString(weights));
        System.out.println("Input benefits: " + Arrays.toString(benefits) + "\n");

        //Case #1 Brute Force
        double case1BruteStart = System.nanoTime();
        knapsackCase1.BruteForceSolution();
        double case1BruteEnd = System.nanoTime();
        double case1BruteTime = case1BruteEnd - case1BruteStart;
        System.out.println("Time Taken: " + case1BruteTime + " nanoseconds");
        System.out.println();

        //Case #1 Dynamic
        double case1DynamicStart = System.nanoTime();
        knapsackCase1.DynamicProgrammingSolution(false);
        double case1DynamicEnd = System.nanoTime();
        double case1DynamicTime = case1DynamicEnd - case1DynamicStart;
        System.out.println("Time Taken: " + case1DynamicTime + " nanoseconds");
        System.out.println();

        //Case #1 Greedy
        double case1GreedyStart = System.nanoTime();
        knapsackCase1.GreedyApproximateSolution();
        double case1GreedyEnd = System.nanoTime();
        double case1GreedyTime = case1GreedyEnd - case1GreedyStart;
        System.out.println("Time Taken: " + case1GreedyTime + " nanoseconds");
        System.out.println();
        System.out.println();



        //Case #2
        System.out.println("Test Case #2\nKnapsack Problem Instance");
        System.out.println("Number of items = " + n2 +" Knapsack Capacity = " + W2);
        System.out.println("Input weights: " + Arrays.toString(weights2));
        System.out.println("Input benefits: " + Arrays.toString(benefits2) + "\n");

        //Case #2 Brute Force
        double case2BruteStart = System.nanoTime();
        knapsackCase2.BruteForceSolution();
        double case2BruteEnd = System.nanoTime();
        double case2BruteTime = case2BruteEnd - case2BruteStart;
        System.out.println("Time Taken: " + case2BruteTime + " nanoseconds");
        System.out.println();

        //Case #2 Dynamic
        double case2DynamicStart = System.nanoTime();
        knapsackCase2.DynamicProgrammingSolution(false);
        double case2DynamicEnd = System.nanoTime();
        double case2DynamicTime = case2DynamicEnd - case2DynamicStart;
        System.out.println("Time Taken: " + case2DynamicTime + " nanoseconds");
        System.out.println();

        //Case #2 Greedy
        double case2GreedyStart = System.nanoTime();
        knapsackCase2.GreedyApproximateSolution();
        double case2GreedyEnd = System.nanoTime();
        double case2GreedyTime = case2GreedyEnd - case2GreedyStart;
        System.out.println("Time Taken: " + case2GreedyTime + " nanoseconds");
        System.out.println();
        System.out.println();



        //Case #3
        System.out.println("Test Case #3\nKnapsack Problem Instance");
        System.out.println("Number of items = " + n3 +" Knapsack Capacity = " + W3);
        System.out.println("Input weights: " + Arrays.toString(weights3));
        System.out.println("Input benefits: " + Arrays.toString(benefits3) + "\n");

        //Case #3 Brute Force
        double case3BruteStart = System.nanoTime();
        knapsackCase3.BruteForceSolution();
        double case3BruteEnd = System.nanoTime();
        double case3BruteTime = case3BruteEnd - case3BruteStart;
        System.out.println("Time Taken: " + case3BruteTime + " nanoseconds");
        System.out.println();

        //Case #3 Dynamic
        double case3DynamicStart = System.nanoTime();
        knapsackCase3.DynamicProgrammingSolution(false);
        double case3DynamicEnd = System.nanoTime();
        double case3DynamicTime = case3DynamicEnd - case3DynamicStart;
        System.out.println("Time Taken: " + case3DynamicTime + " nanoseconds");
        System.out.println();

        //Case #3 Greedy
        double case3GreedyStart = System.nanoTime();
        knapsackCase3.GreedyApproximateSolution();
        double case3GreedyEnd = System.nanoTime();
        double case3GreedyTime = case3GreedyEnd - case3GreedyStart;
        System.out.println("Time Taken: " + case3GreedyTime + " nanoseconds");
        System.out.println();
        System.out.println();



        //Case #4
        System.out.println("Test Case #4\nKnapsack Problem Instance");
        System.out.println("Number of items = " + n4 +" Knapsack Capacity = " + W4);
        System.out.println("Input weights: " + Arrays.toString(weights4));
        System.out.println("Input benefits: " + Arrays.toString(benefits4) + "\n");

        //Case #4 Brute Force
        double case4BruteStart = System.nanoTime();
        knapsackCase4.BruteForceSolution();
        double case4BruteEnd = System.nanoTime();
        double case4BruteTime = case4BruteEnd - case4BruteStart;
        System.out.println("Time Taken: " + case4BruteTime + " nanoseconds");
        System.out.println();

        //Case #4 Dynamic
        double case4DynamicStart = System.nanoTime();
        knapsackCase4.DynamicProgrammingSolution(true);
        double case4DynamicEnd = System.nanoTime();
        double case4DynamicTime = case4DynamicEnd - case4DynamicStart;
        System.out.println("Time Taken: " + case4DynamicTime + " nanoseconds");
        System.out.println();

        //Case #4 Greedy
        double case4GreedyStart = System.nanoTime();
        knapsackCase4.GreedyApproximateSolution();
        double case4GreedyEnd = System.nanoTime();
        double case4GreedyTime = case4GreedyEnd - case4GreedyStart;
        System.out.println("Time Taken: " + case4GreedyTime + " nanoseconds");
        System.out.println();
        System.out.println();


        /*
        //Case #5 FROM GOOGLE
        System.out.println("Test Case #5\nKnapsack Problem Instance");
        System.out.println("Number of items = " + n5 +" Knapsack Capacity = " + W5);
        System.out.println("Input weights: " + Arrays.toString(weights));
        System.out.println("Input benefits: " + Arrays.toString(benefits) + "\n");

        //Case #5 Brute Force
        double case5BruteStart = System.nanoTime();
        knapsackCase5.BruteForceSolution();
        double case5BruteEnd = System.nanoTime();
        double case5BruteTime = case5BruteEnd - case5BruteStart;
        System.out.println("Time Taken: " + case5BruteTime + " nanoseconds");
        System.out.println();

        //Case #5 Dynamic
        double case5DynamicStart = System.nanoTime();
        knapsackCase5.DynamicProgrammingSolution(false);
        double case5DynamicEnd = System.nanoTime();
        double case5DynamicTime = case5DynamicEnd - case5DynamicStart;
        System.out.println("Time Taken: " + case5DynamicTime + " nanoseconds");
        System.out.println();

        //Case #5 Greedy
        double case5GreedyStart = System.nanoTime();
        knapsackCase5.GreedyApproximateSolution();
        double case5GreedyEnd = System.nanoTime();
        double case5GreedyTime = case5GreedyEnd - case5GreedyStart;
        System.out.println("Time Taken: " + case5GreedyTime + " nanoseconds");
        System.out.println();
        System.out.println();
        */


    }


}