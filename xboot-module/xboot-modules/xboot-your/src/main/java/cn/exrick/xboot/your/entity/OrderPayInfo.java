package cn.exrick.xboot.your.entity;

import cn.exrick.xboot.core.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jingqi Chen
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "b_order_pay_info")
@TableName("b_order_pay_info")
@ApiModel(value = "批单/统筹单收款单")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_order_pay_info set del_flag = 1 where id = ?")
public class  OrderPayInfo extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "收款单类型")
    private String bussinessType;

    @ApiModelProperty(value = "业务id")
    private String bussinessId;

    @ApiModelProperty(value = "收款人")
    private String recieveMan;

    @ApiModelProperty(value = "应收全款")
    private BigDecimal shouldRecieve;

    @ApiModelProperty(value = "应收净费")
    private BigDecimal shouldCleanRecieve;


    @ApiModelProperty(value = "实收款项")
    private BigDecimal realRecieve;


    @ApiModelProperty(value = "收款方式")
    private String recieveType;

    @ApiModelProperty(value = "付款账号")
    private String sendNo;

    @ApiModelProperty(value = "收款凭证附件")
    private String recieveEvidence;

    @ApiModelProperty(value = "收款单状态")
    private String recieveStatus;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "收款时间")
    private LocalDateTime recieveTime;

    @ApiModelProperty(value = "创建人部门id")
    private String departmentId;
}