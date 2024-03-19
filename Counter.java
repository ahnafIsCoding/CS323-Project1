
/**
 * Counter class
 * @author Ahnaf Ahmed
 *
 */
public class Counter {

   public int comparison;
   public int swap;

   public Counter() {
      this.comparison = 0;
      this.swap = 0;
   }

   public void add(Counter result) {
      this.comparison += result.comparison;
      this.swap += result.swap;
   }

   public void reset() {
      this.comparison = 0;
      this.swap = 0;
   }
}
