package fsse2305.eshop.exception.transaction;

public class TRANSACTION_NOT_FOUND_EXCEPTION extends Exception{
    final Integer tid;

    public TRANSACTION_NOT_FOUND_EXCEPTION(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + tid + " not found";
    }
}
