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
@Table(name = "b_rate_config")
@TableName("b_rate_config")
@ApiModel(value = "费率设置")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_rate_config set del_flag = 1 where id = ?")
public class RateConfig extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "车辆种类A(必填)")
    private String carTypeA;
    @ApiModelProperty(value = "车辆种类B(必填)")
    private String carTypeB;
    @ApiModelProperty(value = "所属性质(非必填)")
    private String belongType;
    @ApiModelProperty(value = "使用性质(必填)")
    private String useType;
    @ApiModelProperty(value = "车系分类(非必填)")
    private String carSieralType;
    //车损必填  其他不要填
    @ApiModelProperty(value = "年限(车损必填)")
    private String yearType;
    //第三者责任险必填 其他不填
    @ApiModelProperty(value = "保额  单位元")
    private Integer insuranceMoney;

    @ApiModelProperty(value = "费率组(必填)")
    private String regionGroup;

    @ApiModelProperty(value = "费率类型：车损 三者等  数据字典维护(必填)")
    private String feeType;
    //车损必填
    @ApiModelProperty(value = "基础保费(车损必填)")
    private BigDecimal baseFee;


    @ApiModelProperty(value = "费率(车损必填)")
    private BigDecimal feeRate;


}