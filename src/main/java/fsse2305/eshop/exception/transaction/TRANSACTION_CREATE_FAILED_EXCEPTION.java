package fsse2305.eshop.exception.transaction;

public class TRANSACTION_CREATE_FAILED_EXCEPTION extends Exception{
    @Override
    public String toString() {
        return "Fail to create transaction";
    }
}
