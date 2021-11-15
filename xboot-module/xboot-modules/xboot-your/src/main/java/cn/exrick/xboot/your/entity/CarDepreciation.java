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
@Table(name = "b_car_depreciation")
@TableName("b_car_depreciation")
@ApiModel(value = "车型月折旧率")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_car_depreciation set del_flag = 1 where id = ?")
public class CarDepreciation extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "使用性质")
    private String useType;
    @ApiModelProperty(value = "一级分类")
    private String carTypeA;
    @ApiModelProperty(value = "二级分类")
    private String carTypeB;
    @ApiModelProperty(value = "月折旧率")
    private BigDecimal monthRate;

}