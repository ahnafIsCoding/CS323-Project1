
/**
 * Insertion Sort class
 * @author Ahnaf Ahmed
 *
 */
public class InsertionSort {

   /**
    * Insertion sort with only Array as input
    * 
    * @param A
    * @return
    */
   public static Counter sort(int[] A) {

      Counter counter = new Counter();

      for (int i = 1; i < A.length; i++) {

         int j = i;
         while (j > 0) {

            counter.comparison++;
            if (A[j - 1] > A[j]) {

               counter.swap++;
               Helper.swap(A, j - 1, j);
            } else
               break;
            j--;
         }
      }
      return counter;
   }

   /**
    * Insertion sort with Array, low, and high as input
    * 
    * @param A
    * @param low
    * @param high
    * @param counter
    * @return
    */
   public static Counter sort(int[] A, int low, int high, Counter counter) {

      for (int i = low; i <= high; i++) {

         int j = i;
         while (j > 0) {

            counter.comparison++;
            if (A[j - 1] > A[j]) {

               counter.swap++;
               Helper.swap(A, j - 1, j);
            } else
               break;
            j--;
         }
      }
      return counter;
   }
}
