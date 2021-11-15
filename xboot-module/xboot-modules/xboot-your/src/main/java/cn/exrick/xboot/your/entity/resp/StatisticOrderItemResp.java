package cn.exrick.xboot.your.entity.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StatisticOrderItemResp implements Serializable {
    @ApiModelProperty(value = "统筹单号")
    private String orderId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "收款时间")
    private LocalDateTime recieveTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统筹开始时间")
    private LocalDateTime startDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统筹结束时间")
    private LocalDateTime endDate;
    @ApiModelProperty(value = "订单类型，批单还是订单")
    private String orderType;
    @ApiModelProperty(value = "创建人部门id")
    private String departmentId;
    @ApiModelProperty(value = "创建人部门名称")
    private String departmentName;
    @ApiModelProperty(value = "被统筹人姓名")
    private String clientName;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "具体车牌号码")
    private String carNo;
    @ApiModelProperty(value = "行驶证车辆类型")
    private String runCardCarType;
    @ApiModelProperty(value = "收款方式")
    private String recieveType;
    @ApiModelProperty(value = "应收全款")
    private BigDecimal shouldRecieve;
    @ApiModelProperty(value = "渠道折扣")
    private BigDecimal discountRate;
    @ApiModelProperty(value = "应收净费")
    private BigDecimal shouldCleanRecieve;
    @ApiModelProperty(value = "实收款项")
    private BigDecimal realRecieve;
    @ApiModelProperty(value = "收款人")
    private String recieveMan;

}
