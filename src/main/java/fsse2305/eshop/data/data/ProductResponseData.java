package fsse2305.eshop.data.data;

import fsse2305.eshop.data.entity.ProductEntity;

public class ProductResponseData {
    private final ProductEntity productEntity;

    public ProductResponseData(ProductEntity productEntity) {
        this.productEntity= productEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }
}
