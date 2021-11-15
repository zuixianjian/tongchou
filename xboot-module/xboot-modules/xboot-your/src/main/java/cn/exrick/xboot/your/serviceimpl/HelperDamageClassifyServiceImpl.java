package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.your.dao.HelperDamageClassifyDao;
import cn.exrick.xboot.your.dao.HelperDamageDao;
import cn.exrick.xboot.your.dao.HelperDamageDetailDao;
import cn.exrick.xboot.your.dao.HelperOrderDao;
import cn.exrick.xboot.your.entity.HelperDamage;
import cn.exrick.xboot.your.entity.HelperDamageClassify;
import cn.exrick.xboot.your.entity.HelperDamageDetail;
import cn.exrick.xboot.your.entity.HelperOrder;
import cn.exrick.xboot.your.entity.resp.DamageListClassifyItem;
import cn.exrick.xboot.your.entity.resp.DamageListHelperDamageDetailItem;
import cn.exrick.xboot.your.entity.resp.DamageListResp;
import cn.exrick.xboot.your.entity.resp.DamangeListHelperDamageItem;
import cn.exrick.xboot.your.service.HelperDamageClassifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
 * 互助单损失主表分类(记录相应的险种以及损失额)接口实现
 *
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class HelperDamageClassifyServiceImpl implements HelperDamageClassifyService {

    @Autowired
    private HelperDamageClassifyDao helperDamageClassifyDao;
    @Autowired
    private HelperOrderDao helperOrderDao;
    @Autowired
    private HelperDamageDao helperDamageDao;
    @Autowired
    private HelperDamageDetailDao helperDamageDetailDao;

    @Override
    public HelperDamageClassifyDao getRepository() {
        return helperDamageClassifyDao;
    }

    @Override
    public Page<HelperDamageClassify> findByCondition(HelperDamageClassify helperDamageClassify, SearchVo searchVo, Pageable pageable) {

        return helperDamageClassifyDao.findAll(new Specification<HelperDamageClassify>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<HelperDamageClassify> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> helperOrderIdField = root.get("helperOrderId");
                Path<String> feeTypeField = root.get("feeType");
                Path<Integer> insuranceMoneyField = root.get("insuranceMoney");
                Path<java.math.BigDecimal> deductibleFeeField = root.get("deductibleFee");
                Path<java.math.BigDecimal> damangeTotalMoneyField = root.get("damangeTotalMoney");
                Path<Boolean> isDecideField = root.get("isDecide");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 互助单id模糊搜素
                if (StrUtil.isNotBlank(helperDamageClassify.getHelperOrderId())) {
                    list.add(cb.like(helperOrderIdField, '%' + helperDamageClassify.getHelperOrderId() + '%'));
                }
                // 费率类型：车损 三者等  数据字典维护模糊搜素
                if (StrUtil.isNotBlank(helperDamageClassify.getFeeType())) {
                    list.add(cb.like(feeTypeField, '%' + helperDamageClassify.getFeeType() + '%'));
                }
                // 总保额  单位(限额)相等匹配
                if (helperDamageClassify.getInsuranceMoney() != null) {
                    list.add(cb.equal(insuranceMoneyField, helperDamageClassify.getInsuranceMoney()));
                }
                // 不计免责相等匹配
                if (helperDamageClassify.getDeductibleFee() != null) {
                    list.add(cb.equal(deductibleFeeField, helperDamageClassify.getDeductibleFee()));
                }
                // 该类别总损失金额相等匹配
                if (helperDamageClassify.getDamangeTotalMoney() != null) {
                    list.add(cb.equal(damangeTotalMoneyField, helperDamageClassify.getDamangeTotalMoney()));
                }
                // 是否已决相等匹配
                if (helperDamageClassify.getIsDecide() != null) {
                    list.add(cb.equal(isDecideField, helperDamageClassify.getIsDecide()));
                }

                // 创建时间
                if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
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

    @Override
    public DamageListResp getDamageList(String helpOrderId) {
        DamageListResp damageListResp = new DamageListResp();

        HelperOrder helperOrder = helperOrderDao.findById(helpOrderId).get();
        if (helperOrder == null) {
            throw new XbootException("该互助单不存在");
        }
        List<HelperDamageClassify> classifies = helperDamageClassifyDao.getByHelperOrderId(helpOrderId);
        if (classifies!=null){
            ArrayList<DamageListClassifyItem> damageListClassifyItems = new ArrayList<>();
            for (HelperDamageClassify classify : classifies) {

                DamageListClassifyItem damageListClassifyItem = new DamageListClassifyItem();
                    BeanUtils.copyProperties(classify,damageListClassifyItem);
                List<HelperDamage> helperDamages = helperDamageDao.getByHelperOrderIdAndAndHelperDamageClassifyId(helpOrderId, classify.getId());
                if (helperDamages!=null){

                    ArrayList<DamangeListHelperDamageItem> damangeListHelperDamageItems = new ArrayList<>();

                    for (HelperDamage helperDamage : helperDamages) {
                        DamangeListHelperDamageItem damangeListHelperDamageItem = new DamangeListHelperDamageItem();
                        BeanUtils.copyProperties(helperDamage,damangeListHelperDamageItem);
                        List<HelperDamageDetail> helperDamageDetails = helperDamageDetailDao.getByHelperDamageId(helperDamage.getId());
                        if (helperDamageDetails!=null){
                            ArrayList<DamageListHelperDamageDetailItem> damageListHelperDamageDetailItems = new ArrayList<>();
                            for (HelperDamageDetail helperDamageDetail : helperDamageDetails) {
                                DamageListHelperDamageDetailItem damageListHelperDamageDetailItem = new DamageListHelperDamageDetailItem();
                                BeanUtils.copyProperties(helperDamageDetail,damageListHelperDamageDetailItem);
                                damageListHelperDamageDetailItems.add(damageListHelperDamageDetailItem);
                            }
                            damangeListHelperDamageItem.setChildren(damageListHelperDamageDetailItems);
                        }
                        damangeListHelperDamageItems.add(damangeListHelperDamageItem);
                    }

                    damageListClassifyItem.setChildren(damangeListHelperDamageItems);
                }

                damageListClassifyItems.add(damageListClassifyItem);
                damageListResp.setDamageListClassifyItems(damageListClassifyItems);
            }
        }






        return damageListResp;
    }
}