package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.data.ProductByCategoryResponseData;

import java.math.BigDecimal;

public class ProductByCategoryResponseDto {

    private int pid;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    @JsonProperty("has_stock")
    private boolean hasStock;

    public ProductByCategoryResponseDto(ProductByCategoryResponseData productByCategoryResponseData) {
        this.pid = productByCategoryResponseData.getPid();
        this.name = productByCategoryResponseData.getName();
        this.imageUrl = productByCategoryResponseData.getImageUrl();
        this.price = productByCategoryResponseData.price;
        this.hasStock = productByCategoryResponseData.isHasStock();
    }
}
