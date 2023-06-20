package fsse2305.eshop.data.dto;

import fsse2305.eshop.data.DeleteCartItemResponseData;

public class DeleteCartItemResponseDto {
    private String result;

    public DeleteCartItemResponseDto(DeleteCartItemResponseData deleteCartItemResponseData) {
        this.result = deleteCartItemResponseData.getResult();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
