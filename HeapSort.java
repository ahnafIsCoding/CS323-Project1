
/**
 * Heap Sort class
 * @author Ahnaf Ahmed
 */

// imported libraries
import java.util.ArrayList;
import java.util.List;

public class HeapSort {

   /**
    * Regular Heap sort (compares only with left and right children)
    * 
    * @param A
    * @return
    */
   public static Counter regularSort(int[] A) {

      Counter counter = new Counter();

      buildMaxHeap(A, counter, false);

      int n = A.length;
      for (int i = n - 1; i > 0; i--) {
         counter.swap++;
         // delete max
         Helper.swap(A, 0, i);
         // partition partially sorted array
         n--;
         heapify(A, 0, n, counter, false);
      }
      return counter;
   }

   /**
    * Modified Heap sort (compares with all 4 grand children)
    * 
    * @param A
    * @return
    */
   public static Counter modifiedSort(int[] A) {

      Counter counter = new Counter();

      buildMaxHeap(A, counter, true);

      int n = A.length;
      for (int i = n - 1; i > 0; i--) {
         counter.swap++;
         // delete max
         Helper.swap(A, 0, i);
         // partition partially sorted array
         n--;
         heapify(A, 0, n, counter, true);
      }
      return counter;
   }

   /**
    * Keeps the heap organized by percolating down elements
    * 
    * @param A
    * @param i
    * @param n
    * @param counter
    * @param modify
    */
   private static void heapify(int[] A, int i, int n, Counter counter, Boolean modify) {

      int max = i;

      // children of the subtree
      int leftChild = 2 * i + 1;
      int rightChild = leftChild + 1;

      List<Integer> subtree = new ArrayList<Integer>();

      if (modify) {
         // grand children of the subtree
         int leftGrandChild1 = leftChild * 2 + 1;
         int leftGrandChild2 = leftGrandChild1 + 1;
         int rightGrandChild1 = rightChild * 2 + 1;
         int rightGrandChild2 = rightGrandChild1 + 1;

         subtree.add(leftGrandChild1);
         subtree.add(leftGrandChild2);
         subtree.add(rightGrandChild1);
         subtree.add(rightGrandChild2);
      }
      
      subtree.add(leftChild);
      subtree.add(rightChild);

      for (int child : subtree) {
         if (child < n) {
            counter.comparison++;
            if (A[child] > A[max]) {
               max = child;
            }
         }
      }

      if (max != i) {
         counter.swap++;
         Helper.swap(A, i, max);
         heapify(A, max, n, counter, modify);
      }
   }

   /**
    * Builds max heap with highest element at root
    * 
    * @param A
    * @param counter
    * @param modify
    */
   private static void buildMaxHeap(int[] A, Counter counter, Boolean modify) {

      for (int i = A.length / 2 - 1; i >= 0; i--)
         heapify(A, i, A.length, counter, modify);
   }
}
