package cn.exrick.xboot.your.entity.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StatisticBussinessReq implements Serializable {
    @ApiModelProperty(value = "统计模式")
    private String statisticType;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "收款开始时间")
    private LocalDateTime startDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "收款截至时间")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "部门id集合")
    private List<String> departmentIds;
    @ApiModelProperty(value = "收款方式")
    private String recieveType;

    @ApiModelProperty(value = "收款单类型")
    private String bussinessType;
}
