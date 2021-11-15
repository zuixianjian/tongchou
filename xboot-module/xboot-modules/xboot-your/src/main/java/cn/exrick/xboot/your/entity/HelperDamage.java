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

/**
 * @author Jingqi Chen
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "b_helper_damage")
@TableName("b_helper_damage")
@ApiModel(value = "互助单损失主表")
public class HelperDamage extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "互助单id")
    private String helperOrderId;

    @ApiModelProperty(value = "互助单损失主表分类id")
    private String helperDamageClassifyId;

    @ApiModelProperty(value = "车牌号")
    private String carNo;
    @ApiModelProperty(value = "证件号")
    private String idNo;
}