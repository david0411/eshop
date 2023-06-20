package fsse2305.eshop.data.entity;

import fsse2305.eshop.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @ManyToOne
    @JoinColumn(name = "pid")
    private ProductEntity productEntity;
    @ManyToOne
    @JoinColumn(name = "uid")
    private UserEntity userEntity;
    @Column(nullable = false)
    private Integer quantity;

    public CartItemEntity() {

    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUid() {
        return userEntity.getUid();
    }

    public Integer getPid() {
        return productEntity.getPid();
    }
}
