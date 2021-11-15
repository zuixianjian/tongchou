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
@Table(name = "b_helper_order_compensate")
@TableName("b_helper_order_compensate")
@ApiModel(value = "互助单赔付信息")
public class HelperOrderCompensate extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "互助单id")
    private String helperOrderId;

    @ApiModelProperty(value = "总损失金额")
    private BigDecimal totalLoss=new BigDecimal(0);
    @ApiModelProperty(value = "责任占比(%)")
    private BigDecimal dutyPercent=new BigDecimal(0);
    @ApiModelProperty(value = "交强险补偿金额")
    private BigDecimal compulsoryCompensateMoney=new BigDecimal(0);
    @ApiModelProperty(value = "代调查费")
    private BigDecimal investigateMoney=new BigDecimal(0);
    @ApiModelProperty(value = "诉讼费")
    private BigDecimal lawsuitMoney=new BigDecimal(0);
    @ApiModelProperty(value = "仲裁费")
    private BigDecimal arbitrateMoney=new BigDecimal(0);
    @ApiModelProperty(value = "公估费")
    private BigDecimal publicMoney=new BigDecimal(0);
    @ApiModelProperty(value = "鉴定费")
    private BigDecimal appraisalMoney=new BigDecimal(0);
    @ApiModelProperty(value = "其他费用")
    private BigDecimal oteherMoney=new BigDecimal(0);
    @ApiModelProperty(value = "垫付金额")
    private BigDecimal dianfuMoney=new BigDecimal(0);
    @ApiModelProperty(value = "统筹补偿金额")
    private BigDecimal compensateMoney=new BigDecimal(0);
    @ApiModelProperty(value = "待补偿金额")
    private BigDecimal waitForCompensateMoney=new BigDecimal(0);
    @ApiModelProperty(value = "补偿对象")
    private String compensateTarget="";
    @ApiModelProperty(value = "开户行")
    private String openBankName="";
    @ApiModelProperty(value = "银行账号")
    private String bankNo="";





}