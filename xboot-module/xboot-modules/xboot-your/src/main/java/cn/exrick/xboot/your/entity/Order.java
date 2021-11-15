package cn.exrick.xboot.your.entity;

import cn.exrick.xboot.core.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Jingqi Chen
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "b_order")
@TableName("b_order")
@ApiModel(value = "统筹单列表")
@Where(clause = "del_flag = 0")
@SQLDelete(sql = "update b_order set del_flag = 1 where id = ?")
public class Order extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "期限类型")
    private String timeLimitType;

    @ApiModelProperty(value = "自然月数")
    private String month;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统筹开始时间")
    private LocalDateTime startDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统筹结束时间")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "短期折扣")
    private BigDecimal shortDiscount;

    //客户信息
    //被统筹人
    @ApiModelProperty(value = "被统筹人姓名")
    private String clientName;
    @ApiModelProperty(value = "被统筹人证件号码")
    private String clientCardNo;
    @ApiModelProperty(value = "被统筹人类型  数据字典")
    private String clientType;
    @ApiModelProperty(value = "被统筹人电话")
    private String clientPhone;
    //办理人
    @ApiModelProperty(value = "办理人姓名")
    private String handleName;
    @ApiModelProperty(value = "办理人证件号码")
    private String handleCardNo;
    @ApiModelProperty(value = "办理人电话")
    private String handlePhone;
    //行驶证车主
    @ApiModelProperty(value = "行驶证车主姓名")
    private String runCardName;
    @ApiModelProperty(value = "行驶证车主证件号码")
    private String runCardCardNo;
    @ApiModelProperty(value = "行驶证车主电话")
    private String runCardPhone;
    //车型信息
    //车辆种类
    @ApiModelProperty(value = "车辆种类A")
    private String carTypeA;
    @ApiModelProperty(value = "车辆种类B")
    private String carTypeB;
    @ApiModelProperty(value = "车型名称")
    private String carTypeName;
    @ApiModelProperty(value = "车型代码")
    private String carCode;
    @ApiModelProperty(value = "新车购置价")
    private BigDecimal carNewPrice;
    @ApiModelProperty(value = "排量")
    private BigDecimal displacement;
    @ApiModelProperty(value = "核定载重")
    private BigDecimal loadCapacity;
    @ApiModelProperty(value = "整备质量")
    private BigDecimal weight;
    @ApiModelProperty(value = "功率")
    private BigDecimal kws;
    @ApiModelProperty(value = "购置价")
    private BigDecimal buyPrice;
    //车辆信息

    @ApiModelProperty(value = "省份   比如  鲁 陕   数据字典维护")
    @Deprecated
    private String carNoHead;
    @ApiModelProperty(value = "具体车牌号码")
    private String carNo;
    @ApiModelProperty(value = "使用性质")
    private String useType;
    @ApiModelProperty(value = "所属性质")
    private String belongType;

    @ApiModelProperty(value = "行驶证车辆类型")
    private String runCardCarType;
    @ApiModelProperty(value = "车系分类")
    private String carSieralType;
    @ApiModelProperty(value = "座位数")
    private Integer siteCount;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "初登日期")
    private Date firstLogDate;
    @Deprecated
    @ApiModelProperty(value = "折旧前车价")//暂时废弃
    private BigDecimal beforeMonthOldRatePrice;

    @ApiModelProperty(value = "月折旧率")
    private BigDecimal monthOldRate;

    @ApiModelProperty(value = "折旧后车价")
    private BigDecimal monthOldRatePrice;

    @ApiModelProperty(value = "协商车价")
    private BigDecimal negotiatePrice;

    @ApiModelProperty(value = "发动机号")
    private String motorNo;

    @ApiModelProperty(value = "车架号")
    private String carframeNo;

    @ApiModelProperty(value = "是否需要验车")
    private Boolean isCheckCar;

    @ApiModelProperty(value = "统筹单状态")
    private String orderStatus;

    @ApiModelProperty(value = "统筹总金额")
    private BigDecimal totalFee;
    @ApiModelProperty(value = "费率浮动因素(优惠)")
    private String feeFloatReason;

    @ApiModelProperty(value = "费率浮动比例(优惠)")
    private String feeFloatRate;

    @ApiModelProperty(value = "浮动后统筹金额")
    private BigDecimal feeFloat;

    @ApiModelProperty(value = "特别约定")
    private String  orderAgree;

    @ApiModelProperty(value = "渠道折扣")
    private BigDecimal discountRate;

    @ApiModelProperty(value = "优惠和渠道折扣后金额")
    private BigDecimal afterDiscountMoney;
    @ApiModelProperty(value = "备注信息")
    private String remark;




    //批单相关字段

    @ApiModelProperty(value = "订单类型，批单还是订单")
    private String orderType;

    @ApiModelProperty(value = "统筹单id")
    private String parentId;

    @ApiModelProperty(value = "原统筹单数据缓存")
    private String originData;

    @ApiModelProperty(value = "创建人部门id")
    private String departmentId;


    @Transient
    private String queryId;
}