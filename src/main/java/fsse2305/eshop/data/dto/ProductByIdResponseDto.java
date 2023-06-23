package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.data.ProductByIdResponseData;

import java.math.BigDecimal;

public class ProductByIdResponseDto {
      private String pid;
      private String name;
      private String description;
      @JsonProperty("image_url")
      private String imageUrl;
      private BigDecimal price;
      @JsonProperty("stock")
      private Integer stockQty;

      public ProductByIdResponseDto(ProductByIdResponseData productByIdResponseData)      {
            this.pid = productByIdResponseData.getPid();
            this.name = productByIdResponseData.getName();
            this.description = productByIdResponseData.getDescription();
            this.imageUrl = productByIdResponseData.getImageUrl();
            this.price = productByIdResponseData.getPrice();
            this.stockQty = productByIdResponseData.getStockQty();
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
}
