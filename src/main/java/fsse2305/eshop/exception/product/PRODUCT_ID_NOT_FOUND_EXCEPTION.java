package fsse2305.eshop.exception.product;

public class PRODUCT_ID_NOT_FOUND_EXCEPTION extends Exception{
      final String pid;
      public PRODUCT_ID_NOT_FOUND_EXCEPTION(String pid) {
            this.pid = pid;
      }
      @Override
      public String toString() {
            return "Product ID " + pid + " not found";
      }
}
