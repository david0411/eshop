package fsse2305.eshop.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coupon_usage")
public class CouponUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponUsageId;
    @OneToOne
    @JoinColumn(name = "tid")
    TransactionEntity transactionEntity;

    public CouponUsage() {
    }

    public Integer getCouponUsageId() {
        return couponUsageId;
    }

    public void setCouponUsageId(Integer couponUsageId) {
        this.couponUsageId = couponUsageId;
    }

    public TransactionEntity getTransactionEntity() {
        return transactionEntity;
    }

    public void setTransactionEntity(TransactionEntity transactionEntity) {
        this.transactionEntity = transactionEntity;
    }
}
