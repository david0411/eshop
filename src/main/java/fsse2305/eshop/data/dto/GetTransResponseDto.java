package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.data.GetTransResponseData;
import fsse2305.eshop.data.transactionEnum.TransStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class GetTransResponseDto {
    private Integer tid;
    @JsonProperty("buyer_uid")
    private Integer buyerUid;
    private Timestamp datetime;
    private Enum<TransStatus> status;
    private BigDecimal total;
    @JsonProperty("items")
    private List<TransProductResponseDto> transProductResponseDtoList;


    public GetTransResponseDto(GetTransResponseData getTransResponseData, List<TransProductResponseDto> transProductResponseDtoList)   {
        this.tid = getTransResponseData.getTid();
        this.buyerUid = getTransResponseData.getBuyerUid();
        this.datetime = getTransResponseData.getDatetime();
        this.status = getTransResponseData.getStatus();
        this.total = getTransResponseData.getTotal();
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
