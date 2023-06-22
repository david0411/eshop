package fsse2305.eshop.exception.product;

public class STOCK_QTY_DEDUCT_FAILED_EXCEPTION extends Exception{
    private Integer pid;
    public STOCK_QTY_DEDUCT_FAILED_EXCEPTION(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString()    {
        return "Fail to deduct Qty from Product ID: " + pid;
    }
}
