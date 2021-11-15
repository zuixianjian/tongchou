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
@Table(name = "b_channel_discount")
@TableName("b_channel_discount")
@ApiModel(value = "渠道折扣配置")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_channel_discount set del_flag = 1 where id = ?")
public class ChannelDiscount extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "渠道id")
    private String departmentId;
    @ApiModelProperty(value = "渠道折扣")
    private BigDecimal discountRate;


    @ApiModelProperty(value = "费率组(必填)")
    private String rateRegionGroup;

    @ApiModelProperty(value = "折扣组(必填)")
    private String discountRegionGroup;
}