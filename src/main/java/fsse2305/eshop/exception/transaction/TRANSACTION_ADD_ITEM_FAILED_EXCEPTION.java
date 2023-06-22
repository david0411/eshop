package fsse2305.eshop.exception.transaction;

public class TRANSACTION_ADD_ITEM_FAILED_EXCEPTION extends Exception{
    private final String name;
    public TRANSACTION_ADD_ITEM_FAILED_EXCEPTION(String name) {
        this.name = name;
    }

    @Override
    public String toString()    {
        return "Fail to add " + name + " to transaction";
    }
}
