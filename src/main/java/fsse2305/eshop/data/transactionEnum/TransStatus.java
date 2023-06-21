package fsse2305.eshop.data.transactionEnum;

public enum TransStatus {
    PREPARE("10"),
    PAY("20"),
    PROCESSING("30"),
    SUCCESS("40");

    private String code;

    TransStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
