package fsse2305.eshop.exception.cart;

public class CART_ITEM_EXCEED_STOCK_QTY_EXCEPTION extends Exception{
    final Integer pid;
    final Integer stockQty;
    final Integer requestedQty;
    public CART_ITEM_EXCEED_STOCK_QTY_EXCEPTION(Integer pid, Integer stockQty, Integer requestedQty) {
        this.pid = pid;
        this.stockQty = stockQty;
        this.requestedQty = requestedQty;
    }
    @Override
    public String toString() {
        return "Product ID: " + pid + " not enough\n" +
                "Stock quantity: " + stockQty + "\n" +
                "Requested quantity: " + requestedQty;
    }
}
