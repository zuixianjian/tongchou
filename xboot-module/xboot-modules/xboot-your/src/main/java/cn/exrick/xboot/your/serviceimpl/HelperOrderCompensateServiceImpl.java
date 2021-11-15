package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.HelperOrderCompensateDao;
import cn.exrick.xboot.your.entity.HelperOrderCompensate;
import cn.exrick.xboot.your.service.HelperOrderCompensateService;
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
 * 互助单赔付信息接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class HelperOrderCompensateServiceImpl implements HelperOrderCompensateService {

    @Autowired
    private HelperOrderCompensateDao helperOrderCompensateDao;

    @Override
    public HelperOrderCompensateDao getRepository() {
        return helperOrderCompensateDao;
    }

    @Override
    public Page<HelperOrderCompensate> findByCondition(HelperOrderCompensate helperOrderCompensate, SearchVo searchVo, Pageable pageable) {

        return helperOrderCompensateDao.findAll(new Specification<HelperOrderCompensate>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<HelperOrderCompensate> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<java.math.BigDecimal> totalLossField = root.get("totalLoss");
                Path<java.math.BigDecimal> dutyPercentField = root.get("dutyPercent");
                Path<java.math.BigDecimal> compulsoryCompensateMoneyField = root.get("compulsoryCompensateMoney");
                Path<java.math.BigDecimal> investigateMoneyField = root.get("investigateMoney");
                Path<java.math.BigDecimal> lawsuitMoneyField = root.get("lawsuitMoney");
                Path<java.math.BigDecimal> arbitrateMoneyField = root.get("arbitrateMoney");
                Path<java.math.BigDecimal> publicMoneyField = root.get("publicMoney");
                Path<java.math.BigDecimal> appraisalMoneyField = root.get("appraisalMoney");
                Path<java.math.BigDecimal> oteherMoneyField = root.get("oteherMoney");
                Path<java.math.BigDecimal> dianfuMoneyField = root.get("dianfuMoney");
                Path<java.math.BigDecimal> compensateMoneyField = root.get("compensateMoney");
                Path<java.math.BigDecimal> waitForCompensateMoneyField = root.get("waitForCompensateMoney");
                Path<String> compensateTargetField = root.get("compensateTarget");
                Path<String> openBankNameField = root.get("openBankName");
                Path<String> bankNoField = root.get("bankNo");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 总损失金额相等匹配
                if(helperOrderCompensate.getTotalLoss()!=null){
                    list.add(cb.equal(totalLossField, helperOrderCompensate.getTotalLoss()));
                }
                // 责任占比(%)相等匹配
                if(helperOrderCompensate.getDutyPercent()!=null){
                    list.add(cb.equal(dutyPercentField, helperOrderCompensate.getDutyPercent()));
                }
                // 交强险补偿金额相等匹配
                if(helperOrderCompensate.getCompulsoryCompensateMoney()!=null){
                    list.add(cb.equal(compulsoryCompensateMoneyField, helperOrderCompensate.getCompulsoryCompensateMoney()));
                }
                // 代调查费相等匹配
                if(helperOrderCompensate.getInvestigateMoney()!=null){
                    list.add(cb.equal(investigateMoneyField, helperOrderCompensate.getInvestigateMoney()));
                }
                // 诉讼费相等匹配
                if(helperOrderCompensate.getLawsuitMoney()!=null){
                    list.add(cb.equal(lawsuitMoneyField, helperOrderCompensate.getLawsuitMoney()));
                }
                // 仲裁费相等匹配
                if(helperOrderCompensate.getArbitrateMoney()!=null){
                    list.add(cb.equal(arbitrateMoneyField, helperOrderCompensate.getArbitrateMoney()));
                }
                // 公估费相等匹配
                if(helperOrderCompensate.getPublicMoney()!=null){
                    list.add(cb.equal(publicMoneyField, helperOrderCompensate.getPublicMoney()));
                }
                // 鉴定费相等匹配
                if(helperOrderCompensate.getAppraisalMoney()!=null){
                    list.add(cb.equal(appraisalMoneyField, helperOrderCompensate.getAppraisalMoney()));
                }
                // 其他费用相等匹配
                if(helperOrderCompensate.getOteherMoney()!=null){
                    list.add(cb.equal(oteherMoneyField, helperOrderCompensate.getOteherMoney()));
                }
                // 垫付金额相等匹配
                if(helperOrderCompensate.getDianfuMoney()!=null){
                    list.add(cb.equal(dianfuMoneyField, helperOrderCompensate.getDianfuMoney()));
                }
                // 统筹补偿金额相等匹配
                if(helperOrderCompensate.getCompensateMoney()!=null){
                    list.add(cb.equal(compensateMoneyField, helperOrderCompensate.getCompensateMoney()));
                }
                // 待补偿金额相等匹配
                if(helperOrderCompensate.getWaitForCompensateMoney()!=null){
                    list.add(cb.equal(waitForCompensateMoneyField, helperOrderCompensate.getWaitForCompensateMoney()));
                }
                // 补偿对象模糊搜素
                if(StrUtil.isNotBlank(helperOrderCompensate.getCompensateTarget())){
                    list.add(cb.like(compensateTargetField,'%'+helperOrderCompensate.getCompensateTarget()+'%'));
                }
                // 开户行模糊搜素
                if(StrUtil.isNotBlank(helperOrderCompensate.getOpenBankName())){
                    list.add(cb.like(openBankNameField,'%'+helperOrderCompensate.getOpenBankName()+'%'));
                }
                // 银行账号模糊搜素
                if(StrUtil.isNotBlank(helperOrderCompensate.getBankNo())){
                    list.add(cb.like(bankNoField,'%'+helperOrderCompensate.getBankNo()+'%'));
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

    @Override
    public HelperOrderCompensate getByHelpOrderId(String helpOrderId) {
        return helperOrderCompensateDao.getByHelperOrderId(helpOrderId);
    }
}