package fsse2305.eshop.data;

import fsse2305.eshop.data.entity.TransactionEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PrepareTransResponseData {
    private Integer tid;
    private Integer buyerUid;
    private Timestamp datetime;
    private Enum<transStatus> status;
    private BigDecimal total;

    public PrepareTransResponseData(TransactionEntity transactionEntity) {
        this.tid = transactionEntity.getTid();
        this.buyerUid = transactionEntity.getUserEntity().getUid();
        this.datetime = transactionEntity.getDatetime();
        this.status = transactionEntity.getStatus();
        this.total = transactionEntity.getTotal();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
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
