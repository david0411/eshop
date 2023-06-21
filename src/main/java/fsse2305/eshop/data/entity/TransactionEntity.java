package fsse2305.eshop.data.entity;

import fsse2305.eshop.data.transStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    @ManyToOne
    @JoinColumn(name = "buyer_uid", nullable = false)
    private UserEntity userEntity;
    @Column(nullable = false)
    private Timestamp datetime;
    @Column(nullable = false)
    private Enum<transStatus> status;
    @Column(nullable = false)
    private BigDecimal total;

    public TransactionEntity() {
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Enum<transStatus> getStatus() {
        return status;
    }

    public void setStatus(Enum<transStatus> status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
