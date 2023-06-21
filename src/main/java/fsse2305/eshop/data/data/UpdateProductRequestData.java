package fsse2305.eshop.data.data;

import fsse2305.eshop.data.dto.UpdateProductRequestDto;

import java.math.BigDecimal;

public class UpdateProductRequestData {
      private Integer pid;
      private String name;
      private String description;
      private String imageUrl;
      private BigDecimal price;
      private Integer stockQty;

      public UpdateProductRequestData(UpdateProductRequestDto updateProductRequestDto)   {
            this.pid = updateProductRequestDto.getPid();
            this.name = updateProductRequestDto.getName();
            this.description = updateProductRequestDto.getDescription();
            this.imageUrl = updateProductRequestDto.getImageUrl();
            this.price = updateProductRequestDto.getPrice();
            this.stockQty = updateProductRequestDto.getStockQty();
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
}
