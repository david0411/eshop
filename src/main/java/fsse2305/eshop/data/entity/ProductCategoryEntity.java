package fsse2305.eshop.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="product_cat")
public class ProductCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pcid;
    @ManyToOne
    @JoinColumn(name = "pid")
    private ProductEntity productEntity;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private CategoryEntity categoryEntity;

    public ProductCategoryEntity() {
    }

    public Integer getPcid() {
        return pcid;
    }

    public void setPcid(Integer pcid) {
        this.pcid = pcid;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
