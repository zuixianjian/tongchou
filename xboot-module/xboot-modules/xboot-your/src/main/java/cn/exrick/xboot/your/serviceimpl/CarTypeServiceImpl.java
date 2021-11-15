package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.CarTypeDao;
import cn.exrick.xboot.your.entity.CarType;
import cn.exrick.xboot.your.service.CarTypeService;
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

import java.util.List;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.reflect.Field;

/**
 * 车型信息维护接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class CarTypeServiceImpl implements CarTypeService {

    @Autowired
    private CarTypeDao carTypeDao;

    @Override
    public CarTypeDao getRepository() {
        return carTypeDao;
    }

    @Override
    public Page<CarType> findByCondition(CarType carType, SearchVo searchVo, Pageable pageable) {

        return carTypeDao.findAll(new Specification<CarType>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<CarType> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> carTypeNameField = root.get("carTypeName");
                Path<String> codeField = root.get("code");
                Path<String> carTypeAField = root.get("carTypeA");
                Path<String> carTypeBField = root.get("carTypeB");
                Path<String> belongTypeField = root.get("belongType");
                Path<String> useTypeField = root.get("useType");
                Path<java.math.BigDecimal> carNewPriceField = root.get("carNewPrice");
                Path<Integer> siteCountField = root.get("siteCount");
                Path<java.math.BigDecimal> loadCapacityField = root.get("loadCapacity");
                Path<java.math.BigDecimal> displacementField = root.get("displacement");
                Path<String> carSieralTypeField = root.get("carSieralType");
                Path<java.math.BigDecimal> buyPriceField = root.get("buyPrice");
                Path<java.math.BigDecimal> andRateBuyPriceField = root.get("andRateBuyPrice");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 车型名称模糊搜素
                if(StrUtil.isNotBlank(carType.getCarTypeName())){
                    list.add(cb.like(carTypeNameField,'%'+carType.getCarTypeName()+'%'));
                }
                // 车型代码模糊搜素
                if(StrUtil.isNotBlank(carType.getCode())){
                    list.add(cb.like(codeField,'%'+carType.getCode()+'%'));
                }
                // 车辆种类模糊搜素
                if(StrUtil.isNotBlank(carType.getCarTypeA())){
                    list.add(cb.like(carTypeAField,'%'+carType.getCarTypeA()+'%'));
                }
                if(StrUtil.isNotBlank(carType.getCarTypeB())){
                    list.add(cb.like(carTypeBField,'%'+carType.getCarTypeB()+'%'));
                }
                // 所属性质模糊搜素
                if(StrUtil.isNotBlank(carType.getBelongType())){
                    list.add(cb.like(belongTypeField,'%'+carType.getBelongType()+'%'));
                }
                // 使用性质模糊搜素
                if(StrUtil.isNotBlank(carType.getUseType())){
                    list.add(cb.like(useTypeField,'%'+carType.getUseType()+'%'));
                }
                // 新车购置价相等匹配
                if(carType.getCarNewPrice()!=null){
                    list.add(cb.equal(carNewPriceField, carType.getCarNewPrice()));
                }
                // 座位数相等匹配
                if(carType.getSiteCount()!=null){
                    list.add(cb.equal(siteCountField, carType.getSiteCount()));
                }
                // 载重相等匹配
                if(carType.getLoadCapacity()!=null){
                    list.add(cb.equal(loadCapacityField, carType.getLoadCapacity()));
                }
                // 排量相等匹配
                if(carType.getDisplacement()!=null){
                    list.add(cb.equal(displacementField, carType.getDisplacement()));
                }
                // 车系分类模糊搜素
                if(StrUtil.isNotBlank(carType.getCarSieralType())){
                    list.add(cb.like(carSieralTypeField,'%'+carType.getCarSieralType()+'%'));
                }
                // 购置价相等匹配
                if(carType.getBuyPrice()!=null){
                    list.add(cb.equal(buyPriceField, carType.getBuyPrice()));
                }
                // 含税购置价相等匹配
                if(carType.getAndRateBuyPrice()!=null){
                    list.add(cb.equal(andRateBuyPriceField, carType.getAndRateBuyPrice()));
                }

                // 创建时间
                if(searchVo!=null){
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



}