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
@Table(name = "b_helper_damage_classify")
@TableName("b_helper_damage_classify")
@ApiModel(value = "互助单损失主表分类(记录相应的险种以及损失额)")
public class HelperDamageClassify extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "互助单id")
    private String helperOrderId;

    @ApiModelProperty(value = "费率类型：车损 三者等  数据字典维护")
    private String feeType;
    @ApiModelProperty(value = "总保额  单位(限额)")
    private Integer insuranceMoney;

    @ApiModelProperty(value = "不计免责")
    private BigDecimal deductibleFee;

    @ApiModelProperty(value = "该类别总损失金额")
    private BigDecimal damangeTotalMoney;


    @ApiModelProperty(value = "是否已决")
    private Boolean isDecide=false;
}