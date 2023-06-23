package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.data.PrepareTransResponseData;
import fsse2305.eshop.data.transactionEnum.TransStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PrepareTransResponseDto {
    private String tid;
    @JsonProperty("buyer_uid")
    private String buyerUid;
    private LocalDateTime datetime;
    private Enum<TransStatus> status;
    private BigDecimal total;
    @JsonProperty("items")
    private List<TransProductResponseDto> transProductResponseDtoList;

    public PrepareTransResponseDto(PrepareTransResponseData prepareTransResponseData, List<TransProductResponseDto> transProductResponseDtoList)   {
        this.tid = prepareTransResponseData.getTid();
        this.buyerUid = prepareTransResponseData.getBuyerUid();
        this.datetime = prepareTransResponseData.getDatetime();
        this.status = prepareTransResponseData.getStatus();
        this.total = prepareTransResponseData.getTotal();
        this.transProductResponseDtoList = transProductResponseDtoList;
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

    public List<TransProductResponseDto> getTransProductResponseDtoList() {
        return transProductResponseDtoList;
    }

    public void setTransProductResponseDtoList(List<TransProductResponseDto> transProductResponseDtoList) {
        this.transProductResponseDtoList = transProductResponseDtoList;
    }
}
