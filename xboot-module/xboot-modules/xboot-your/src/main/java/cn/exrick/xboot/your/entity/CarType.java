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
@Table(name = "b_car_type")
@TableName("b_car_type")
@ApiModel(value = "车型代码")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_car_type set del_flag = 1 where id = ?")
public class CarType extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "车型名称")
    private String carTypeName;
    @ApiModelProperty(value = "车型代码")
    private String code;

    @ApiModelProperty(value = "车型别名")
    private String carTypeNameAlaise;

    @ApiModelProperty(value = "生产厂家")
    private String productorCompany;

    @ApiModelProperty(value = "品牌")
    private String brandName;

    @ApiModelProperty(value = "整备质量")
    private BigDecimal weight;

    @ApiModelProperty(value = "车辆种类A")
    private String carTypeA;
    @ApiModelProperty(value = "车辆种类B")
    private String carTypeB;

    @ApiModelProperty(value = "上市年份")
    private String saleYear;
    @ApiModelProperty(value = "功率")
    private BigDecimal kws;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "所属性质")
    private String belongType;
    @ApiModelProperty(value = "使用性质")
    private String useType;

    @ApiModelProperty(value = "官方指导价")
    private BigDecimal carNewPrice;

    @ApiModelProperty(value = "座位数")
    private Integer siteCount;

    @ApiModelProperty(value = "载重")
    private BigDecimal loadCapacity;

    @ApiModelProperty(value = "排量")
    private BigDecimal displacement;

    @ApiModelProperty(value = "车系分类")
    private String carSieralType;

    @ApiModelProperty(value = "购置价")
    private BigDecimal buyPrice;

    @ApiModelProperty(value = "含税购置价")
    private BigDecimal andRateBuyPrice;
}