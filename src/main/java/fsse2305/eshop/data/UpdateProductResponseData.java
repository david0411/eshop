package fsse2305.eshop.data;

import fsse2305.eshop.data.entity.ProductEntity;

import java.math.BigDecimal;

public class UpdateProductResponseData {
      private Integer pid;
      private String name;
      private String description;
      private String imageUrl;
      private BigDecimal price;
      private Integer stockQty;
      private boolean hasStock;

public  UpdateProductResponseData(ProductEntity productEntity){
      this.pid = productEntity.getPid();
      this.name = productEntity.getName();
      this.description = productEntity.getDescription();
      this.imageUrl = productEntity.getImageUrl();
      this.price = productEntity.getPrice();
      this.stockQty = productEntity.getStockQty();
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
