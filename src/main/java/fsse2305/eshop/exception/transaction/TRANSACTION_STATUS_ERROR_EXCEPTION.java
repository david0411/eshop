package fsse2305.eshop.exception.transaction;

import fsse2305.eshop.data.transactionEnum.TransStatus;

public class TRANSACTION_STATUS_ERROR_EXCEPTION extends Exception{
    private final Enum<TransStatus> currStatus;
    private final TransStatus requiredStatus;
    public TRANSACTION_STATUS_ERROR_EXCEPTION(Enum<TransStatus> currStatus, TransStatus requiredStatus) {
        this.currStatus = currStatus;
        this.requiredStatus = requiredStatus;
    }

    @Override
    public String toString()    {
        return "Current status:" + currStatus + " Require status:" + requiredStatus;
    }
}
