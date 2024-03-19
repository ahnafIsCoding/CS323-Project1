
/**
 * Helper class
 * @author Ahnaf Ahmed
 *
 */
public class Helper {

   /**
    * Swaps given references in the input Array
    * 
    * @param A
    * @param i1
    * @param i2
    */
   public static void swap(int[] A, int i1, int i2) {
      int temp = A[i1];
      A[i1] = A[i2];
      A[i2] = temp;
   }
}
