package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.core.common.utils.SecurityUtil;
import cn.exrick.xboot.core.entity.Department;
import cn.exrick.xboot.core.entity.User;
import cn.exrick.xboot.core.service.DepartmentService;
import cn.exrick.xboot.your.dao.RateConfigDao;
import cn.exrick.xboot.your.entity.ChannelDiscount;
import cn.exrick.xboot.your.entity.RateConfig;
import cn.exrick.xboot.your.mapper.RateConfigMapper;
import cn.exrick.xboot.your.service.ChannelDiscountService;
import cn.exrick.xboot.your.service.IDepartmentService;
import cn.exrick.xboot.your.service.RateConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.reflect.Field;

/**
 * 费率设置接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class RateConfigServiceImpl implements RateConfigService {

    @Autowired
    private RateConfigDao rateConfigDao;

    @Autowired
    private RateConfigMapper rateConfigMapper;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private ChannelDiscountService channelDiscountService;
    @Override
    public RateConfigDao getRepository() {
        return rateConfigDao;
    }

    @Override
    public Page<RateConfig> findByCondition(RateConfig rateConfig, SearchVo searchVo, Pageable pageable) {

        return rateConfigDao.findAll(new Specification<RateConfig>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<RateConfig> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> carTypeAField = root.get("carTypeA");
                Path<String> carTypeBField = root.get("carTypeB");
                Path<String> belongTypeField = root.get("belongType");
                Path<String> useTypeField = root.get("useType");
                Path<String> carSieralTypeField = root.get("carSieralType");
                Path<String> yearTypeField = root.get("yearType");
                Path<Integer> insuranceMoneyField = root.get("insuranceMoney");
                Path<String> regionGroupField = root.get("regionGroup");
                Path<String> feeTypeField = root.get("feeType");
                Path<java.math.BigDecimal> baseFeeField = root.get("baseFee");
                Path<java.math.BigDecimal> feeRateField = root.get("feeRate");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 车辆种类模糊搜素
                if(StrUtil.isNotBlank(rateConfig.getCarTypeA())){
                    list.add(cb.like(carTypeAField,'%'+rateConfig.getCarTypeA()+'%'));
                }
                if(StrUtil.isNotBlank(rateConfig.getCarTypeB())){
                    list.add(cb.like(carTypeBField,'%'+rateConfig.getCarTypeB()+'%'));
                }
                // 所属性质模糊搜素
                if(StrUtil.isNotBlank(rateConfig.getBelongType())){
                    list.add(cb.like(belongTypeField,'%'+rateConfig.getBelongType()+'%'));
                }
                // 使用性质模糊搜素
                if(StrUtil.isNotBlank(rateConfig.getUseType())){
                    list.add(cb.like(useTypeField,'%'+rateConfig.getUseType()+'%'));
                }
                // 车系分类模糊搜素
                if(StrUtil.isNotBlank(rateConfig.getCarSieralType())){
                    list.add(cb.like(carSieralTypeField,'%'+rateConfig.getCarSieralType()+'%'));
                }
                // 年限模糊搜素
                if(StrUtil.isNotBlank(rateConfig.getYearType())){
                    list.add(cb.like(yearTypeField,'%'+rateConfig.getYearType()+'%'));
                }
                // 保额  单位万相等匹配
                if(rateConfig.getInsuranceMoney()!=null){
                    list.add(cb.equal(insuranceMoneyField, rateConfig.getInsuranceMoney()));
                }
                // 费率组模糊搜素
                if(StrUtil.isNotBlank(rateConfig.getRegionGroup())){
                    list.add(cb.like(regionGroupField,'%'+rateConfig.getRegionGroup()+'%'));
                }
                // 费率类型：车损 三者等  数据字典维护模糊搜素
                if(StrUtil.isNotBlank(rateConfig.getFeeType())){
                    list.add(cb.like(feeTypeField,'%'+rateConfig.getFeeType()+'%'));
                }
                // 基础保费相等匹配
                if(rateConfig.getBaseFee()!=null){
                    list.add(cb.equal(baseFeeField, rateConfig.getBaseFee()));
                }
                // 费率相等匹配
                if(rateConfig.getFeeRate()!=null){
                    list.add(cb.equal(feeRateField, rateConfig.getFeeRate()));
                }

                // 创建时间
                if (searchVo!=null){
                    if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
                        Date start = DateUtil.parse(searchVo.getStartDate());
                        Date end = DateUtil.parse(searchVo.getEndDate());
                        list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                    }
                }


                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public List<RateConfig> getMineFeeList(RateConfig rateConfig) {
        //获取当前用户的费率组，如果没有费率组 按车牌号来
        // 获取当前登录用户
        User user = securityUtil.getCurrUser();
        //获取该用户所在部门的所有顶级部门(包括自己所在部门)
        String  rateRegionGroup = null;
        String  discountRegionGroup = null;
        if (user.getDepartmentId() != null) {
            List<Department> departments = departmentService.getAllHeadDepartmentsByDepartmentId(user.getDepartmentId());
            if (departments != null) {
                for (Department department : departments) {
                    List<ChannelDiscount> channelDiscounts = channelDiscountService.findByDepartMentId(department.getId());
                    if (channelDiscounts != null && channelDiscounts.size() > 0) {
                        discountRegionGroup = channelDiscounts.get(0).getDiscountRegionGroup();
                        rateRegionGroup = channelDiscounts.get(0).getRateRegionGroup();
                        break;
                    }
                }

            }
        }
        if (rateRegionGroup!=null){
            rateConfig.setRegionGroup(rateRegionGroup);
        }
        List<RateConfig> configs=new ArrayList<>();
        if ("carloss".equals(rateConfig.getFeeType())){
            configs=rateConfigMapper.getCarloss(rateConfig);
            if (configs!=null){
                ArrayList<RateConfig> confs = new ArrayList<>();
                for (RateConfig config : configs) {
                    if (Float.parseFloat(config.getYearType())>Float.parseFloat(rateConfig.getYearType())){
                        if (confs.size()==0){
                            confs.add(config);
                        }else  {
                            if (Float.parseFloat(config.getYearType())<Float.parseFloat(confs.get(0).getYearType())){
                                confs.set(0,config);
                            }
                        }
                    }

                }
                configs=confs;
            }
        }else if ("thirdDuty".equals(rateConfig.getFeeType())){
            configs=rateConfigMapper.getThirdDuty(rateConfig);
        }else if ("driver".equals(rateConfig.getFeeType())){
            configs=rateConfigMapper.getDriverOrPassenger(rateConfig);
        }else if ("passenger".equals(rateConfig.getFeeType())){
            configs=rateConfigMapper.getDriverOrPassenger(rateConfig);
        }
      if (configs.size()>1){
          return null;
      }else {
          return configs;
      }

    }
}