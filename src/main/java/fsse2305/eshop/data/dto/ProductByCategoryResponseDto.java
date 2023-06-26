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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }
}
