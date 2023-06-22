package fsse2305.eshop.exception.transaction;

public class TRANSACTION_STATUS_ERROR_EXCEPTION extends Exception{
    String currStatus;
    String requiredStatus;
    public TRANSACTION_STATUS_ERROR_EXCEPTION(String currStatus, String requiredStatus) {
        this.currStatus = currStatus;
        this.requiredStatus = requiredStatus;
    }

    @Override
    public String toString()    {
        return "Current status:" + currStatus + " Require status:" + requiredStatus;
    }
}
