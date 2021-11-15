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
@Table(name = "b_helper_order_pic")
@TableName("b_helper_order_pic")
@ApiModel(value = "互助单附件图片")
public class HelperOrderPic extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类")
    private String classify;


    @ApiModelProperty(value = "互助单id")
    private String helperOrderId;

    @ApiModelProperty(value = "附件地址")
    private String fileUrl;


    @ApiModelProperty(value = "照片备注")
    private String remark;
}