package fsse2305.eshop.data.data;

import fsse2305.eshop.data.entity.ProductEntity;

import java.math.BigDecimal;

public class UpdateCartItemQtyResponseData {
    private String pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer cartQty;
    private Integer stockQty;

    public UpdateCartItemQtyResponseData(ProductEntity productEntity, Integer quantity) {
        this.pid = productEntity.getPid();
        this.name = productEntity.getName();
        this.imageUrl = productEntity.getImageUrl();
        this.price = productEntity.getPrice();
        this.cartQty = quantity;
        this.stockQty = productEntity.getStockQty();
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
