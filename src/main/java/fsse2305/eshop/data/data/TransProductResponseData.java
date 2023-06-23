package fsse2305.eshop.data.data;

import fsse2305.eshop.data.entity.TransProductEntity;

import java.math.BigDecimal;

public class TransProductResponseData {
    private String tpid;
    private Integer quantity;
    private BigDecimal subtotal;
    private ProductResponseData productResponseData;

    public TransProductResponseData(TransProductEntity transProductEntity, ProductResponseData productResponseData) {
        this.tpid = transProductEntity.getTpid();
        this.quantity = transProductEntity.getQuantity();
        this.subtotal = transProductEntity.getSubtotal();
        this.productResponseData = productResponseData;
    }

    public ProductResponseData getProductResponseData() {
        return productResponseData;
    }

    public void setProductResponseData(ProductResponseData productResponseData) {
        this.productResponseData = productResponseData;
    }

    public String getTpid() {
        return tpid;
    }

    public void setTpid(String tpid) {
        this.tpid = tpid;
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
}
