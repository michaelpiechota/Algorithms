//Michael Piechota 11/09/2017
//COMP 482 Project 2: 0-1 Knapsack Analysis
//Knapsack.java - Contains Brute Force, Dynamic Programming, and Greedy (modified) Algorithms.
import java.util.*;

public class Knapsack {
  //knapsack specifications
  private int items;
  private double[][] itemList;
  private int capacity;
  private int[] weights;
  private int[] benefits;

  //knapsack constructor
  public Knapsack(int W, int[] w, int[] b) {
    this.weights = w;
    this.benefits = b;
    this.capacity = W;
    this.items = w.length;
    this.itemList = new double[this.items + 1][5];
  }

  /*
  A public static method with signature int[] generateSubset( int k, int n)
  that generates the kth  subset of {0, ...n-1}  where n>0 and 0<= k <= 2n -1
  and the kth subset is the binary representation of k using n bits.
  */
  public static int[] generateSubset(int k, int n){
    int[] kthSubset = new int[n + 1];
    int remainderValue = 0;
    int nValue = n;

    //checking size of k
    if(k >= Math.pow(2, n)){
      System.out.println("*WARNING* K is larger than 2^n - 1!");
    }

    while(k != 0 && nValue > 1){
      //instantiate remainder
      remainderValue = k % 2;
      k /= 2;

      if(remainderValue == 1){
        kthSubset[nValue] = remainderValue;
      } else{
        kthSubset[nValue] = remainderValue;
      }
      nValue--;
    }

    if(k == 1){
      kthSubset[nValue] = 1;
      nValue--;
    }

    //returns kth  subset of {0, ...n-1}
    return kthSubset;
  }

  /*
  A public method BruteForceSolution() that  generates and prints all optimal
  solutions of the KNP problem. Use your generateSubset method to generate all
  subsets of the input items. Test each subset for feasibility and optimality.
  */
  public void BruteForceSolution(){
    Knapsack bruteKnapsack = new Knapsack(this.capacity, this.weights, this.benefits);
    
    int maximumNumber = (int)Math.pow(2, bruteKnapsack.items) - 1;
    int[] binarySubset = new int[bruteKnapsack.items];
    
    double weightTally = 0;
    double benefitTally = 0;
    
    ArrayList<int[]> setArray = new ArrayList<int[]>();
    ArrayList<int[]> solutionArray = new ArrayList<int[]>();

    bruteKnapsack.generateSet(bruteKnapsack.items, bruteKnapsack.weights, bruteKnapsack.benefits);

    System.out.println("Brute Force Solution");
    
    //all binary subsets from 1 to maximumNumber are produced
    for(int i = 1; i <= maximumNumber; i++){
      binarySubset = bruteKnapsack.generateSubset(i, bruteKnapsack.items);
      weightTally = 0;
      benefitTally = 0;
      int[] solutionSubset = new int[bruteKnapsack.items + 3];
      
      for(int j = 0; j <= bruteKnapsack.items; j++){
        for(int k = 0; k <= bruteKnapsack.items; k++){
          bruteKnapsack.itemList[k][4] = binarySubset[k];
        }
        if(bruteKnapsack.itemList[j][4] == 1){
          weightTally += bruteKnapsack.itemList[j][1];
          benefitTally += bruteKnapsack.itemList[j][2];
        }
      }

      if(weightTally <= bruteKnapsack.capacity){
        for(int j = 0; j < bruteKnapsack.items + 1; j++){
          if(binarySubset[j] == 1){
            solutionSubset[j] = j;
          } else{
            solutionSubset[j] = 0;
          }
        }

        solutionSubset[bruteKnapsack.items + 1] = (int)weightTally;
        solutionSubset[bruteKnapsack.items + 2] = (int)benefitTally;

        setArray.add(solutionSubset);
      }
    }
    
    solutionArray.add(setArray.get(0));

    for(int i = 1; i < setArray.size(); i++){
      if(solutionArray.get(0)[bruteKnapsack.items + 2] < setArray.get(i)[bruteKnapsack.items + 2]){
        solutionArray.clear();
        solutionArray.add(setArray.get(i));
        i++;
      }
      else if(solutionArray.get(0)[bruteKnapsack.items + 2] == setArray.get(i)[bruteKnapsack.items + 2]){
        solutionArray.add(setArray.get(i));
        i++;
      }
    }

    for(int i = 0; i < solutionArray.size(); i++){
      ArrayList<Integer> optimalSolutionSubSet = new ArrayList<Integer>();
      //fill array
      for(int j = 0; j < bruteKnapsack.items; j++){
        if(solutionArray.get(i)[j] != 0){
          optimalSolutionSubSet.add(solutionArray.get(i)[j]);
        }
      }

      System.out.println("Optimal Set = "+optimalSolutionSubSet+" weight sum = "+
              solutionArray.get(i)[bruteKnapsack.items + 1]+" benefit sum = "+
              solutionArray.get(i)[bruteKnapsack.items + 2]);
    }
    
  }


  /*
  A public method DynamicProgrammingSolution( boolean printBmatrix)
  that generates and prints one optimal solution to the KNP problem.
  You must use the dynamic programming recurrence equations from class.
  */
  public void DynamicProgrammingSolution(boolean printBmatrix){
    Knapsack dynamicKnapsack = new Knapsack(this.capacity, this.weights, this.benefits);
    
    ArrayList<Integer> setArray = new ArrayList<Integer>();
    
    int weightTally = 0;
    int benefitTally = 0;
    
    dynamicKnapsack.generateSet(dynamicKnapsack.items, dynamicKnapsack.weights, dynamicKnapsack.benefits);

    int[][] printMatrix = new int[dynamicKnapsack.items + 1][dynamicKnapsack.capacity + 4];
    
    //generating the columns of the matrix
    for(int i = 0; i < 3; i++){
      printMatrix[0][i] = -1;
    }
    for(int i = 3; i < dynamicKnapsack.capacity + 4; i++){
      printMatrix[0][i] = i - 3;
    }

    //generating the rows of the matrix
    for(int i = 1; i < dynamicKnapsack.items + 1; i++){
      for(int j = 0; j < dynamicKnapsack.capacity + 4; j++){
        if(j == 0){
          //index rows
          printMatrix[i][0] = (int)dynamicKnapsack.itemList[i - 1][0];
        } else if(j == 1){
          //weight rows
          printMatrix[i][1] = (int)dynamicKnapsack.itemList[i - 1][1];
        } else if(j == 2){
          //benefit rows
          printMatrix[i][2] = (int)dynamicKnapsack.itemList[i - 1][2];
        }
        else {
          printMatrix[i][j] = 0;
        }
      }
    }

    //now fill the matrix for printing out
    for(int i = 2; i < dynamicKnapsack.items + 1; i++){
      for(int j = 4; j < dynamicKnapsack.capacity + 4; j++){
        if(printMatrix[i][1] > printMatrix[0][j]){
          printMatrix[i][j] = printMatrix[i - 1][j];
        } else {
          if(printMatrix[i][1] != -1){
            printMatrix[i][j] = Math.max(printMatrix[i - 1][j], printMatrix[i - 1][j - printMatrix[i][1]] + printMatrix[i][2]);
          }
          else {
            printMatrix[i][j] = Math.max(printMatrix[i - 1][j], printMatrix[i - 1][j] + printMatrix[i][2]);
          }
        }
      }
    }

    if(printBmatrix == true){
      System.out.println("Printed B Matrix:");
      for(int i = 0; i < dynamicKnapsack.items + 1; i++){
        for(int j = 0; j < dynamicKnapsack.capacity + 4; j++){
          System.out.print(printMatrix[i][j]);
          //separating entries with commas
          System.out.print(", ");
        }
        System.out.println("\n");
      }
    }

    //retrieve total benefit and weight then generate solution set
    benefitTally = (int)printMatrix[dynamicKnapsack.items][dynamicKnapsack.capacity + 3];
    for(int i = dynamicKnapsack.capacity + 3; i > 2; i--){
      if(benefitTally != printMatrix[dynamicKnapsack.items][i]){
        weightTally = printMatrix[0][i + 1];
        break;
      }
    }
    
    int weightCheck = dynamicKnapsack.capacity + 3;
    //setting up matrix to be printed
    outerloop:
    for(int i = dynamicKnapsack.items; i > 1; i--){
      if(printMatrix[i][weightCheck] > printMatrix[i - 1][weightCheck]){
        setArray.add(printMatrix[i][0]);
        weightCheck -= printMatrix[i][1];
      } else if(printMatrix[i][weightCheck] == 0){
        break outerloop;
      }
    }

    //printing out results
    System.out.println("Dynamic Programming Solution");
    System.out.println("Optimal Set = "+setArray+" weight sum = "+(int)weightTally+" benefit sum = "+(int)benefitTally);
    
  }

  /*
  A public method GreedyApproximateSolution() to generate
  and print an approximate solution to the KNP Problem using
  the following algorithm.Add items (whole items only) to the knapsack
  in decreasing  benefit/weight  ratio order. That is, add the highest
  benefit/weight ratio first. Add items as long as the sum of the weights
  of the added items <=W. This is a variation of the solution to the Fractional
  Knapsack Problem. Just don’t add in the last fractional item
  */
  public void GreedyApproximateSolution(){
    Knapsack greedyKnapsack = new Knapsack(this.capacity, this.weights, this.benefits);

    ArrayList<Integer> setArray = new ArrayList<Integer>();
    double weightTally = 0;
    double benefitTally = 0;

    greedyKnapsack.generateSet(greedyKnapsack.items, greedyKnapsack.weights, greedyKnapsack.benefits);

    sortWeights(greedyKnapsack.itemList);

    for(int i = greedyKnapsack.items; i > 0; i--){
      if(weightTally + greedyKnapsack.itemList[i][1] <= greedyKnapsack.capacity && greedyKnapsack.itemList[i][3] != 0){
        setArray.add((int)greedyKnapsack.itemList[i][0]);
        weightTally += greedyKnapsack.itemList[i][1];
        benefitTally += greedyKnapsack.itemList[i][2];
      }
    }

    System.out.println("Greedy Approximate Solution");
    System.out.println("Optimal Set = "+setArray+" weight sum = "+(int)weightTally+" benefit sum = "+(int)benefitTally);

  }

  /*******************************************Utility Functions*******************************************/
  //utility set generating function used in Brute/Dynamic/Greedy functions.
  //needed in all functions so modularized for cleaner code
  private void generateSet(int items, int[] w, int[] b){
    //the input array is indexed at 0
    this.itemList[0][0] = 0.0;
    this.itemList[0][1] = -1.0;
    this.itemList[0][2] = -1.0;
    this.itemList[0][3] = 0.0;
    this.itemList[0][4] = 0.0;
    //generating all arrays from i to n
    for(int i = 1; i < this.items; i++){
      this.itemList[i][0] = (double)i;
      this.itemList[i][1] = (double)w[i];
      this.itemList[i][2] = (double)b[i];
      this.itemList[i][3] = (double)(b[i]/(double)w[i]);
      this.itemList[i][4] = 0.0;
    }
  }

  //modified bubble sort to sort weights.
  //used in brute/greedy/dynamic solutions so decided to modularize
  //using bubble instead of merge sort due to ease of implementation and modification
  private static void sortWeights(double array[][]){
    int arrayLength = array.length;

    for (int i = 0; i < arrayLength-1; i++){
      for (int j = 0; j < arrayLength-i-1; j++){
        if (array[j][3] > array[j+1][3]){
          double[] temporaryArray = new double[array[j].length];

          //Using arraycopy: Copies an array from the specified source array,
          //beginning at the specified position, to the specified position of the destination array.
          System.arraycopy( array[j], 0, temporaryArray, 0, array[j].length);
          System.arraycopy( array[j + 1], 0, array[j], 0, array[j + 1].length);
          System.arraycopy( temporaryArray, 0, array[j + 1], 0, temporaryArray.length);
        }
      }
    }
  }


}