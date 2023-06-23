package fsse2305.eshop.data.data;

import fsse2305.eshop.data.entity.TransactionEntity;
import fsse2305.eshop.data.transactionEnum.TransStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GetTransResponseData {
    private String tid;
    private String buyerUid;
    private LocalDateTime datetime;
    private Enum<TransStatus> status;
    private BigDecimal total;
    private List<TransProductResponseData> transProductResponseDataList;

    public GetTransResponseData(TransactionEntity transactionEntity, List<TransProductResponseData> transProductResponseDataList) {
        this.tid = transactionEntity.getTid();
        this.buyerUid = transactionEntity.getBuyerUid();
        this.datetime = transactionEntity.getDatetime();
        this.status = TransStatus.valueOf(transactionEntity.getStatus());
        this.total = transactionEntity.getTotal();
        this.transProductResponseDataList = transProductResponseDataList;
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
