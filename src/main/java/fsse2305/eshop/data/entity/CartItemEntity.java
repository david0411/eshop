package fsse2305.eshop.data.entity;

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
}
