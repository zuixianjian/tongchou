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
@Table(name = "b_youhui")
@TableName("b_youhui")
@ApiModel(value = "优惠政策配置")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_youhui set del_flag = 1 where id = ?")
public class Youhui extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "优惠描述")
    private String feeFloatReason;
    @ApiModelProperty(value = "折扣率")
    private BigDecimal feeFloatRate;
    @ApiModelProperty(value = "折扣组")
    private String regionGroup;

}