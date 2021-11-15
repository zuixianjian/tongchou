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
import java.time.LocalDateTime;

/**
 * @author Jingqi Chen
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "b_check_code")
@TableName("b_check_code")
@ApiModel(value = "验车码")
public class CheckCode extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "验车码")
    private String code;
    @ApiModelProperty(value = "日期")
    private LocalDateTime generateDate;

}