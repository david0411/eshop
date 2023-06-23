package fsse2305.eshop.data.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tid;
    @Field(name = "buyer_uid")
    private String buyerUid;
    private LocalDateTime datetime;
    private String status;
    private BigDecimal total;

    public TransactionEntity() {
    }

    public TransactionEntity(String buyerUid, LocalDateTime datetime, String status, BigDecimal total) {
        this.buyerUid = buyerUid;
        this.datetime = datetime;
        this.status = status;
        this.total = total;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(String buyerUid) {
        this.buyerUid = buyerUid;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "tid=" + tid +
                ", buyer_uid=" + buyerUid +
                ", datetime=" + datetime +
                ", status=" + status +
                ", total=" + total +
                '}';
    }
}
