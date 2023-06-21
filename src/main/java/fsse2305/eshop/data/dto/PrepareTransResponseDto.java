package fsse2305.eshop.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsse2305.eshop.data.PrepareTransResponseData;
import fsse2305.eshop.data.transStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class PrepareTransResponseDto {
    private Integer tid;
    @JsonProperty("buyer_uid")
    private Integer buyerUid;
    private Timestamp datetime;
    private Enum<transStatus> status;
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

    public List<TransProductResponseDto> getTransProductResponseDtoList() {
        return transProductResponseDtoList;
    }

    public void setTransProductResponseDtoList(List<TransProductResponseDto> transProductResponseDtoList) {
        this.transProductResponseDtoList = transProductResponseDtoList;
    }
}
