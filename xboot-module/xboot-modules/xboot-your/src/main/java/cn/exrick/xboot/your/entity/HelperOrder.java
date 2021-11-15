package cn.exrick.xboot.your.entity;

import cn.exrick.xboot.core.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

/**
 * @author Jingqi Chen
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "b_helper_order")
@TableName("b_helper_order")
@ApiModel(value = "互助单主表")
public class HelperOrder extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "统筹单id")
    private String orderId;

    @ApiModelProperty(value = "被统筹人姓名")
    private String clientName;
    @ApiModelProperty(value = "车型名称")
    private String carTypeName;
    @ApiModelProperty(value = "省份   比如  鲁 陕   数据字典维护")
    private String carNoHead;
    @ApiModelProperty(value = "具体车牌号码")
    private String carNo;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统筹开始时间")
    private LocalDateTime startDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统筹结束时间")
    private LocalDateTime endDate;
    @ApiModelProperty(value = "发动机号")
    private String motorNo;

    @ApiModelProperty(value = "车架号")
    private String carframeNo;

    @ApiModelProperty(value = "报案人")
    private String reportName;
    @ApiModelProperty(value = "报案电话")
    private String reportTel;

    @ApiModelProperty(value = "事故类型")
    private String accidentType;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "事故时间")
    private LocalDateTime accidentTime;

    @ApiModelProperty(value = "三者车牌")
    private String thirdCarNo;

    @ApiModelProperty(value = "三者车型")
    private String thirdCarType;

    @ApiModelProperty(value = "三者电话")
    private String thirdPhone;

    @ApiModelProperty(value = "事故地点")
    private String accidentPlace;

    @ApiModelProperty(value = "事故概况")
    private String accidentDesc;

    @ApiModelProperty(value = "事故备注说明")
    private String remark;

    @ApiModelProperty(value = "事故处理状态")
    private String status;

    @ApiModelProperty(value = "创建人部门id")
    private String departmentId;

    @ApiModelProperty(value = "调查员id")
    private String diaochaUserId;
    @ApiModelProperty(value = "调查员姓名")
    @Transient
    private String diaochaUserName;

}