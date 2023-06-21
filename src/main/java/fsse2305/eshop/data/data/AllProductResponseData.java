package fsse2305.eshop.data.data;

import fsse2305.eshop.data.entity.ProductEntity;

import java.math.BigDecimal;

public class AllProductResponseData {
      public int pid;
      public String name;
      public String imageUrl;
      public BigDecimal price;
      public boolean hasStock;

      public AllProductResponseData(ProductEntity productEntity)      {
            this.pid = productEntity.getPid();
            this.name = productEntity.getName();
            this.imageUrl = productEntity.getImageUrl();
            this.price = productEntity.getPrice();
            setHasStock(productEntity.getStockQty());
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

      public void setHasStock(Integer stockQty) {
            this.hasStock = stockQty > 0;
      }
}
