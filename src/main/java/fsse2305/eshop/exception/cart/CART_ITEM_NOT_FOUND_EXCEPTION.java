package fsse2305.eshop.exception.cart;

public class CART_ITEM_NOT_FOUND_EXCEPTION extends Exception{
    final Integer pid;
    public CART_ITEM_NOT_FOUND_EXCEPTION(Integer pid) {
        this.pid = pid;
    }
    @Override
    public String toString() {
        return "Product ID: " + pid + " not in the cart";
    }
}
