package cn.exrick.xboot.your.entity.resp;

import cn.exrick.xboot.core.entity.Department;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class DepartMentChannelDiscountResp extends Department {
    @ApiModelProperty(value = "渠道折扣")
    private BigDecimal discountRate;

    @ApiModelProperty(value = "渠道配置id")
    private String channelDiscountId;



    @ApiModelProperty(value = "费率组(必填)")
    private String rateRegionGroup="";

    @ApiModelProperty(value = "折扣组(必填)")
    private String discountRegionGroup="";
}
