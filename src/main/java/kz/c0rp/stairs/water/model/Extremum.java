package kz.c0rp.stairs.water.model;

import java.util.List;

/**
 * @author Sanzhar Aubakirov (c0rp.aubakirov@gmail.com)
 */
public class Extremum {

   private List<Integer> localMaximums;
   private Boolean hasLocalMinimum;

   public Extremum(final List<Integer> localMaximums,
                   final Boolean hasLocalMinimum) {

      this.localMaximums = localMaximums;
      this.hasLocalMinimum = hasLocalMinimum;
   }

   public List<Integer> getLocalMaximums() {

      return localMaximums;
   }

   public void setLocalMaximums(final List<Integer> localMaximums) {

      this.localMaximums = localMaximums;
   }

   public Boolean getHasLocalMinimum() {

      return hasLocalMinimum;
   }

   public void setHasLocalMinimum(final Boolean hasLocalMinimum) {

      this.hasLocalMinimum = hasLocalMinimum;
   }
}
