package fsse2305.eshop.data.dto;

import fsse2305.eshop.data.PutCartItemResponseData;

public class PutCartItemResponseDto {
    private String result;

    public PutCartItemResponseDto(PutCartItemResponseData putCartItemResponseData) {
        this.result = putCartItemResponseData.getResult();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
