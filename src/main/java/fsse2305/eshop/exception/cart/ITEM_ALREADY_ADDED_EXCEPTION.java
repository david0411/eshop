package fsse2305.eshop.exception.cart;

public class ITEM_ALREADY_ADDED_EXCEPTION extends Exception{
    final Integer pid;
    public ITEM_ALREADY_ADDED_EXCEPTION(Integer pid) {
        this.pid = pid;
    }
    @Override
    public String toString() {
        return "Product ID: " + pid + " already in the cart";
    }
}
