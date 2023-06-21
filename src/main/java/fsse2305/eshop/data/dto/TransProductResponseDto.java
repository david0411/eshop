package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.data.TransProductResponseData;

import java.math.BigDecimal;

public class TransProductResponseDto {
    private Integer tpid;
    @JsonProperty("product")
    private ProductResponseDto productResponseDto;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransProductResponseDto(TransProductResponseData transProductResponseData, ProductResponseDto productResponseDto) {
        this.tpid = transProductResponseData.getTpid();
        this.productResponseDto = productResponseDto;
        this.quantity = transProductResponseData.getQuantity();
        this.subtotal = transProductResponseData.getSubtotal();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProductResponseDto() {
        return productResponseDto;
    }

    public void setProductResponseDto(ProductResponseDto productResponseDto) {
        this.productResponseDto = productResponseDto;
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
