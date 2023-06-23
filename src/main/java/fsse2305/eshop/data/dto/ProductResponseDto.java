package fsse2305.eshop.data.dto;

import fsse2305.eshop.data.data.ProductResponseData;

public class ProductResponseDto {
    private final ProductResponseData productResponseData;

    public ProductResponseDto(ProductResponseData productResponseData) {
        this.productResponseData = productResponseData;
    }

    public ProductResponseData getProductResponseData() {
        return productResponseData;
    }
}
