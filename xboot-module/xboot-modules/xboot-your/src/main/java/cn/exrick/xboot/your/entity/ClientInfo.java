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
@Table(name = "b_client_info")
@TableName("b_client_info")
@ApiModel(value = "客户信息维护")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_client_info set del_flag = 1 where id = ?")
public class ClientInfo extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "客户姓名")
    private String name;
    @ApiModelProperty(value = "客户性质")
    private String clientType;
    @ApiModelProperty(value = "证件号码")
    private String idNo;
    @ApiModelProperty(value = "手机号码")
    private String telephone;

    @ApiModelProperty(value = "性别")
    private String sex;
}