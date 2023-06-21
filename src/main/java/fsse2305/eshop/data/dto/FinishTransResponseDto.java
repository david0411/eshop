package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.data.FinishTransResponseData;
import fsse2305.eshop.data.transactionEnum.TransStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class FinishTransResponseDto {
    private Integer tid;
    @JsonProperty("buyer_uid")
    private Integer buyerUid;
    private Timestamp datetime;
    private Enum<TransStatus> status;
    private BigDecimal total;
    @JsonProperty("items")
    private List<TransProductResponseDto> transProductResponseDtoList;

    public FinishTransResponseDto(FinishTransResponseData finishTransResponseData, List<TransProductResponseDto> transProductResponseDtoList)   {
        this.tid = finishTransResponseData.getTid();
        this.buyerUid = finishTransResponseData.getBuyerUid();
        this.datetime = finishTransResponseData.getDatetime();
        this.status = finishTransResponseData.getStatus();
        this.total = finishTransResponseData.getTotal();
        this.transProductResponseDtoList = transProductResponseDtoList;
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

    public List<TransProductResponseDto> getTransProductResponseDtoList() {
        return transProductResponseDtoList;
    }

    public void setTransProductResponseDtoList(List<TransProductResponseDto> transProductResponseDtoList) {
        this.transProductResponseDtoList = transProductResponseDtoList;
    }
}
