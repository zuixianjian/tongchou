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

/**
 * @author Jingqi Chen
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "b_car_type_relation")
@TableName("b_car_type_relation")
@ApiModel(value = "车型关联信息表")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_car_type_relation set del_flag = 1 where id = ?")
public class CarTypeRelation extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "车型一级分类")
    private String carTypeA;
    @ApiModelProperty(value = "车型二级分类")
    private String carTypeB;
}