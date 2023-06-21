package fsse2305.eshop.data;

import fsse2305.eshop.data.entity.TransProductEntity;

import java.math.BigDecimal;

public class TransProductResponseData {
    private Integer tpid;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransProductResponseData(TransProductEntity transProductEntity) {
        this.tpid = transProductEntity.getTpid();
        this.quantity = transProductEntity.getQuantity();
        this.subtotal = transProductEntity.getSubtotal();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
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
