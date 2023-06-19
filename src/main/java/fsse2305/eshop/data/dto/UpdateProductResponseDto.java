package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.UpdateProductResponseData;

import java.math.BigDecimal;

public class UpdateProductResponseDto {
      private Integer pid;
      private String name;
      private String description;
      @JsonProperty("image_url")
      private String imageUrl;
      private BigDecimal price;
      @JsonProperty("stock")
      private Integer stockQty;
      @JsonProperty("image_url")
      private boolean hasStock;

      public UpdateProductResponseDto(UpdateProductResponseData updateProductResponseData)      {
            this.pid= updateProductResponseData.getPid();
            this.name = updateProductResponseData.getName();
            this.description = updateProductResponseData.getDescription();
            this.imageUrl = updateProductResponseData.getImageUrl();
            this.price = updateProductResponseData.getPrice();
            this.stockQty = updateProductResponseData.getStockQty();
      }

      public Integer getPid() {
            return pid;
      }

      public void setPid(Integer pid) {
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

      public boolean isHasStock() {
            return hasStock;
      }

      public void setHasStock(boolean hasStock) {
            this.hasStock = hasStock;
      }
}
