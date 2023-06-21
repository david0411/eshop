package fsse2305.eshop.data.data;

import fsse2305.eshop.data.entity.TransProductEntity;

import java.math.BigDecimal;

public class ProductResponseData {
    private int pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stockQty;

    public ProductResponseData(TransProductEntity transProductEntity) {
        this.pid = transProductEntity.getPid();
        this.name = transProductEntity.getName();
        this.description = transProductEntity.getDescription();
        this.imageUrl = transProductEntity.getImageUrl();
        this.price = transProductEntity.getPrice();
        this.stockQty = transProductEntity.getStockQty();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getStockQty() {
        return stockQty;
    }

    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }
}
