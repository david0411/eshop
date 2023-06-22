package fsse2305.eshop.exception.transaction;

public class TRANSACTION_FINISH_FAILED_EXCEPTION extends Exception{
    private final Integer tid;
    public TRANSACTION_FINISH_FAILED_EXCEPTION(Integer tid) {
        this.tid = tid;
    }
    @Override
    public String toString()    {
        return "Fail to finish transaction " + tid;
    }
}
