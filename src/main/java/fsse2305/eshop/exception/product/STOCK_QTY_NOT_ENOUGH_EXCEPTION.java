package fsse2305.eshop.exception.product;

public class STOCK_QTY_NOT_ENOUGH_EXCEPTION extends Exception{
    private final String pid;
    private final Integer stockQty;
    private final Integer quantity;

    public STOCK_QTY_NOT_ENOUGH_EXCEPTION(String pid, Integer stockQty, Integer quantity) {
        this.pid = pid;
        this.stockQty = stockQty;
        this.quantity = quantity;
    }

    @Override
    public String toString()    {
        return "Product ID: " + pid + " not enough\n" +
                "Stock Qty: " + stockQty + "\n" +
                "Request Qty: " + quantity;
    }
}
