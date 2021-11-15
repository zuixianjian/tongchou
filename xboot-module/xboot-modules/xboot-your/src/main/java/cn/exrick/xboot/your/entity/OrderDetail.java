package cn.exrick.xboot.your.entity;

import cn.exrick.xboot.core.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Jingqi Chen
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "b_order_detail")
@TableName("b_order_detail")
@ApiModel(value = "统筹单明细列表")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_order_detail set del_flag = 1 where id = ?")
public class OrderDetail extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "统筹单id")
    private String orderId;

    @ApiModelProperty(value = "费率类型：车损 三者等  数据字典维护")
    private String feeType;

    @ApiModelProperty(value = "总保额  单位(限额)")
    private Integer insuranceMoney;

    @ApiModelProperty(value = "基础保费")
    private BigDecimal baseFee;

    @ApiModelProperty(value = "费率")
    private BigDecimal feeRate;


    @ApiModelProperty(value = "应收保费")
    private BigDecimal actualFee;


    @ApiModelProperty(value = "不计免赔")
    private BigDecimal deductibleFee;

    @ApiModelProperty(value = "每人统筹额")
    private BigDecimal everyFee;

    @ApiModelProperty(value = "座位数")
    private Integer siteCount;


}