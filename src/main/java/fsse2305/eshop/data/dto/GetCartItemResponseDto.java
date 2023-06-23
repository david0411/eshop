package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.data.GetCartItemResponseData;

import java.math.BigDecimal;

public class GetCartItemResponseDto {
    private String pid;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    @JsonProperty("cart_quantity")
    private Integer cartQty;
    @JsonProperty("stock")
    private Integer stockQty;

    public GetCartItemResponseDto(GetCartItemResponseData getCartItemResponseData)  {
        this.pid = getCartItemResponseData.getPid();
        this.name = getCartItemResponseData.getName();
        this.imageUrl = getCartItemResponseData.getImageUrl();
        this.price = getCartItemResponseData.getPrice();
        this.cartQty = getCartItemResponseData.getCartQty();
        this.stockQty = getCartItemResponseData.getStockQty();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
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
