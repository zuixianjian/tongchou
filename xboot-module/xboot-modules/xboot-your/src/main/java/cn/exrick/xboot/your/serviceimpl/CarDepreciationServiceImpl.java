package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.CarDepreciationDao;
import cn.exrick.xboot.your.entity.CarDepreciation;
import cn.exrick.xboot.your.service.CarDepreciationService;
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
 * 月折旧率接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class CarDepreciationServiceImpl implements CarDepreciationService {

    @Autowired
    private CarDepreciationDao carDepreciationDao;

    @Override
    public CarDepreciationDao getRepository() {
        return carDepreciationDao;
    }

    @Override
    public Page<CarDepreciation> findByCondition(CarDepreciation carDepreciation, SearchVo searchVo, Pageable pageable) {

        return carDepreciationDao.findAll(new Specification<CarDepreciation>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<CarDepreciation> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> useTypeField = root.get("useType");
                Path<String> carTypeAField = root.get("carTypeA");
                Path<String> carTypeBField = root.get("carTypeB");
                Path<java.math.BigDecimal> monthRateField = root.get("monthRate");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 使用性质模糊搜素
                if(StrUtil.isNotBlank(carDepreciation.getUseType())){
                    list.add(cb.like(useTypeField,'%'+carDepreciation.getUseType()+'%'));
                }
                // 车辆种类模糊搜素
                if(StrUtil.isNotBlank(carDepreciation.getCarTypeA())){
                    list.add(cb.like(carTypeAField,'%'+carDepreciation.getCarTypeA()+'%'));
                }
                if(StrUtil.isNotBlank(carDepreciation.getCarTypeB())){
                    list.add(cb.like(carTypeBField,'%'+carDepreciation.getCarTypeB()+'%'));
                }
                // 月折旧率相等匹配
                if(carDepreciation.getMonthRate()!=null){
                    list.add(cb.equal(monthRateField, carDepreciation.getMonthRate()));
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