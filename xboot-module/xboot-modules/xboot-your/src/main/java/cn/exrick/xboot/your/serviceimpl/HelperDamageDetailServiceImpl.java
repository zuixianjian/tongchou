package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.HelperDamageDetailDao;
import cn.exrick.xboot.your.entity.HelperDamageDetail;
import cn.exrick.xboot.your.service.HelperDamageDetailService;
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
 * 互助单损失明细接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class HelperDamageDetailServiceImpl implements HelperDamageDetailService {

    @Autowired
    private HelperDamageDetailDao helperDamageDetailDao;

    @Override
    public HelperDamageDetailDao getRepository() {
        return helperDamageDetailDao;
    }

    @Override
    public Page<HelperDamageDetail> findByCondition(HelperDamageDetail helperDamageDetail, SearchVo searchVo, Pageable pageable) {

        return helperDamageDetailDao.findAll(new Specification<HelperDamageDetail>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<HelperDamageDetail> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> helperDamageIdField = root.get("helperDamageId");
                Path<String> damageProjectNameField = root.get("damageProjectName");
                Path<String> damageProjectIdField = root.get("damageProjectId");
                Path<String> supplierLevelField = root.get("supplierLevel");
                Path<java.math.BigDecimal> priceField = root.get("price");
                Path<Integer> countField = root.get("count");
                Path<java.math.BigDecimal> totalPriceField = root.get("totalPrice");
                Path<java.math.BigDecimal> checkPriceField = root.get("checkPrice");
                Path<Integer> checkCountField = root.get("checkCount");
                Path<java.math.BigDecimal> checkTotalPriceField = root.get("checkTotalPrice");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 损失主表id模糊搜素
                if(StrUtil.isNotBlank(helperDamageDetail.getHelperDamageId())){
                    list.add(cb.like(helperDamageIdField,'%'+helperDamageDetail.getHelperDamageId()+'%'));
                }
                // 损失项目名称模糊搜素
                if(StrUtil.isNotBlank(helperDamageDetail.getDamageProjectName())){
                    list.add(cb.like(damageProjectNameField,'%'+helperDamageDetail.getDamageProjectName()+'%'));
                }
                // 损失项目编码模糊搜素
                if(StrUtil.isNotBlank(helperDamageDetail.getDamageProjectId())){
                    list.add(cb.like(damageProjectIdField,'%'+helperDamageDetail.getDamageProjectId()+'%'));
                }
                // 供应商级别模糊搜素
                if(StrUtil.isNotBlank(helperDamageDetail.getSupplierLevel())){
                    list.add(cb.like(supplierLevelField,'%'+helperDamageDetail.getSupplierLevel()+'%'));
                }
                // 单价相等匹配
                if(helperDamageDetail.getPrice()!=null){
                    list.add(cb.equal(priceField, helperDamageDetail.getPrice()));
                }
                // 损失项目数量相等匹配
                if(helperDamageDetail.getCount()!=null){
                    list.add(cb.equal(countField, helperDamageDetail.getCount()));
                }
                // 小计相等匹配
                if(helperDamageDetail.getTotalPrice()!=null){
                    list.add(cb.equal(totalPriceField, helperDamageDetail.getTotalPrice()));
                }
                // 审核单价相等匹配
                if(helperDamageDetail.getCheckPrice()!=null){
                    list.add(cb.equal(checkPriceField, helperDamageDetail.getCheckPrice()));
                }
                // 审核损失项目数量相等匹配
                if(helperDamageDetail.getCheckCount()!=null){
                    list.add(cb.equal(checkCountField, helperDamageDetail.getCheckCount()));
                }
                // 审核小计相等匹配
                if(helperDamageDetail.getCheckTotalPrice()!=null){
                    list.add(cb.equal(checkTotalPriceField, helperDamageDetail.getCheckTotalPrice()));
                }

                // 创建时间
                if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

}