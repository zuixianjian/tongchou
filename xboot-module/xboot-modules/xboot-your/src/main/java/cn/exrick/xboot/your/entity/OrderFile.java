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
@Table(name = "b_order_file")
@TableName("b_order_file")
@ApiModel(value = "统筹单附件")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_order_file set del_flag = 1 where id = ?")
public class OrderFile extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "统筹单id")
    private String orderId;

    @ApiModelProperty(value = "附件地址")
    private String fileUrl;

}