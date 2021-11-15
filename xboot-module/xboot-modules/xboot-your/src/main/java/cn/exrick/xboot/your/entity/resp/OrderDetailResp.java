package cn.exrick.xboot.your.entity.resp;

import cn.exrick.xboot.your.entity.Order;
import cn.exrick.xboot.your.entity.OrderDetail;
import cn.exrick.xboot.your.entity.OrderFile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderDetailResp implements Serializable {
    @ApiModelProperty(value = "统筹单基础信息")
    private Order order;
    @ApiModelProperty(value = "统筹单详情列表")
    private List<OrderDetail> orderDetails;
    @ApiModelProperty(value = "统筹单附件")
    private List<OrderFile> orderFiles;

}
