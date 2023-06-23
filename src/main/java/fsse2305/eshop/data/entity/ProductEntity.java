package fsse2305.eshop.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "product")
public class ProductEntity {
    @Id
    private String pid;
    private String name;
    private String description;
    @Field(name = "image_url")
    private String imageUrl;
    private BigDecimal price;
    @Field(name = "stock_qty")
    private Integer stockQty;

    public ProductEntity() {
    }

    public ProductEntity(String pid, String name, String description, String imageUrl, BigDecimal price, Integer stockQty) {
        this.pid = pid;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stockQty = stockQty;
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

    @Override
    public String toString() {
        return "ProductEntity{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", stockQty=" + stockQty +
                '}';
    }
}
