package fsse2305.eshop.data.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "trans_product")
public class TransProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tpid;
    private String tid;
    private String pid;
    private String name;
    private String description;
    @Field(name = "image_url")
    private String imageUrl;
    private BigDecimal price;
    @Field(name = "stock_qty")
    private Integer stockQty;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransProductEntity() {
    }

    public TransProductEntity(String tid, String pid, String name, String description, String imageUrl, BigDecimal price, Integer stockQty, Integer quantity, BigDecimal subtotal) {
        this.tid = tid;
        this.pid = pid;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stockQty = stockQty;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getTpid() {
        return tpid;
    }

    public void setTpid(String tpid) {
        this.tpid = tpid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "TransProductEntity{" +
                "tpid=" + tpid +
                ", tid=" + tid +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", stockQty=" + stockQty +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
