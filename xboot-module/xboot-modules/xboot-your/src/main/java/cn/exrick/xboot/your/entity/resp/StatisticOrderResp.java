package cn.exrick.xboot.your.entity.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StatisticOrderResp implements Serializable {
    @ApiModelProperty(value = "应收全款汇总")
    private BigDecimal shouldRecieveSum;
    @ApiModelProperty(value = "应收净费汇总")
    private BigDecimal shouldCleanRecieveSum;
    @ApiModelProperty(value = "实收款项汇总")
    private BigDecimal realRecieveSum;
    @ApiModelProperty(value = "统计汇总")
    private List<StatisticOrderItemResp> statisticOrderItemResps;
}
