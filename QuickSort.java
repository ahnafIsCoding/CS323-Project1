
/**
 * Quick Sort class
 * @author Ahnaf Ahmed
 *
 */
public class QuickSort {

   private static final int CUTOFF_SIZE = 5;

   /**
    * Basic Quick sort with no protection (pivot = first element in the partition)
    * 
    * @param A
    * @return
    */
   public static Counter basicSort(int[] A) {

      Counter counter = new Counter();

      quickSortByPartition(A, 0, A.length - 1, counter, false);

      return counter;
   }

   /**
    * Quick sort with protection (pivot = median of three {first,mid,last})
    * 
    * @param A
    * @return
    */
   public static Counter protectedSort(int[] A) {

      Counter counter = new Counter();

      quickSortByPartition(A, 0, A.length - 1, counter, true);

      return counter;
   }

   /**
    * Partitions each side of the pivot and calls itself recursively until it
    * reaches cutoff size
    * 
    * @param A
    * @param low
    * @param high
    * @param counter
    * @param protection
    */
   private static void quickSortByPartition(int[] A, int low, int high, Counter counter, Boolean protection) {

      if (high - low <= CUTOFF_SIZE) {
         InsertionSort.sort(A, low, high, counter);
      } else {

         int pivot_index;

         if (protection) {

            // median of 3 -> 6 comparisons
            counter.comparison += 6;
            pivot_index = findPivot(A, low, high);

            counter.swap++;
            Helper.swap(A, low, pivot_index);
         }

         pivot_index = low;
         int pivot_value = A[pivot_index];

         int left_pointer = low + 1;
         int right_pointer = high;

         while (true) {

            while (left_pointer <= high && A[left_pointer] <= pivot_value) {
               counter.comparison++;
               left_pointer++;
            }

            while (right_pointer > low && A[right_pointer] >= pivot_value) {
               counter.comparison++;
               right_pointer--;
            }

            counter.comparison++;
            if (left_pointer >= right_pointer)
               break;

            counter.swap++;
            Helper.swap(A, left_pointer, right_pointer);
         }
         counter.swap++;
         Helper.swap(A, low, right_pointer);

         pivot_index = right_pointer;

         quickSortByPartition(A, low, pivot_index, counter, protection);
         quickSortByPartition(A, pivot_index + 1, high, counter, protection);
      }
   }

   /**
    * Finds the median of three {first, mid, last}
    * 
    * @param A
    * @param low
    * @param high
    * @return
    */
   private static int findPivot(int[] A, int low, int high) {

      // Pivot = median of 3
      int mid = (low + high) / 2;
      int pivot = low;
      if (A[low] >= A[mid] && A[low] <= A[high])
         pivot = low;
      if (A[mid] > A[low] && A[mid] <= A[high])
         pivot = mid;
      if (A[high] > A[low] && A[high] < A[mid])
         pivot = high;

      return pivot;
   }

}
