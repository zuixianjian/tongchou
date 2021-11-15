package cn.exrick.xboot.your.entity;

import cn.exrick.xboot.core.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
@Table(name = "b_helper_damage_detail")
@TableName("b_helper_damage_detail")
@ApiModel(value = "互助单损失明细")
public class HelperDamageDetail extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "损失主表id")
    private String helperDamageId;

    @ApiModelProperty(value = "损失项目名称")
    private String damageProjectName;

    @ApiModelProperty(value = "损失项目编码")
    private String damageProjectId;

    @ApiModelProperty(value = "供应商级别")
    private String supplierLevel;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "损失项目数量")
    private Integer count;

    @ApiModelProperty(value = "小计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "审核单价")
    private BigDecimal checkPrice;

    @ApiModelProperty(value = "审核损失项目数量")
    private Integer checkCount;

    @ApiModelProperty(value = "审核小计")
    private BigDecimal checkTotalPrice;
}