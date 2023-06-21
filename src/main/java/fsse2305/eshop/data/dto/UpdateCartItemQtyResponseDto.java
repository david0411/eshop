package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.data.UpdateCartItemQtyResponseData;

import java.math.BigDecimal;

public class UpdateCartItemQtyResponseDto {
    private int pid;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    @JsonProperty("cart_quantity")
    private Integer cartQty;
    @JsonProperty("stock")
    private Integer stockQty;

    public UpdateCartItemQtyResponseDto(UpdateCartItemQtyResponseData updateCartItemQtyResponseData) {
        this.pid = updateCartItemQtyResponseData.getPid();
        this.name = updateCartItemQtyResponseData.getName();
        this.imageUrl = updateCartItemQtyResponseData.getImageUrl();
        this.price = updateCartItemQtyResponseData.getPrice();
        this.cartQty = updateCartItemQtyResponseData.getCartQty();
        this.stockQty = updateCartItemQtyResponseData.getStockQty();
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

    public Integer getCartQty() {
        return cartQty;
    }

    public void setCartQty(Integer cartQty) {
        this.cartQty = cartQty;
    }

    public Integer getStockQty() {
        return stockQty;
    }

    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }
}
