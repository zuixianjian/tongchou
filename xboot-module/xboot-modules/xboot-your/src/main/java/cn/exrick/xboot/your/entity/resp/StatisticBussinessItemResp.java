package cn.exrick.xboot.your.entity.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class StatisticBussinessItemResp implements Serializable {
    @ApiModelProperty(value = "参统车辆数")
    private Integer carCount;
    @ApiModelProperty(value = "创建人部门id")
    private String departmentId;
    @ApiModelProperty(value = "创建人部门名称")
    private String departmentName;

    @ApiModelProperty(value = "应收净费总和")
    private BigDecimal shouldCleanRecieveTotal;

    @ApiModelProperty(value = "应收全款总和")
    private BigDecimal shouldRecieveTotal;

    @ApiModelProperty(value = "实收款项总和")
    private BigDecimal realRecieveTotal;

    @ApiModelProperty(value = "事件登记数量")
    private Integer accidentLogCount;

    @ApiModelProperty(value = "事件结案数量")
    private Integer accidentFinishCount;

    @ApiModelProperty(value = "总损失金额")
    private BigDecimal damageTotalMoney;

    @ApiModelProperty(value = "统筹补偿金额")
    private BigDecimal orderCompensateMoney;

    @ApiModelProperty(value = "日期标识")
    private String dateFlag;
}
