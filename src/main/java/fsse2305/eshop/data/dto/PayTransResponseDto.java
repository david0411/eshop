package fsse2305.eshop.data.dto;

import fsse2305.eshop.data.data.PayTransResponseData;

public class PayTransResponseDto {
    private String result;

    public PayTransResponseDto(PayTransResponseData payTransResponseData) {
        this.result = payTransResponseData.getResult();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
