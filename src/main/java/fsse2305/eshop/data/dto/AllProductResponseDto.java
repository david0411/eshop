package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.data.AllProductResponseData;

import java.math.BigDecimal;

public class AllProductResponseDto {

      private int pid;
      private String name;
      @JsonProperty("image_url")
      private String imageUrl;
      private BigDecimal price;
      @JsonProperty("has_stock")
      private boolean hasStock;

      public AllProductResponseDto(AllProductResponseData allProductResponseData) {
            this.pid = allProductResponseData.getPid();
            this.name = allProductResponseData.getName();
            this.imageUrl = allProductResponseData.getImageUrl();
            this.price = allProductResponseData.price;
            this.hasStock = allProductResponseData.isHasStock();
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

      public boolean isHasStock() {
            return hasStock;
      }

      public void setHasStock(boolean hasStock) {
            this.hasStock = hasStock;
      }
}
