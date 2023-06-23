package fsse2305.eshop.data.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart_item")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cid;
    private String pid;
    private String uid;
    private Integer quantity;

    public CartItemEntity() {
    }

    public CartItemEntity(String pid, String uid, Integer quantity) {
        this.pid = pid;
        this.uid = uid;
        this.quantity = quantity;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemEntity{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", uid=" + uid +
                ", quantity=" + quantity +
                '}';
    }
}
