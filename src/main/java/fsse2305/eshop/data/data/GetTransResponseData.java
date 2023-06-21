package fsse2305.eshop.data.data;

import fsse2305.eshop.data.entity.TransactionEntity;
import fsse2305.eshop.data.transactionEnum.TransStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class GetTransResponseData {
    private Integer tid;
    private Integer buyerUid;
    private Timestamp datetime;
    private Enum<TransStatus> status;
    private BigDecimal total;
    private List<TransProductResponseData> transProductResponseDataList;

    public GetTransResponseData(TransactionEntity transactionEntity, List<TransProductResponseData> transProductResponseDataList) {
        this.tid = transactionEntity.getTid();
        this.buyerUid = transactionEntity.getUserEntity().getUid();
        this.datetime = transactionEntity.getDatetime();
        this.status = transactionEntity.getStatus();
        this.total = transactionEntity.getTotal();
        this.transProductResponseDataList = transProductResponseDataList;
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

    public Enum<TransStatus> getStatus() {
        return status;
    }

    public void setStatus(Enum<TransStatus> status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransProductResponseData> getTransProductResponseDataList() {
        return transProductResponseDataList;
    }

    public void setTransProductResponseDataList(List<TransProductResponseData> transProductResponseDataList) {
        this.transProductResponseDataList = transProductResponseDataList;
    }
}
