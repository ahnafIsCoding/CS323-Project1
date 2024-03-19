
/**
 * Sorting Algorithm Analysis Project
 * CS 323
 * Prof. Anne Smith
 * Spring 2024
 * @author Ahnaf Ahmed
 */

// imported libraries
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
   public static void main(String[] args) {

      /**
       * 1. Comparisons of Clock Time for Sorting Algorithms (large arrays): a)
       * generate an array of 10,000 random integers, with values between 1 and
       * 1,000,000
       */

      System.out.println("1. Comparisons of Clock Time for Sorting Algorithms (large arrays):\n");

      // large array with size 10,000
      int[] largeArr = createArr(10000, 1000000);

      System.out.println("Array size: " + largeArr.length); // 10000

      // for each sort
      int no_of_simulations;

      /**
       * 1. b) “Sort” the array using:
       */

      // running once
      no_of_simulations = 1;

      sort(largeArr, "Heap Sort", no_of_simulations);
      sort(largeArr, "Modified Heap Sort", no_of_simulations);
      sort(largeArr, "Basic Quick Sort", no_of_simulations);
      sort(largeArr, "Protected Quick Sort", no_of_simulations);

      /**
       * 2.RUN the sorting algorithms a total of 100 times each, and display the
       * average clock time used by each of the algorithms.
       */

      // running 100 times
      no_of_simulations = 100;

      sort(largeArr, "Heap Sort", no_of_simulations);
      sort(largeArr, "Modified Heap Sort", no_of_simulations);
      sort(largeArr, "Basic Quick Sort", no_of_simulations);
      sort(largeArr, "Protected Quick Sort", no_of_simulations);

      /**
       * 2. c) Run the same programs, but increase the array size to 100,000.
       */

      // large array with size 100,000
      int[] largerArr = createArr(100000, 1000000);

      System.out.println("\nArray size: " + largerArr.length); // 100000

      // running once
      no_of_simulations = 1;

      sort(largerArr, "Heap Sort", no_of_simulations);
      sort(largerArr, "Modified Heap Sort", no_of_simulations);
      sort(largerArr, "Basic Quick Sort", no_of_simulations);
      sort(largerArr, "Protected Quick Sort", no_of_simulations);

      // running 100 times
      no_of_simulations = 100;

      sort(largerArr, "Heap Sort", no_of_simulations);
      sort(largerArr, "Modified Heap Sort", no_of_simulations);
      sort(largerArr, "Basic Quick Sort", no_of_simulations);
      sort(largerArr, "Protected Quick Sort", no_of_simulations);

      /**
       * For one “run” only, run QuickSort - BASIC and HeapSort on a sorted array.
       */

      System.out.println("\nRunning QuickSort - BASIC and HeapSort on a sorted array:");

      no_of_simulations = 1;

      // Sort original array
      Arrays.sort(largeArr);

      // size 100,000 blows up the stack for Quick Sort
      // Arrays.sort(largerArr);

      sort(largeArr, "Heap Sort", no_of_simulations);
      sort(largeArr, "Basic Quick Sort", no_of_simulations);

      // size 100,000 is commented out for compilation of the program
      // sort(largerArr, "Heap Sort", no_of_simulations);
      // sort(largerArr, "Basic Quick Sort", no_of_simulations);

      /**
       * 2. Comparisons of Clock Time for Sorting Algorithms (small arrays): a)
       * generate an arrays of random integers, with values between 1 and 1000, with
       * sizes 16, 32, 64, 128, and 256.
       */

      System.out.println("\n2. Comparisons of Clock Time for Sorting Algorithms (small arrays):");

      // small array with size 16
      int[] smallArr16 = createArr(16, 1000);

      // small array with size 32
      int[] smallArr32 = createArr(32, 1000);

      // small array with size 64
      int[] smallArr64 = createArr(64, 1000);

      // small array with size 128
      int[] smallArr128 = createArr(128, 1000);
      // small array with size 256
      int[] smallArr256 = createArr(256, 1000);

      List<int[]> smallArrays = new ArrayList<int[]>();
      smallArrays.add(smallArr16);
      smallArrays.add(smallArr32);
      smallArrays.add(smallArr64);
      smallArrays.add(smallArr128);
      smallArrays.add(smallArr256);

      /**
       * d) RUN the sorting algorithms a total of 100 times each, and display the
       * average clock time used by each of the algorithms.
       */

      no_of_simulations = 100;

      /**
       * b) “Sort” the arrays using:
       */

      for (int[] smallArr : smallArrays) {
         System.out.println("\nArray size: " + smallArr.length);
         sort(smallArr, "Insertion Sort", no_of_simulations);
         sort(smallArr, "Heap Sort", no_of_simulations);
         sort(smallArr, "Basic Quick Sort", no_of_simulations);
      }
   }

   /**
    * Sort wrapper function that calculates and displays time
    * 
    * @param originalArr
    * @param sortName
    * @param n
    */
   public static void sort(int[] originalArr, String sortName, int n) {
      long total_comparison = 0, total_swap = 0;
      double total_time = 0; // To accumulate total time

      for (int i = 0; i < n; i++) {

         // clone the original array for each run
         int[] arr = originalArr.clone();
         // reset counter for each run
         Counter counter = new Counter();

         double start_time = 0, end_time = 0;

         switch (sortName) {
         case "Heap Sort":
            start_time = System.nanoTime();
            counter = HeapSort.regularSort(arr);
            end_time = System.nanoTime();
            break;
         case "Modified Heap Sort":
            start_time = System.nanoTime();
            counter = HeapSort.modifiedSort(arr);
            end_time = System.nanoTime();
            break;
         case "Basic Quick Sort":
            start_time = System.nanoTime();
            counter = QuickSort.basicSort(arr);
            end_time = System.nanoTime();
            break;
         case "Protected Quick Sort":
            start_time = System.nanoTime();
            counter = QuickSort.protectedSort(arr);
            end_time = System.nanoTime();
            break;
         case "Insertion Sort":
            start_time = System.nanoTime();
            counter = InsertionSort.sort(arr);
            end_time = System.nanoTime();
         default:
            Arrays.sort(arr);
            break;
         }

         double time = end_time - start_time;

         // accumulate totals
         total_time += time;
         total_comparison += counter.comparison;
         total_swap += counter.swap;
      }

      /**
       * 1. d) Include counters for the number of comparisons and the number of
       * “swaps” (“bubbles”) used in each algorithm, calculate average values and
       * discuss the results.
       */

      // calculate averages
      double average_time = total_time / n;
      long average_comparison = total_comparison / n;
      long average_swap = total_swap / n;

      // display time
      System.out.println("'" + sortName + "' " + (n == 1 ? "Total" : "AVERAGE") + " Clock Time: " + average_time
            + " ns ; Comparisons: " + average_comparison + " ; Swaps: " + average_swap);
   }

   /**
    * Array creator
    * 
    * @param size
    * @param max
    * @return
    */
   public static int[] createArr(int size, int max) {

      // random integer generator
      Random rand = new Random();

      int[] newArr = new int[size];
      // assigning values between 1 and max
      for (int i = 0; i < newArr.length; i++) {
         newArr[i] = rand.nextInt(max);
      }

      return newArr;
   }
}